package net.ddns.buenaondalab.bch.service;

import java.util.List;

import javax.ejb.Local;

@Local
public interface Service {
	
	<E> E create(E e);

	<E> E update(E e);

	<E> void delete(Class<E> clazz, long id);

	<E> E findById(Class<E> clazz, long id);
	
	<E> List<E> findAll(Class<E> clazz);

}
