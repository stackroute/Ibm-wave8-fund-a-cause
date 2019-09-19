package com.stackroute.search.repository;

import com.stackroute.search.models.Donor;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

//Repository to perform operations on Donor
public interface DonationRepository extends Neo4jRepository<Donor, Long> {

}