package com.stackroute.cause;

import com.stackroute.cause.core.Pipeline;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CauseApplication {

	StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
	public static void main(String[] args) {
		SpringApplication.run(CauseApplication.class, args);
	}

}
