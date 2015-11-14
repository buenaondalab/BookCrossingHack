package net.ddns.buenaondalab.bch.dao;

import java.util.List;

import javax.ejb.Local;

import net.ddns.buenaondalab.bch.model.Region;

@Local
public interface RegionDao extends Dao {
	
	public List<Region> findByName(String name);

}
