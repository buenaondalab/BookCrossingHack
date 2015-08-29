package net.ddns.buenaondalab.bch.daoImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import net.ddns.buenaondalab.bch.dao.RegionDao;
import net.ddns.buenaondalab.bch.model.Region;

@Stateless
@LocalBean
public class RegionDaoImpl extends DaoImpl implements RegionDao {

	@Override
	public List<Region> findByName(String name) {
		
		TypedQuery<Region> query = em.createNamedQuery("Region.findByName", Region.class);
		query.setParameter("name", name);
		List<Region> regions = query.getResultList();
		return regions;
	}

}
