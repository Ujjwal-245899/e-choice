package com.evoting.Service;

import com.evoting.DAO.CandidateRepository;
import com.evoting.DAO.ElectionRepository;
import com.evoting.Model.Candidate;
import com.evoting.Model.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ElectionServiceImpl implements ElectionService{
    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository;


    @Override
    public boolean saveElection(String name, String startDate, String endDate, List<String> candidateNames, List<MultipartFile> candidateImages) throws IOException{
        // Create a new Election object
        final long MAX_FILE_SIZE = 3 * 1024 * 1024; // 3 MB in bytes

        // Validate file sizes
        for (MultipartFile candidateImage : candidateImages) {
            if (candidateImage.getSize() > MAX_FILE_SIZE) {
                throw new IOException("File size must be less than 3 MB");
            }
        }

        Election election = new Election();
        election.setName(name);
        election.setStartDate(startDate);
        election.setEndDate(endDate);

        List<Candidate> candidates = new ArrayList<>();

        for (int i = 0; i < candidateNames.size(); i++) {
            String candidateName = candidateNames.get(i);
            MultipartFile candidateImage = candidateImages.get(i);

            Candidate candidate = new Candidate();
            candidate.setName(candidateName);
            candidate.setImage(candidateImage.getBytes());
            candidate.setElection(election);

            candidates.add(candidate);
        }

        election.setCandidates(candidates);
        electionRepository.save(election);

        return true;
    }
}
