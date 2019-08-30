package com.stackroute.Receipient.repository;

import com.stackroute.Receipient.domain.Receipient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceipientRepository extends MongoRepository<Receipient,String> {
    /*Query to get product owner by name*/
    @Query("{name:'?0'}")
    List<Receipient> getProductOwnerByName(String name);
}
