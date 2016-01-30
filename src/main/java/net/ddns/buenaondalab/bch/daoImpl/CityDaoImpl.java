package net.ddns.buenaondalab.bch.daoImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import net.ddns.buenaondalab.bch.dao.CityDao;
import net.ddns.buenaondalab.bch.model.City;
import net.ddns.buenaondalab.bch.model.Country;

@Stateless
@LocalBean
public class CityDaoImpl extends DaoImpl implements CityDao {

	@Override
	public List<City> findByName(String name) {
		
		TypedQuery<City> query = em.createNamedQuery("City.findByName", City.class);
		query.setParameter("name", name);
		List<City> cities = query.getResultList();
		return cities;
	}
	
	@Override
	public List<City> getCities(Country country) {
		
		TypedQuery<City> query = em.createNamedQuery("City.findByCountry", City.class);
		query.setParameter("country", country);
		List<City> cities = query.getResultList();
		return cities;
	}

}
