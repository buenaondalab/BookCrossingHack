package net.ddns.buenaondalab.bch.serviceImpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.ddns.buenaondalab.bch.dao.Dao;
import net.ddns.buenaondalab.bch.service.Service;

@Stateless
@LocalBean
public class ServiceImpl implements Service {
	
	@EJB
	Dao dao;

	@Override
	public <E> E create(E e) {
		
		return dao.create(e);
	}

	@Override
	public <E> E update(E e) {
		
		return dao.update(e);
	}

	@Override
	public <E> void delete(Class<E> clazz, long id) {
		
		dao.delete(clazz, id);
	}

	@Override
	public <E> E findById(Class<E> clazz, long id) {
		
		return dao.findById(clazz, id);
	}

	@Override
	public <E> List<E> findAll(Class<E> clazz) {
		
		return dao.findAll(clazz);
	}

}
