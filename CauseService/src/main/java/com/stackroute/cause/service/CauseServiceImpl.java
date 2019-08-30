package com.stackroute.cause.service;


import com.stackroute.cause.domain.Cause;
import com.stackroute.cause.exception.CauseAlreadyExistsException;
import com.stackroute.cause.exception.CauseNotFoundException;
import com.stackroute.cause.repository.CauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CauseServiceImpl implements CauseService {
    private CauseRepository repository;

    @Autowired
    public CauseServiceImpl(CauseRepository repository) {
        this.repository = repository;
    }


    @Override
    public Cause saveNewCause(Cause cause) throws CauseAlreadyExistsException {
        if (repository.existsById(cause.getId())) {
            throw new CauseAlreadyExistsException("Cause already exists!");
        }
        Cause savedCause = repository.save(cause);
        return savedCause;

    }

    @Override
    public List<Cause> getAllCauses() throws CauseNotFoundException {
        List<Cause> causelist = repository.findAll();
        if (causelist.isEmpty()) {
            throw new CauseNotFoundException("Cause list empty");
        }
        return causelist;    }

    @Override
    public Cause deleteCause(String id) throws CauseNotFoundException {
        if(repository.existsById(id))
        {
            repository.deleteById(id);
        }
        else
        {
            throw new CauseNotFoundException("Cause Not Found");

        }
        return null;
    }

    @Override
    public Cause updateCausedetails(Cause cause) throws CauseNotFoundException {
        Optional<Cause> Optional = repository.findById(cause.getId());
        if(Optional.isEmpty()){
            throw new CauseNotFoundException("Cause not found!");
        }

        repository.save(cause);
        return Optional.get();    }

    @Override
    public List<Cause> getCauseByName(String name) throws CauseNotFoundException {
        List<Cause> product =repository.getCauseByName(name);
        if(product.isEmpty())
        {
            throw  new CauseNotFoundException("Cause not Found");
        }
        return product;
    }
}
