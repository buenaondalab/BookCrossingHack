package net.ddns.buenaondalab.bch.dao;

import java.util.List;

import javax.ejb.Local;

import net.ddns.buenaondalab.bch.model.City;
import net.ddns.buenaondalab.bch.model.Country;

@Local
public interface CityDao extends Dao {
	
	public List<City> findByName(String name);

	List<City> getCities(Country country);

}
