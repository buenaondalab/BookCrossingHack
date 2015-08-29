package net.ddns.buenaondalab.bch.serviceImpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.ddns.buenaondalab.bch.dao.CityDao;
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
	
	@Override
	public List<Place> getPlacesByCountryName(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Place> getPlacesByRegionName(String regName) {
		
		Region r = regDao.findByName(regName).get(0);		
		return placeDao.getPlaces(r);
	}
	
	//TODO: What about many cities with the same name?
	@Override
	public List<Place> getPlacesByCityName(String cityName) {
		City c = cityDao.findByName(cityName).get(0);
		return placeDao.getPlaces(c);
	}
	

	@Override
	public <E> E create(E e) {
		return placeDao.create(e);
	}

	@Override
	public <E> E update(E e) {
		return placeDao.update(e);
	}

	@Override
	public <E> void delete(Class<E> clazz, long id) {
		placeDao.delete(clazz, id);
		
	}

	@Override
	public <E> E findById(Class<E> clazz, long id) {
		return placeDao.findById(clazz, id);
	}

	@Override
	public <E> List<E> findAll(Class<E> clazz) {
		return placeDao.findAll(clazz);
	}

	

}
