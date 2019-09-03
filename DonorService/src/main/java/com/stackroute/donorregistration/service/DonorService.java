package com.stackroute.donorregistration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.donorregistration.domain.Donor;
import com.stackroute.donorregistration.exceptions.DonorAlreadyExistsException;
import com.stackroute.donorregistration.exceptions.DonorNotFoundException;

import java.util.List;

public interface DonorService {
    Donor saveNewDonor(Donor donor) throws DonorAlreadyExistsException, JsonProcessingException;

    List<Donor> getAllDonors() throws DonorNotFoundException;

    List<Donor> deleteById(String id) throws DonorNotFoundException;

    Donor updateById(Donor donor) throws DonorNotFoundException;

    List<Donor> getByName(String name) throws DonorNotFoundException;

}
