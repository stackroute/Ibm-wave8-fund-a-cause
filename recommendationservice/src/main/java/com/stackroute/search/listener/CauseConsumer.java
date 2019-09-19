package com.stackroute.search.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.search.models.Cause;
import com.stackroute.search.repository.CauseRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;

@Service
public class CauseConsumer {

    private CauseRepository causeRepository;

    public CauseConsumer(CauseRepository causeRepository) {
        this.causeRepository = causeRepository;
    }

    @KafkaListener(topics="fa-Cause",groupId = "group_id_cause")
    public void consume(Cause cause) throws IOException {

        System.out.println(cause);

        System.out.println(causeRepository.save(cause));

    }

}
