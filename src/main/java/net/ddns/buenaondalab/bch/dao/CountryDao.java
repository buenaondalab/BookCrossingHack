package net.ddns.buenaondalab.bch.dao;

import java.util.List;

import javax.ejb.Local;

import net.ddns.buenaondalab.bch.model.Country;

@Local
public interface CountryDao extends Dao {
	
	public List<Country> findByName(String name);

}
