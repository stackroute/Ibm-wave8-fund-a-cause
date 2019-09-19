package com.stackroute.search.repository;

import com.stackroute.search.models.Cause;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Repository to perform operations on Donor
public interface CauseRepository extends Neo4jRepository<Cause, Long> {

    @Query("MATCH (d:Donor),(do:Donation) WHERE d.email={email} AND d.email = do.donorId \n" +
            "CREATE (d)-[rel:DONATION_HISTORY]->(do) RETURN do.receiverId")
    List<String> retrieveReceiverId(@Param("email") String email);

}