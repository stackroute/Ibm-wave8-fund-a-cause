package com.stackroute.CauseCRUD.Service;


import com.stackroute.CauseCRUD.Domain.CauseCRUD;
import com.stackroute.CauseCRUD.Exception.CauseAlreadyExistsException;
import com.stackroute.CauseCRUD.Exception.CauseNotFoundException;
import com.stackroute.CauseCRUD.Repository.CauseCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Optional;

@Service
public class CauseCRUDServiceImpl implements CauseCRUDService {
    private CauseCRUDRepository repository;

    @Autowired
    public CauseCRUDServiceImpl(CauseCRUDRepository repository) {
        this.repository = repository;
    }


    @Override
    public CauseCRUD saveNewCause(CauseCRUD cause) throws CauseAlreadyExistsException {
        if (repository.existsById(cause.getId())) {
            throw new CauseAlreadyExistsException("Cause already exists!");
        }
        CauseCRUD savedCause = repository.save(cause);
        return savedCause;

    }

    @Override
    public List<CauseCRUD> getAllCauses() throws CauseNotFoundException {
        List<CauseCRUD> causelist = repository.findAll();
        if (causelist.isEmpty()) {
            throw new CauseNotFoundException("Cause list empty");
        }
        return causelist;    }

    @Override
    public CauseCRUD deleteCause(String id) throws CauseNotFoundException {
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
    public CauseCRUD updateCausedetails(CauseCRUD cause) throws CauseNotFoundException {
        Optional<CauseCRUD> Optional = repository.findById(cause.getId());
        if(Optional.isEmpty()){
            throw new CauseNotFoundException("Cause not found!");
        }

        repository.save(cause);
        return Optional.get();    }

    @Override
    public List<CauseCRUD> getCauseByName(String name) throws CauseNotFoundException {
        List<CauseCRUD> product =repository.getCauseByName(name);
        if(product.isEmpty())
        {
            throw  new CauseNotFoundException("Cause not Found");
        }
        return product;
    }
}
