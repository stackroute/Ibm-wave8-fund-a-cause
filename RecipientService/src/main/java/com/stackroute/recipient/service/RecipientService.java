package com.stackroute.recipient.service;

import com.stackroute.recipient.domain.Recipient;
import com.stackroute.recipient.exception.RecipientAlreadyExistsException;
import com.stackroute.recipient.exception.RecipientNotFoundException;

import java.util.List;

public interface RecipientService {
    public Recipient saveNewProductOwner(Recipient owner) throws RecipientAlreadyExistsException;
    public List<Recipient> getAllOwners() throws RecipientNotFoundException;
    public Recipient deleteOwner(String id) throws RecipientNotFoundException;
    public Recipient updateOwnerdetails(Recipient owner) throws RecipientNotFoundException;
    public Recipient getProductOwnerByName(String owner) throws RecipientNotFoundException;
    public Recipient getProductOwnerById(String Owner) throws RecipientNotFoundException;

}
