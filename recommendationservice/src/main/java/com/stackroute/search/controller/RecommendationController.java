package com.stackroute.search.controller;

import com.stackroute.search.service.RecommendationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin
@Api("Search based on query")
public class RecommendationController {

    private RecommendationServiceImpl recommendationService;

    public RecommendationController(RecommendationServiceImpl recommendationService) {
        this.recommendationService = recommendationService;
    }

    //GetMapping method to search by email
    @ApiOperation("Execute Search Query")
    @GetMapping("recommend/{email}")
    public ResponseEntity<List<String>> retrieveReceiverId(@PathVariable("email") String email) throws IOException {
        return new ResponseEntity<List<String>>(recommendationService.retrieveReceiverId(email), HttpStatus.OK);
    }


}