package com.stackroute.Cause.Service;

import com.stackroute.Cause.Domain.Cause;
import com.stackroute.Cause.Exception.CauseAlreadyExistsException;
import com.stackroute.Cause.Exception.CauseNotFoundException;

import java.util.List;

public interface CauseService {
    public Cause saveNewCause(Cause cause) throws CauseAlreadyExistsException;
    public List<Cause> getAllCauses() throws CauseNotFoundException;
    public Cause deleteCause(String id) throws CauseNotFoundException;
    public Cause updateCausedetails(Cause cause) throws CauseNotFoundException;
    public List<Cause> getCauseByName(String name) throws CauseNotFoundException;
}
