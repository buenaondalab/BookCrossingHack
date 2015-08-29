package net.ddns.buenaondalab.bch.daoImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import net.ddns.buenaondalab.bch.dao.PlaceDao;
import net.ddns.buenaondalab.bch.model.City;
import net.ddns.buenaondalab.bch.model.Country;
import net.ddns.buenaondalab.bch.model.Place;
import net.ddns.buenaondalab.bch.model.Region;

@Stateless
@LocalBean
public class PlaceDaoImpl extends DaoImpl implements PlaceDao {

	@Override
	public List<Place> getPlaces(Country c) {
		TypedQuery<Place> query = em.createNamedQuery("Place.findByCountry", Place.class);
		query.setParameter("country", c);
		return query.getResultList();
	}

	@Override
	public List<Place> getPlaces(Region r) {
		TypedQuery<Place> query = em.createNamedQuery("Place.findByRegion", Place.class);
		query.setParameter("region", r);
		return query.getResultList();
	}

	@Override
	public List<Place> getPlaces(City c) {
		TypedQuery<Place> query = em.createNamedQuery("Place.findByCity", Place.class);
		query.setParameter("city", c);
		return query.getResultList();
	}
	
	

}
