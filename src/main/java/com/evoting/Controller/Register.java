package com.evoting.Controller;

import com.evoting.DTO.UserDTO;
import com.evoting.Service.EmailService;
import com.evoting.Service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Random;

@Controller // Use @Controller instead of @RestController
public class Register {
	@Autowired
	private RegistrationService registrationService;
	@Autowired
			private EmailService emailService;




	HashMap<Object , Object > map = new HashMap<>();

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("user", new UserDTO());
		return "registration"; // Return the name of the registration view
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") UserDTO user, Model model) {
		String otp = generateOtp();

		map.put("otp",otp);

		map.put("UserDTO",user);
		System.out.println("sent the otp to the client");
		emailService.sendSimpleMail(user.getEmail(),otp);
		System.out.println("otp sent ");
		return "redirect:/otpForm"; // Redirect to the OTP form
	}

	@GetMapping("/otpForm")
	public String showOtpForm() {
		return "otp"; // Return the name of the OTP form view
	}

	@PostMapping("/evoting/verify")
	public String handleverifyOtp(Model model , @RequestParam("otp") String otp) {
		System.out.println(otp+"-->"+ map.get("otp"));
		if (otp.equals(map.get("otp"))) {
			registrationService.SaveUser((UserDTO) map.get("UserDTO"));
			System.out.println("User saved successfully!");
			return "sucess"; // Return the name of the success view
		} else {
			System.out.println("Invalid OTP");
			return "error"; // Return the name of the error view
		}
	}


	public String generateOtp() {

		String numbers = "0123456789";
		StringBuilder otp = new StringBuilder();
		Random random = new Random();

		// Generate OTP of given length
		for (int i = 0; i < 6; i++) {
			otp.append(numbers.charAt(random.nextInt(numbers.length())));
		}

		return otp.toString();


	}
}
