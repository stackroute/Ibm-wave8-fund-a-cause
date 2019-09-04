package com.stackroute.cause.controller;


import com.stackroute.cause.domain.Cause;
import com.stackroute.cause.service.CauseService;
import com.stackroute.cause.core.Pipeline;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="api/v1/")
public class CauseController {
    private CauseService service;

    @Autowired
    private KafkaTemplate<String,Cause> kafkaTemplate;

    public static final String TOPIC="Cause";

    @Autowired
    public CauseController(CauseService service) {
        this.service = service;
    }

    @PostMapping("cause")
    public ResponseEntity<?> saveNewCause(@RequestBody Cause cause){
        ResponseEntity responseEntity;

        try{
            service.saveNewCause(cause);
            responseEntity=new ResponseEntity<String>("Cause is registered", HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        kafkaTemplate.send(TOPIC,cause);
        return responseEntity;
    }

    /*Get all the causes*/
    @GetMapping("causes")
    public ResponseEntity<?> getAllCauses() {
        ResponseEntity responseEntity;
        try {

            responseEntity = new ResponseEntity<List<Cause>>(service.getAllCauses(), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    /*Delete the cause using id attribute*/
    @DeleteMapping({"cause/{id}"})
    public ResponseEntity<?> deleteCause(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try {
            service.deleteCause(id);
            responseEntity = new ResponseEntity<String>("Cause deleted", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    /*Update the cause using id attribute*/
    @PutMapping("cause/{id}")
    public ResponseEntity<?> updateCause(@RequestBody Cause cause){
        ResponseEntity responseEntity;
        try {
            service.updateCausedetails(cause);
            responseEntity= new ResponseEntity<String>("Cause Details updated", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    /*Get cause details using name attribute*/
    /*Stanford Core NLP used for minimal search*/
    @GetMapping("cause/{name}")
    public ResponseEntity<?> getCauseByName(@PathVariable String name) {
        ResponseEntity responseEntity;

        String search="";

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

        CoreDocument coreDocument = new CoreDocument(name);

        stanfordCoreNLP.annotate(coreDocument);

        List<CoreLabel> coreLabelList = coreDocument.tokens();


        for(CoreLabel coreLabel : coreLabelList) {

            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            if(pos.equals("NN") || pos.equals("NNP") || pos.equals("NNPS")) {
                System.out.println(coreLabel.originalText());
                search = search.concat(" "+coreLabel.originalText());
            }
        }
        search = search.trim();
        try {
            List<Cause> causeList=service.getCauseByName(search);
            responseEntity = new ResponseEntity<List<Cause>>(causeList, HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

}
