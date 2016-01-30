package net.ddns.buenaondalab.bch.service;

import java.util.List;

import javax.ejb.Local;

import net.ddns.buenaondalab.bch.model.City;
import net.ddns.buenaondalab.bch.model.Country;
import net.ddns.buenaondalab.bch.model.Place;
import net.ddns.buenaondalab.bch.model.Region;

@Local
public interface PlaceService extends Service {
	
	/**
	 * Get all places in Country c
	 * @param c the Country
	 * @return
	 */
	public List<Place> getPlaces(Country c);
	
	public List<Place> getPlacesByCountry(String country);
	
	/**
	 * Get all places in Region r
	 * @param r the Region
	 * @return
	 */
	public List<Place> getPlaces(Region r);
	
	public List<Place> getPlacesByRegion(String regName);
	
	
	/**
	 * Get all places in City c
	 * @param c the City
	 * @return
	 */
	public List<Place> getPlaces(City c);
	
	public List<Place> getPlacesByCity(String cityName);

	public List<Place> getPlacesAround(Double north, Double east, Double south, Double west);


}
