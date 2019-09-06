package com.stackroute.recipient.service;

import com.stackroute.recipient.domain.Recipient;
import com.stackroute.recipient.exception.RecipientAlreadyExistsException;
import com.stackroute.recipient.exception.RecipientNotFoundException;
import com.stackroute.recipient.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipientServiceImpl implements RecipientService {
    private RecipientRepository repository;
    @Autowired
    private KafkaTemplate<String, Recipient> kafkaTemplate;

    public static final String TOPIC="registration";


    @Autowired
    public RecipientServiceImpl(RecipientRepository repository) {
        this.repository = repository;
    }


    @Override
    public Recipient saveNewProductOwner(Recipient owner) throws RecipientAlreadyExistsException {
        if (repository.existsById(owner.getId())) {
            throw new RecipientAlreadyExistsException("Owner already exists!");
        }
        kafkaTemplate.send(TOPIC,owner);
        return repository.save(owner);




    }

    @Override
    public List<Recipient> getAllOwners() throws RecipientNotFoundException {
        List<Recipient> ownerlist = repository.findAll();
        if (ownerlist.isEmpty()) {
            throw new RecipientNotFoundException("Owners list empty");
        }
    return ownerlist;
    }

    @Override
    public Recipient deleteOwner(String username) throws RecipientNotFoundException {
        if(repository.existsById(username))
        {
            repository.deleteById(username);
        }
        else
        {
            throw new RecipientNotFoundException("Owner Not Found");

        }
        return null;
    }

    @Override
    public Recipient updateOwnerdetails(Recipient owner) throws RecipientNotFoundException {

        Optional<Recipient> ownerOptional = repository.findById(owner.getId());
        if(ownerOptional.isEmpty()){
            throw new RecipientNotFoundException("Owner not found!");
        }

        repository.save(owner);
        return ownerOptional.get();
    }

    @Override
    public Recipient getProductOwnerByName(String owner) throws RecipientNotFoundException {
        Recipient product =repository.getProductOwnerByEmail(owner);
//        if(product.isEmpty())
//        {
//            throw  new RecipientNotFoundException("Owner not Found");
//        }
        return product;
    }
    @Override
    public Recipient getProductOwnerById(String id) throws RecipientNotFoundException {
        Optional<Recipient> product =repository.findById(id);
        if(product.isEmpty())
        {
            throw  new RecipientNotFoundException("Owner not Found");
        }
        return product.get();
    }


}
