package com.evoting.Controller;

import com.evoting.DAO.CandidateRepository;
import com.evoting.DAO.ElectionRepository;
import com.evoting.DTO.ElectionRequest;
import com.evoting.Model.Candidate;
import com.evoting.Model.Election;
import com.evoting.Service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ElectionController {

    @Autowired
    private ElectionService electionService;


    @GetMapping("/adminpage")
    public ModelAndView showAdminPage()
    {
        return new ModelAndView("adminPage");
    }
    @GetMapping("/createElection")
    public String showCreateElection(){
        return "createElection";
    }



    @PostMapping("/saveElection" )
    @ResponseBody
    public String SaveElection(@RequestParam String name,
                               @RequestParam String startDate,
                               @RequestParam String endDate,
                               @RequestParam Map<String, String> params) {

      boolean flag=  electionService.saveElection(name, startDate, endDate, params);

      if(flag==true)
      {
          return "sucess";
      }






        // Return a response
        return "";
    }
    }


