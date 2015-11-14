package net.ddns.buenaondalab.bch.dao;

import java.util.List;

public interface Dao {

	<E> E create(E e);

	<E> E update(E e);

	<E> void delete(Class<E> clazz, long id);

	<E> E findById(Class<E> clazz, long id);
	
	<E> List<E> findAll(Class<E> clazz);


}