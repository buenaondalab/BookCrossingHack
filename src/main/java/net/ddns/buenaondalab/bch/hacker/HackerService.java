package net.ddns.buenaondalab.bch.hacker;

import javax.ejb.Local;

@Local
public interface HackerService {

	void syncAll();

	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	void syncCountries();

	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	void syncRegions();

	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	void syncCities();
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	void syncPlaces();
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	void syncBooks();
	
	

}