package com.stackroute.Cause.Repository;

import com.stackroute.Cause.Domain.Cause;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CauseRepository extends MongoRepository<Cause,String> {
    /*Query to get Cause by name*/
    @Query("{name:'?0'}")
    List<Cause> getCauseByName(String name);
}
