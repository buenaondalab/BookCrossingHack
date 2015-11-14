package net.ddns.buenaondalab.bch.serviceImpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.ddns.buenaondalab.bch.dao.CityDao;
import net.ddns.buenaondalab.bch.dao.CountryDao;
import net.ddns.buenaondalab.bch.dao.PlaceDao;
import net.ddns.buenaondalab.bch.dao.RegionDao;
import net.ddns.buenaondalab.bch.model.City;
import net.ddns.buenaondalab.bch.model.Country;
import net.ddns.buenaondalab.bch.model.Place;
import net.ddns.buenaondalab.bch.model.Region;
import net.ddns.buenaondalab.bch.service.PlaceService;

@Stateless
@LocalBean
public class PlaceServiceImpl implements PlaceService {

	@EJB
	PlaceDao placeDao;
	@EJB
	RegionDao regDao;
	@EJB
	CityDao cityDao;
	@EJB
	CountryDao countryDao;
	
	@Override
	public List<Place> getPlaces(Country c) {
		
		return placeDao.getPlaces(c);
	}

	@Override
	public List<Place> getPlaces(Region r) {
		
		return placeDao.getPlaces(r);
	}

	@Override
	public List<Place> getPlaces(City c) {
		
		return placeDao.getPlaces(c);
	}
	
	/**
	 * @param country is the country name
	 */
	@Override
	public List<Place> getPlacesByCountry(String country) {
		
		Country c = countryDao.findByName(country).get(0);
		return placeDao.getPlaces(c);
	}

	@Override
	public List<Place> getPlacesByRegion(String regName) {
		
		Region r = regDao.findByName(regName).get(0);		
		return placeDao.getPlaces(r);
	}
	
	//TODO: What about many cities with the same name? Actually only the first is retrieved
	@Override
	public List<Place> getPlacesByCity(String cityName) {
		City c = cityDao.findByName(cityName).get(0);
		return placeDao.getPlaces(c);
	}
	

	@Override
	public Place create(Place p) {
		return placeDao.create(p);
	}

	@Override
	public Place update(Place p) {
		return placeDao.update(p);
	}

	@Override
	public void delete(long id) {
		placeDao.delete(Place.class, id);
		
	}

	@Override
	public Place findById(long id) {
		return placeDao.findById(Place.class, id);
	}

	@Override
	public List<Place> findAll() {
		return placeDao.findAll(Place.class);
	}

	@Override
	public List<Place> getPlacesAround(Double radius, Double lat, Double lng) {
		return placeDao.findAround(radius, lat, lng);
	}

	

}
