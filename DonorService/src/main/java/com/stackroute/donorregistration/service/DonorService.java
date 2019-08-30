package com.stackroute.DonorRegistration.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.DonorRegistration.Domain.Donor;
import com.stackroute.DonorRegistration.Exceptions.DonorAlreadyExistsException;
import com.stackroute.DonorRegistration.Exceptions.DonorNotFoundException;

import java.util.List;

public interface DonorService {
    Donor saveNewDonor(Donor donor) throws DonorAlreadyExistsException, JsonProcessingException;

    List<Donor> getAllDonors() throws DonorNotFoundException;

    List<Donor> deleteById(String id) throws DonorNotFoundException;

    Donor updateById(Donor donor) throws DonorNotFoundException;

    List<Donor> getByName(String name) throws DonorNotFoundException;

}
