package net.ddns.buenaondalab.bch.daoImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import net.ddns.buenaondalab.bch.dao.CountryDao;
import net.ddns.buenaondalab.bch.model.Country;

@Stateless
@LocalBean
public class CountryDaoImpl extends DaoImpl implements CountryDao {

	@Override
	public List<Country> findByName(String name) {
		
		TypedQuery<Country> query = em.createNamedQuery("Country.findByName", Country.class);
		query.setParameter("name", name);
		List<Country> countries = query.getResultList();
		return countries;
	}

}
