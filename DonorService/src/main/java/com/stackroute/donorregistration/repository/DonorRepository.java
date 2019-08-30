package com.stackroute.donorregistration.repository;

import com.stackroute.donorregistration.domain.Donor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepository extends MongoRepository<Donor, String> {

    /* query to get donor by name */
    @Query("{username:'?0'}")
    List<Donor> findByName(String name);

}
