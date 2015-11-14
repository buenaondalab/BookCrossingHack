package net.ddns.buenaondalab.bch.daoImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ddns.buenaondalab.bch.dao.Dao;

/**
 * Simply maps the entitymanager.
 * It simplifies refactoring (unitName change) and wraps some logic (limited queries).
 *
 */
@Stateless
@LocalBean
public class DaoImpl implements Dao {
	
	protected Logger logger = LoggerFactory.getLogger(DaoImpl.class);
	
    @PersistenceContext(unitName = "BCHackDb")
    protected EntityManager em;

    /* (non-Javadoc)
	 * @see net.ddns.buenaondalab.bch.daoImpl.Dao#create(E)
	 */
    @Override
	public <E> E create(E e) {
        em.persist(e);
        return e;
    }

    /* (non-Javadoc)
	 * @see net.ddns.buenaondalab.bch.daoImpl.Dao#update(E)
	 */
    @Override
	public <E> E update(E e) {
        return em.merge(e);
    }

    /* (non-Javadoc)
	 * @see net.ddns.buenaondalab.bch.daoImpl.Dao#delete(java.lang.Class, long)
	 */
    @Override
	public <E> void delete(Class<E> clazz, long id) {
        em.remove(em.find(clazz, id));
    }

    /* (non-Javadoc)
	 * @see net.ddns.buenaondalab.bch.daoImpl.Dao#find(java.lang.Class, long)
	 */
    @Override
	public <E> E findById(Class<E> clazz, long id) {
        return em.find(clazz, id);
    }
    
    /**
     * @author andrea (BuenaOndaLab)
     */
    public <E> List<E> findAll(Class<E> clazz) {
    	TypedQuery<E> query = em.createQuery("SELECT c FROM " + clazz.getSimpleName() + " c", clazz);
    	logger.info(query.toString());
    	List<E> results = query.getResultList();
    	return results;
    }

}