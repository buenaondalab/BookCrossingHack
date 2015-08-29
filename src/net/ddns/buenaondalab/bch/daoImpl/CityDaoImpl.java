package net.ddns.buenaondalab.bch.daoImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import net.ddns.buenaondalab.bch.dao.CityDao;
import net.ddns.buenaondalab.bch.model.City;

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

}
