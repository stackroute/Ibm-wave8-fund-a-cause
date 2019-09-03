package com.stackroute.donorregistration.exceptions;


/* Exception thrown when donor not found */

public class DonorNotFoundException extends Exception{
    public DonorNotFoundException(String message)
    {
        // Call constructor of parent Exception
        super(message);

    }
}
