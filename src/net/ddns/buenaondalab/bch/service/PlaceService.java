package net.ddns.buenaondalab.bch.service;

import java.util.List;

import javax.ejb.Local;

import net.ddns.buenaondalab.bch.model.City;
import net.ddns.buenaondalab.bch.model.Country;
import net.ddns.buenaondalab.bch.model.Place;
import net.ddns.buenaondalab.bch.model.Region;

@Local
public interface PlaceService {
	
	<E> E create(E e);

	<E> E update(E e);

	<E> void delete(Class<E> clazz, long id);

	<E> E findById(Class<E> clazz, long id);
	
	<E> List<E> findAll(Class<E> clazz);
	
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
	
	public List<Place> getPlacesByRegionName(String regName);
	
	
	/**
	 * Get all places in City c
	 * @param c the City
	 * @return
	 */
	public List<Place> getPlaces(City c);
	
	public List<Place> getPlacesByCityName(String cityName);

	public List<Place> getPlacesByCountryName(String country);

}
