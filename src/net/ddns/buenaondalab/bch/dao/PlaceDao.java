package net.ddns.buenaondalab.bch.dao;

import java.util.List;

import javax.ejb.Local;

import net.ddns.buenaondalab.bch.model.City;
import net.ddns.buenaondalab.bch.model.Country;
import net.ddns.buenaondalab.bch.model.Place;
import net.ddns.buenaondalab.bch.model.Region;

@Local
public interface PlaceDao extends Dao {
	
	
	/**
	 * Get all places in Country c
	 * @param c the Country
	 * @return
	 */
	public List<Place> getPlaces(Country c);
	
	/**
	 * Get all places in Region r
	 * @param r the Region
	 * @return
	 */
	public List<Place> getPlaces(Region r);
	
	/**
	 * Get all places in City c
	 * @param c the City
	 * @return
	 */
	public List<Place> getPlaces(City c);

}
