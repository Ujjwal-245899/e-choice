package com.evoting.Service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface ElectionService {

    public boolean saveElection(String name,String startDate, String endDate, Map<String, String> params);
}
