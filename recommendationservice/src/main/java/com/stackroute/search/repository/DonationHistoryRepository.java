package com.stackroute.search.repository;
import com.stackroute.search.models.Donation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

//Repository to perform operations on Donor
public interface DonationHistoryRepository extends Neo4jRepository<Donation, Long> {

}