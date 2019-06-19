package com.businessapp.fxgui.fxml;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.List;

import com.businessapp.model.Note;
import com.businessapp.model.Borrow;
import com.businessapp.model.Borrow.ArticleStatus;
import com.businessapp.model.Customer.CustomerStatus;
import com.businessapp.repositories.BorrowRepositoryIntf;

public class TableViewable_Borrow extends TableViewable {
	public final static String ContactSeparator = "; ";

	// Rental object properties.
	private final BorrowRepositoryIntf repository;
	private final Borrow entity;

	// RentalRepository properties.
	private final ObservableList<TableViewable> tvItemsList;
	private final TableViewFXMLController tvFxmlController;

	/*
	 * TableView description information.
	 */
	private static final String tabLabel = "Borrow/Ausleihe";
	private static final String cssPrefix = "tableview-Borrow";

	// Enumerate Rental columns in Tableview.
	private enum Col { id, userID, articleID, Ausleihdatum, Tage, status };

	private static final String[][] _colDescr = {
		// i_col=0,			i_label=1,	i_css=2,				i_visble=3, i_edble=4
		{ Col.id.name(),	"Verleih-Nr..",cssPrefix + "-column-id",		"1", "0" },
		{ Col.userID.name(),	"UserID",		cssPrefix + "-column-userID",		"1", "1" },
		{ Col.articleID.name(),	"ArticleID",		cssPrefix + "-column-articleID",		"1", "1" },
		{ Col.Ausleihdatum.name(),"Datum",	cssPrefix + "-column-Ausleihdatum",	"1", "1" },
		{ Col.Tage.name(),	"Tage.",	cssPrefix + "-column-Tage",	"0", "0" },
		{Col.status.name(),"Status", cssPrefix+"-column-status","1","1"}
	};

	@Override
	Object[][] getColDescr() {
		return _colDescr;
	}

	/*
	 * Defined CSS style classes:
	 *   .tableview-borrow-column-id
	 *   .tableview-borrow-column-name
	 *   .tableview-borrow-column-status
	 *   .tableview-borrow-column-notes
	 *   .tableview-borrow-column-notes-button
	 *   .tableview-borrow-column-contacts
	 *   .tableview-hbox
	 */

	/*
	 * Public constructor for RentalRepositoryDAO instance.
	 */
	public TableViewable_Borrow( TableViewFXMLController tvFxmlController, BorrowRepositoryIntf borrowRepo ) {
		this.repository = borrowRepo;
		this.entity = null;		// null indicates RentalRepositoryDAO instance.		
		this.tvItemsList = FXCollections.observableArrayList();
		this.tvFxmlController = tvFxmlController;
	}

	/*
	 * Public constructor for individual RentalDAO instances.
	 */
	private TableViewable_Borrow( TableViewFXMLController tvFxmlController, BorrowRepositoryIntf borrowRepo,
			ObservableList<TableViewable> tvItemsList, Borrow e )
	{
		this.repository = borrowRepo;
		this.entity = e;
		this.tvItemsList = tvItemsList;
		this.tvFxmlController = tvFxmlController;
	}


	/*
	 * Managebility methods.
	 */
	@Override
	public void start() {
		reload();
	}

	@Override
	public void stop() {
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	private void reload() {
		tvItemsList.clear();
		for( Borrow entity : repository.findAll() ) {
			TableViewable_Borrow tvc = new TableViewable_Borrow( tvFxmlController, repository, tvItemsList, entity );
			tvItemsList.add( tvc );
		}
		tvFxmlController.reloadData();
	}

	/*
	 * CRUD-methods used to manipulate underlying data (list).
	 */
	@Override
	public TableViewable createInstance() {
		Borrow entity = repository.create();
		TableViewable tvEntity = new TableViewable_Borrow( tvFxmlController, repository, tvItemsList, entity );
		return tvEntity;
	}

	@Override
	public String getId() {
		return entity.getId();
	}

	@Override
	public void importFormData( HashMap<String,String> formData ) {

		if( formData != null ) {

			for( String key : formData.keySet() ) {

				String val = formData.get( key );
				//System.out.println( "==> updated(" + cx.getId() + "): " + key + ", v: " + val );
				switch( Col.valueOf( key ) ) {
				//case id:	//id's can't be updated.

				case userID:
					entity.setUserID( val );
					break;
					
				case articleID:
					entity.setArticleID( val );
					break;

				case Ausleihdatum:
					entity.setAusleihedatum( val );
					break;

				case Tage:
					entity.setRückgabedatum( Integer.parseInt( val ));
					break;
				case status:
					String st=val.toLowerCase();
					entity.SetStatus( st.startsWith( "ava" )? ArticleStatus.AVAILABLE :
						st.startsWith( "loa" )? ArticleStatus.LOAN : 
						st.startsWith("res")? ArticleStatus.RESERVED:
						ArticleStatus.UNDERCLEANING
					);
					break;
				}
			}
			repository.update( entity, true );
			reload();
		}
	}

	@Override
	public void delete( List<String> ids ) {
		repository.delete( ids );
		reload();
	}

	@Override
	public ObservableList<TableViewable> getData() {
		return tvItemsList;
	}


	/*
	 * Table description methods used to construct TableView.
	 */
	@Override
	public String getTabLabel() {
		return tabLabel;
	}

	@Override
	public String getCellValueAsString( int col ) {
		String ret = "-";
		switch( Col.valueOf( getColName( col ) ) ) {
		case id:	return entity.getId();
		case userID:	return entity.getCustomer();
		case articleID:	return entity.getArticle();
		case Ausleihdatum: return entity.getAusleihdatum();
		case Tage: return Integer.toString(entity.getRückgabedatum());
		case status:
			ArticleStatus st = entity.getStatus();
			switch( st ) {
			case AVAILABLE: ret = "AVA"; break;
			case LOAN: ret = "LOAN"; break;
			case RESERVED: ret= "RES"; break;
			case UNDERCLEANING: ret="UC";break;
			default: ret = st.name();
			}
			return ret;
		}
		return ret;
	}

	@Override
	public String getCellValue( int col ) {
		return getCellValueAsString( col );
	}

	@Override
	public Callback<TableColumn<TableViewable, String>, TableCell<TableViewable, String>> getCellFactory( int col ) {
		return null;
	}
}

