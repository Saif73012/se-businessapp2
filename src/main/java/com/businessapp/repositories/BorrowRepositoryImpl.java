package com.businessapp.repositories;
import java.util.List;

import com.businessapp.model.Borrow;
class BorrowRepositoryImpl extends GenericMemRepositoryImpl<Borrow> implements BorrowRepositoryIntf {

	BorrowRepositoryImpl(List<Borrow> list)
	{
		super( list );
	}
	/**
	 * Create a new entity that is *not* yet managed in the repository.
	 * A new entity is added to the repository using the update() method.
	 * 
	 * @return new entity instance that is not yet managed in the repository.
	 */
	@Override
	public Borrow create() {
		return new Borrow( "","");
	}
	
}
