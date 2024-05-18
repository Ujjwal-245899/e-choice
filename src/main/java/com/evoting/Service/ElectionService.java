package com.evoting.Service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ElectionService {

    boolean saveElection(String name, String startDate, String endDate, List<String> candidateNames, List<MultipartFile> candidateImages) throws IOException;}
