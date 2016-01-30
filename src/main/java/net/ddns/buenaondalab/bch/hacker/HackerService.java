package net.ddns.buenaondalab.bch.hacker;

import javax.ejb.Local;

import net.ddns.buenaondalab.bch.model.Country;
import net.ddns.buenaondalab.bch.service.Service;

@Local
public interface HackerService extends Service {

	void syncAll();

	void syncCountries();

	void syncRegions();
	
	void syncRegions(Country country);

	void syncCities();
	
	void syncCities(Country country);
	
	void syncPlaces();
	
	void syncPlaces(Country country);
	
	void syncBooks();
	
	void syncBooks(Country country);

	
	

}