package com.stackroute.cause.service;

import com.stackroute.cause.domain.Cause;
import com.stackroute.cause.exception.CauseAlreadyExistsException;
import com.stackroute.cause.exception.CauseNotFoundException;

import java.util.List;

public interface CauseService {
    public Cause saveNewCause(Cause cause) throws CauseAlreadyExistsException;
    public List<Cause> getAllCauses() throws CauseNotFoundException;
    public Cause deleteCause(String id) throws CauseNotFoundException;
    public Cause updateCausedetails(Cause cause) throws CauseNotFoundException;
    public List<Cause> getCauseByName(String name) throws CauseNotFoundException;

    public List<Cause> getCauseByCauseType(String type) throws CauseNotFoundException;

    public List<Cause> getCauseByCauseReceiver(String receiver) throws CauseNotFoundException;
}
