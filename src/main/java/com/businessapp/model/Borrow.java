package com.businessapp.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.businessapp.logic.IDGenerator;
import com.businessapp.model.Room.CleanStatus;
import com.businessapp.model.Room.RoomStatus;

public class Borrow implements EntityIntf {
	private static final long serialVersionUID = 1L;

	/*
	 * Properties.
	 */
	public enum ArticleStatus { LOAN, AVAILABLE, UNDERCLEANING, RESERVED };
	
	private final String id;	// Unique, non-null Customer id.

	private String articleID;
	
	private String kundeID;
	
	private String Ausleihdatum;
	
	private int Tage;
	
//	private Customer kunde;
//	private Article Artikel;
	
	


	// Room status.
	
	//
	private ArticleStatus status =ArticleStatus.AVAILABLE;
	
	
	


	/**
	 * Private default constructor (required by JSON deserialization).
	 */
	@SuppressWarnings("unused")
	private Borrow() { this( null, null, null ); }

	/**
	 * Public constructor.
	 * @param name Customer name.
	 */
	
	
	public Borrow( String kundeID, String articleID ) {
		this( null, kundeID, articleID );
	}
	
	
	
	
	

	/**
	 * Public constructor.
	 * @param id Customer id. If null, a new id is generated.
	 * @param name Customer name.
	 */
private static final IDGenerator IDG = new IDGenerator( "C.", IDGenerator.IDTYPE.AIRLINE, 6 );
	
	public Borrow(String id, String kundeID,String articleID) {
		this.id = id==null? IDG.nextId() : id;
	this.kundeID=kundeID;
	this.articleID=articleID;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	Date date = new Date();
	this.Ausleihdatum=dateFormat.format(date);
	this.status=ArticleStatus.LOAN;
	this.Tage=1;
	}
	//
	public Borrow(String id, String kundeID,String articleID,String Ausleihdatum ,int days,ArticleStatus status) {
		this.id = id==null? IDG.nextId() : id;
	this.kundeID=kundeID;
	this.Ausleihdatum=Ausleihdatum;
	this.Tage=days;
	this.articleID=articleID;
	this.status=status;
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

	public  ArticleStatus getStatus() 
	{
		return status;
	}
	public void SetStatus(ArticleStatus status) 
	{
		this.status=status;
	}
	
	public String getArticle() 
	{
		return articleID;
	}
	
	public String getCustomer() 
	{
		return kundeID;
	}
	
	public void setUserID(String userID) {
    	this.kundeID = userID;
    }
	public void setArticleID(String articleID) {
    	this.articleID = articleID;
    }
	
	public void setAusleihedatum(String Ausleihdatum) {
		this.Ausleihdatum =Ausleihdatum;
	}
	public String getAusleihdatum() 
	{
		return Ausleihdatum;
	}
	
	public int getRückgabedatum() 
	{
		return Tage;
	}
	
	public void setRückgabedatum(int days) {
		this.Tage =days;
	}

	
}
