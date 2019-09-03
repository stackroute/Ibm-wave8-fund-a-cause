package com.stackroute.donationservice.repository;

import com.stackroute.donationservice.domain.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends MongoRepository<Donation, String>
{

    @Query("{donationId:'?0'}")
    Optional<Donation> searchByDonationId(String id);

    @Query("{donorId:'?0'}")
    Optional<Donation> searchByDonorId(String id);

    @Query("{causeId:'?0'}")
    Optional<Donation> searchByCauseId(String id);

    @Query("{receiverId:'?0'}")
    Optional<Donation> searchByReceiverId(String id);




}

