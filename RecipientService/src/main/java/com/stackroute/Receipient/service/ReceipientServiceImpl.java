package com.stackroute.Receipient.service;

import com.stackroute.Receipient.domain.Receipient;
import com.stackroute.Receipient.exception.ReceipientAlreadyExistsException;
import com.stackroute.Receipient.exception.ReceipientNotFoundException;
import com.stackroute.Receipient.repository.ReceipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceipientServiceImpl implements ReceipientService {
    private ReceipientRepository repository;
    @Autowired
    private KafkaTemplate<String,Receipient> kafkaTemplate;

    public static final String TOPIC="registration";


    @Autowired
    public ReceipientServiceImpl(ReceipientRepository repository) {
        this.repository = repository;
    }


    @Override
    public Receipient saveNewProductOwner(Receipient owner) throws ReceipientAlreadyExistsException {
        if (repository.existsById(owner.getId())) {
            throw new ReceipientAlreadyExistsException("Owner already exists!");
        }
        kafkaTemplate.send(TOPIC,owner);
        Receipient savedOwner = repository.save(owner);

        return savedOwner;


    }

    @Override
    public List<Receipient> getAllOwners() throws ReceipientNotFoundException {
        List<Receipient> ownerlist = repository.findAll();
        if (ownerlist.isEmpty()) {
            throw new ReceipientNotFoundException("Owners list empty");
        }
    return ownerlist;
    }

    @Override
    public Receipient deleteOwner(String username) throws ReceipientNotFoundException {
        if(repository.existsById(username))
        {
            repository.deleteById(username);
        }
        else
        {
            throw new ReceipientNotFoundException("Owner Not Found");

        }
        return null;
    }

    @Override
    public Receipient updateOwnerdetails(Receipient owner) throws ReceipientNotFoundException {
        Optional<Receipient> ownerOptional = repository.findById(owner.getId());
        if(ownerOptional.isEmpty()){
            throw new ReceipientNotFoundException("Owner not found!");
        }
        //owner.setId(id);
        repository.save(owner);
        return ownerOptional.get();
    }

    @Override
    public List<Receipient> getProductOwnerByName(String owner) throws ReceipientNotFoundException {
        List<Receipient> product =repository.getProductOwnerByName(owner);
        if(product.isEmpty())
        {
            throw  new ReceipientNotFoundException("Owner not Found");
        }
        return product;
    }


}
