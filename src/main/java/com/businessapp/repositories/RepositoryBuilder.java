package com.businessapp.repositories;

import java.util.ArrayList;
import java.util.List;

import com.businessapp.logic.ManagedComponentIntf;
import com.businessapp.model.Article;
import com.businessapp.model.Article.RoomStatus;
import com.businessapp.model.Customer;
import com.businessapp.model.Customer.CustomerStatus;
import com.businessapp.model.Note;
import com.businessapp.model.Borrow;
import com.businessapp.model.Borrow.ArticleStatus;


/**
 * Public class that builds an in-memory mock model with a selection
 * of entity instances.
 * 
 * @author Sven Graupner
 *
 */
public class RepositoryBuilder implements ManagedComponentIntf {
	private static RepositoryBuilder _singleton = null;

	public static final String Customer = "Customer";
	public static final String Article = "Article";
	public static final String Borrow = "Borrow";

	private final CustomerRepositoryIntf customerRepository;
	private final ArticleRepositoryIntf articleRepository;
	private final BorrowRepositoryIntf borrowRepository;
	/**
	 * Private constructor.
	 */
	private RepositoryBuilder() {
		List<Customer> customerList = new ArrayList<Customer>();
		this.customerRepository = new CustomerRepositoryImpl( customerList );

		List<Article> articleList = new ArrayList<Article>();
		this.articleRepository = new ArticleRepositoryImpl( articleList );
	
		List<Borrow> borrowList = new ArrayList<Borrow>();
		this.borrowRepository = new BorrowRepositoryImpl(borrowList);
	}

	/**
	 * Public access method according to the Singleton pattern.
	 * 
	 * @return reference to ModelBuilder singleton instance.
	 */
	public static RepositoryBuilder getInstance() {
		if( _singleton == null ) {
			_singleton = new RepositoryBuilder();
		}
		return _singleton;
	}


	@Override
	public void start() {
		customerRepository.start();
		buildCustomerFixture( customerRepository.findAll() );
		articleRepository.start();
		buildArticleFixture( articleRepository.findAll() );
		borrowRepository.start();
		buildBorrowFixture(borrowRepository.findAll());
	}

	@Override
	public void stop() {
		articleRepository.stop();
		customerRepository.stop();
		borrowRepository.stop();
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}


	public CustomerRepositoryIntf getCustomerRepository() {
		return customerRepository;
	}

	public ArticleRepositoryIntf getArticleRepository() {
		return articleRepository;
	}
	public BorrowRepositoryIntf getBorrowRepository() {
	return borrowRepository;
	}
	

	/**
	 * Method to create a set of Customer entities.
	 * @param list container into which entities are inserted.
	 */
	public List<Customer> buildCustomerFixture( List<Customer> list ) {
		Customer c = new Customer( "Jens ","Baumann" );
		c.getContacts().add( "eme@yahoo.com" );
		
		list.add( c );

		c = new Customer( "Anne"," Meyer" );
		c.getContacts().add( "meyer244@gmail.com" );
		c.getContacts().add( "+49170482395" );
		list.add( c );

		c = new Customer( "Saifali"," Ali" );
		c.getContacts().add( "saif.ali@example.com" );
//		c.setStatus(CustomerStatus.ACTIVE);
		list.add( c );
		

		c = new Customer( "Isabella ","Johnson" );
		c.getContacts().add( "isabella.johnson@example.com" );
		c.setStatus( CustomerStatus.SUSPENDED );
		list.add( c );

		c = new Customer( "Ethan"," Williams" );
		c.getContacts().add( "ethan.williams@example.com" );
		list.add( c );

		c = new Customer( "Emma"," Jones" );
		c.getContacts().add( "emma.jones@example.com" );
		c.setStatus(CustomerStatus.SUSPENDED);
		list.add( c );

		c = new Customer( "Michael"," Brown" );
		c.getContacts().add( "michael.brown@example.com" );
		list.add( c );
		
		c = new Customer( "Max"," Walter" );
		c.getContacts().add( "max.walter@example.com" );
		list.add( c );
		
		c = new Customer( "Heinrich"," Weber" );
		c.getContacts().add( "heinrich.Weber@example.com" );
		list.add( c );
		
		c = new Customer( "Dr Mararethe"," Böse" );
		c.getContacts().add( "drmb@yahoo.de" );
		c.setStatus(CustomerStatus.SUSPENDED);
		
		Note e1 = new Note("Zahlt Rechnung verspätet");
		c.getNotes().add(e1);
		Note e2 = new Note("Berschwärt sich über Mitarbeiter");
		c.getNotes().add(e2);
		Note e3 = new Note("Greift Angestellte verbal an");
		c.getNotes().add(e3);
		Note e4 = new Note("Wurde aus dem Geschäft verwiesen");
		c.getNotes().add(e4);
		list.add( c );
		
		

		return list;
	}
	
	public List<Borrow>buildBorrowFixture(List<Borrow> list)
	{
		// public Borrow(String id, String kundeID,String articleID,String Ausleihdatum ,int days) {
		list.add(new Borrow(null,"Sven","R5","2019/06/13",1,ArticleStatus.UNDERCLEANING));
		list.add(new Borrow(null,"Hahn","R2","2019/06/14",2,ArticleStatus.LOAN));
		list.add(new Borrow(null,"Linus","R1","2019/06/14",5,ArticleStatus.RESERVED));
		list.add(new Borrow(null,"","R5","2019/06/15",1,ArticleStatus.AVAILABLE));
		list.add(new Borrow(null,"","R3","2019/06/14",2,ArticleStatus.AVAILABLE));
		list.add(new Borrow(null,"Myth","R4","2019/06/14",30,ArticleStatus.LOAN));
		list.add(new Borrow(null,"Serkan","R1","2019/06/19",3,ArticleStatus.LOAN));
		list.add(new Borrow(null,"Kevin","R5","2019/06/14",2,ArticleStatus.RESERVED));
		return list;
	}


	/**
	 * Method to create a set of Article entities.
	 * @param list container into which entities are inserted.
	 */
	
	public List<Article> buildArticleFixture( List<Article> list ) {
		list.add(new Article("R1","Raum Tonstudio A",1500,RoomStatus.AVAILABLE));
		list.add(new Article("R2","Raum Tanzstudio 1",2500,RoomStatus.AVAILABLE));
		list.add(new Article("R3","Raum Tanzstudio 2",3000,RoomStatus.AVAILABLE));
		list.add(new Article("R4","Stauraum",1500,RoomStatus.AVAILABLE));
		list.add(new Article("R5","Raum Tonstudio B",2000,RoomStatus.AVAILABLE));
		
		
		list.add( new Article(null, "Canon Objektiv EF 50mm f/1.2L USM", 154900  ) );
		list.add( new Article( "Canon Objektiv EF 50mm f/1.4 USM", 44900  ) );
		
		list.add( new Article( "Canon Objektiv EF 40mm f/2.8 STM", 23900  ) );
		list.add( new Article( "Canon Objektiv EF 50mm f/1.8 STM", 13900  ) );
		list.add( new Article( "Canon Objektiv EF 24-70mm f/4L IS USM", 92900  ) );
		list.add( new Article( "Canon Objektiv EF 24-105mm f/4L IS II USM", 119900  ) );
		list.add( new Article( "Canon Objektiv EF 24-70mm f/2.8L II USM", 201900  ) );
		list.add( new Article( "Canon Objektiv EF-S 18-55mm f/4-5.6 IS STM", 24900  ) );
		list.add( new Article( "Canon Objektiv EF-S 18-55mm f/3.5-5.6 IS II", 19900  ) );
		list.add( new Article( "Canon Objektiv EF-S 18-55mm f/3.5-5.6 IS STM", 24900  ) );
		list.add( new Article( "Canon Objektiv EF 24-105mm f/3.5-5.6 IS STM", 47900  ) );
		list.add( new Article( "Canon Objektiv EF-S 18-135mm f/3.5-5.6 IS STM + EW 73B + LC Kit", 49900 ) );
		list.add( new Article( "Canon Objektiv EF-S 18-200mm f/3.5-5.6 IS ", 58500  ) );
		list.add( new Article( "Canon Objektiv EF-S 18-135mm f/3.5-5.6 IS STM", 49900  ) );
		list.add( new Article( "Canon Objektiv EF-S 15-85mm f/3.5-5.6 IS USM", 79900  ) );
		list.add( new Article( "Canon Objektiv EF-S 18-135mm f/3.5-5.6 IS USM", 54900  ) );
		list.add( new Article( "Canon Objektiv EF-S 17-55mm f/2.8 IS USM", 91900  ) );
		list.add( new Article( "Canon Objektiv EF 400mm f/4 DO IS II USM", 702900  ) );
		list.add( new Article( "Canon Objektiv EF 800mm f/5.6L IS USM ", 1414900  ) );
		list.add( new Article( "Canon Objektiv EF 300mm f/4L IS USM", 146900  ) );
		list.add( new Article( "Canon Objektiv EF 200mm f/2.8L II USM ", 82900  ) );
		list.add( new Article( "Canon Objektiv EF 400mm f/5.6L USM", 144900  ));
		list.add( new Article( "Canon Objektiv EF 135mm f/2L USM ", 110900  ) );
		list.add( new Article( "Canon Objektiv EF 85mm f/1.8 USM", 47900  ) );
		list.add( new Article( "Canon Objektiv EF 100mm f/2 USM ", 52900  ) );
		list.add( new Article( "Canon Objektiv EF 200mm f/2L IS USM", 630900  ) );
		list.add( new Article( "Canon Objektiv EF 85mm f/1.2L II USM", 223900  ) );
		list.add( new Article( "Canon Objektiv EF 300mm f/2.8L IS II USM", 649900  ) );
		list.add( new Article( "Canon Objektiv EF 400mm f/2.8L IS II USM", 1101900 ) );
		list.add( new Article( "Canon Objektiv EF 85mm f/1.4L IS USM", 159900  ) );
		list.add( new Article( "Canon Objektiv EF 500mm f/4L IS II USM", 997900  ) );
		list.add( new Article( "Canon Objektiv EF 600mm f/4L IS II USM", 1263900  ) );
		list.add( new Article( "Canon Objektiv EF 70-300mm f/4-5.6 IS II USM", 53900 ) );
		list.add( new Article( "Canon Objektiv EF 70-200mm f/4L IS USM", 140900  ) );
		list.add( new Article( "Canon Objektiv EF 28-300mm f/3.5-5.6L IS USM", 265900  ) );
		list.add( new Article( "Canon Objektiv EF 100-400mm f/4.5-5.6L IS II USM ", 237900  ) );
		list.add( new Article( "Canon Objektiv EF 75-300mm f/4-5.6 III USM", 36900  ) );
		list.add( new Article( "Canon Objektiv EF 75-300mm f/4-5.6 III", 29900  ) );
		list.add( new Article( "Canon Objektiv EF 70-300mm f/4-5.6L IS USM", 142900  ) );
		list.add( new Article( "Canon Objektiv EF-S 55-250mm f/4-5.6 IS STM + ET 63 + LC Kit", 34900  ) );
		list.add( new Article( "Canon Objektiv EF-S 55-250mm f/4-5.6 IS STM", 34900  ) );
		list.add( new Article( "Canon Objektiv EF 70-200mm f/2.8L IS II USM", 229900 ) );
		list.add( new Article( "Canon Objektiv EF 70-200mm f/4L USM", 68900  ) );
		list.add( new Article( "Canon Objektiv EF 70-200mm f/2.8L USM", 157900  ) );
		list.add( new Article( "Canon Objektiv EF 200-400mm f/4L IS USM + Extender 1.4x", 1169900 ) );

		return list;
	}

}
