package com.businessapp.modelgen.Model;


import java.util.*;

import com.businessapp.model.Customer.CustomerStatus;

/**
 * 
 */
public class Customer extends EntityIntf {

    /**
     * Default constructor
     */
    public Customer() {
    }

    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private List<String> contacts;

    /**
     * 
     */
    private List<Note> notes;

    /**
     * 
     */
    private CustomerStatus status;

    /**
     * @return
     */
    public String getId() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public String getName() {
        // TODO implement here
        return "";
    }

    /**
     * @param String 
     * @return
     */
    public void setName( String name) {
        // TODO implement here
       // return null;
    }

    /**
     * @return
     */
    public List<String> getContacts() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Note> getNotes() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public CustomerStatus getStatus() {
        // TODO implement here
        return null;
    }

    /**
     * @param CustomerStatus 
     * @return
     */
    public void setStatus( CustomerStatus status) {
        // TODO implement here
        //return null;
    }

}