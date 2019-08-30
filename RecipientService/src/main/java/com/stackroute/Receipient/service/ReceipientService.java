package com.stackroute.Receipient.service;

import com.stackroute.Receipient.domain.Receipient;
import com.stackroute.Receipient.exception.ReceipientAlreadyExistsException;
import com.stackroute.Receipient.exception.ReceipientNotFoundException;

import java.util.List;

public interface ReceipientService {
    public Receipient saveNewProductOwner(Receipient owner) throws ReceipientAlreadyExistsException;
    public List<Receipient> getAllOwners() throws ReceipientNotFoundException;
    public Receipient deleteOwner(String id) throws ReceipientNotFoundException;
    public Receipient updateOwnerdetails(Receipient owner) throws ReceipientNotFoundException;
    public List<Receipient> getProductOwnerByName(String owner) throws ReceipientNotFoundException;
}
