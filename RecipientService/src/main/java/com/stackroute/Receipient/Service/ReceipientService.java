package com.stackroute.Receipient.Service;

import com.stackroute.Receipient.Domain.Receipient;
import com.stackroute.Receipient.Exception.ReceipientAlreadyExistsException;
import com.stackroute.Receipient.Exception.ReceipientNotFoundException;

import java.util.List;

public interface ReceipientService {
    public Receipient saveNewProductOwner(Receipient owner) throws ReceipientAlreadyExistsException;
    public List<Receipient> getAllOwners() throws ReceipientNotFoundException;
    public Receipient deleteOwner(String id) throws ReceipientNotFoundException;
    public Receipient updateOwnerdetails(Receipient owner) throws ReceipientNotFoundException;
    public List<Receipient> getProductOwnerByName(String owner) throws ReceipientNotFoundException;
}
