package com.stackroute.CauseCRUD.Repository;

import com.stackroute.CauseCRUD.Domain.CauseCRUD;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CauseCRUDRepository extends MongoRepository<CauseCRUD,String> {
    /*Query to get Cause by name*/
    @Query("{name:'?0'}")
    List<CauseCRUD> getCauseByName(String name);
}
