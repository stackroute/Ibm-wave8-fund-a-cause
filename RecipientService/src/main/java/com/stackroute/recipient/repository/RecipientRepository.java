package com.stackroute.recipient.repository;

import com.stackroute.recipient.domain.Recipient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepository extends MongoRepository<Recipient,String> {
    /*Query to get product owner by name*/
    @Query("{email:'?0'}")
    List<Recipient> getProductOwnerByEmail(String name);
}
