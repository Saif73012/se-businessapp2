package com.businessapp.model;

import java.util.ArrayList;
import java.util.List;

import com.businessapp.logic.IDGenerator;
import com.businessapp.model.Customer.CustomerStatus;

public class Room implements EntityIntf {
	private static final long serialVersionUID = 1L;

	/*
	 * Properties.
	 */
	private final String id;	// Unique, non-null Customer id.

	private String name;		// Customer name.


	// Room status.
	public enum RoomStatus { AVAILABLE, SUSPENDED, TERMINATED };
	//
	private RoomStatus status;
	
	public enum CleanStatus{CLEAN,DIRTY};
	private CleanStatus statusc;
	
	


	/**
	 * Private default constructor (required by JSON deserialization).
	 */
	

	/**
	 * Public constructor.
	 * @param name Customer name.
	 */
	private static final IDGenerator IDG = new IDGenerator( "C.", IDGenerator.IDTYPE.AIRLINE, 6 );
	public Room(String id, String name) {
		this.id = id==null? IDG.nextId() : id;
		this.name=name;
		this.status=RoomStatus.SUSPENDED;
	}

	/*
	 * Public getter/setter methods.
	 */

	/**
	 * Return Customer id.
	 * @return Customer id.
	 */
	public String getId() {		
		return id;
	}

	
	public void clean() {
		this.statusc =CleanStatus.CLEAN;
	}

	
}
