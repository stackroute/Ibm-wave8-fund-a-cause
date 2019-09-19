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

    Donor getByName(String email) throws DonorNotFoundException;

    Donor getById(String id) throws DonorNotFoundException;

}
