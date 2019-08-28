package com.stackroute.CauseCRUD.Service;

import com.stackroute.CauseCRUD.Domain.CauseCRUD;
import com.stackroute.CauseCRUD.Exception.CauseAlreadyExistsException;
import com.stackroute.CauseCRUD.Exception.CauseNotFoundException;

import java.util.List;

public interface CauseCRUDService {
    public CauseCRUD saveNewCause(CauseCRUD cause) throws CauseAlreadyExistsException;
    public List<CauseCRUD> getAllCauses() throws CauseNotFoundException;
    public CauseCRUD deleteCause(String id) throws CauseNotFoundException;
    public CauseCRUD updateCausedetails(CauseCRUD cause) throws CauseNotFoundException;
    public List<CauseCRUD> getCauseByName(String name) throws CauseNotFoundException;
}
