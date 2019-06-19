package com.businessapp.model;

import com.businessapp.logic.IDGenerator;
import com.businessapp.model.Room.CleanStatus;
import com.businessapp.model.Room.RoomStatus;


/**
 * Article is an Entity-class that represents an item for rent or for sale.
 * 
 * @author Sven Graupner
 * 
 */
public class Article implements EntityIntf {
	private static final long serialVersionUID = 1L;

	/*
	 * Properties.
	 */
	private final String id;	// Unique, non-null Article id.

	private String name;		// Article name.

	private long price;		// Article price.
	public enum RoomStatus { AVAILABLE, SUSPENDED, TERMINATED, LOAN };
	//
	private RoomStatus status;
	
	public enum CleanStatus{CLEAN,DIRTY};
	private CleanStatus statusc;
	
	/**
	 * Private default constructor (required by JSON deserialization).
	 */
	@SuppressWarnings("unused")
	private Article() { this( null, null, (long)0 ); }

	/**
	 * Public constructor.
	 * @param name Article name.
	 */
	
	public Article( String name, long  price ) {
		this( null, name,price);
		
 
		
	}

	/**
	 * Public constructor.
	 * @param id Article id. If null, a new id is generated.
	 * @param name Article name.
	 */
	private static final IDGenerator IDG = new IDGenerator( null, IDGenerator.IDTYPE.NUM, 8 );
	//
	public Article( String id, String name, long price ) {
		this.id = id==null? IDG.nextId() : id;
		this.name = name;
		setPrice( price );
	}
	
	public Article(String id, String name, long price,RoomStatus status) 
	{
		this.id = id==null? IDG.nextId() : id;
		this.name = name;
		setPrice( price );
		this.status=status;
		
	}
	public Article(String id, String name, long price,RoomStatus status,CleanStatus statusc) 
	{
		this.id = id==null? IDG.nextId() : id;
		this.name = name;
		setPrice( price );
		this.status=status;
		this.statusc=statusc;
		
	}


	/*
	 * Public getter/setter methods.
	 */

	/**
	 * Return Article id.
	 * @return Article id.
	 */
	public String getId() {		// No setId(). Id's cannot be altered.
		return id;
	}

	/**
	 * Return Article name.
	 * @return Article name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set Article name.
	 * @param name Article name.
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * Return Article price.
	 * @return Article price.
	 */
	public String getPrice() {
		return Long.toString(price);
	}

	/**
	 * Set Article price.
	 * @param name Article price.
	 */
	public void setPrice( long price ) {
		this.price = price;
	}
	public void clean() {
		this.statusc =CleanStatus.CLEAN;
	}

}
