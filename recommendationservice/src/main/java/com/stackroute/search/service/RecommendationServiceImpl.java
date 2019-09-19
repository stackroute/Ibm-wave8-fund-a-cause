package com.stackroute.search.service;


import com.stackroute.search.repository.CauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private CauseRepository causeRepository;

    @Autowired
    public RecommendationServiceImpl(CauseRepository causeRepository) {
        this.causeRepository = causeRepository;
    }

    @Override
    public List<String> retrieveReceiverId(String email){
        return causeRepository.retrieveReceiverId(email);
    }

}
