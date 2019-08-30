package com.stackroute.donationservice.repository;

import com.stackroute.donationservice.domain.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends MongoRepository<Donation, String>
{
}

