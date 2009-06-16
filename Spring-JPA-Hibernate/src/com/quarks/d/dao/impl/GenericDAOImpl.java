package com.quarks.d.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.quarks.d.dao.GenericDAO;

/*
 * * Implements the generic CRUD data access operations using Java Persistence
 * APIs. <p> To write a DAO, subclass and parameterize this class with your
 * entity. Of course, assuming that you have a traditional 1:1 appraoch for
 * Entity:DAO design. This is actually an implementation that uses some
 * extensions for Java Persistence from Hibernate - you can see how the packages
 * for the extensions are not imported, but named inline.
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID>
{

	private Class<T>		entityBeanType;

	private EntityManager	em;

	public GenericDAOImpl()
	{
		this.entityBeanType = (Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass() )
				.getActualTypeArguments()[0];
	}

	// If this DAO is wired in as a Seam component, Seam injects the right
	// persistence context
	// if a method on this DAO is called. If the caller is a conversational
	// stateful component,
	// the persistence context will be scoped to the conversation, not to the
	// method call.
	// You can call this method and set the EntityManager manually, in an
	// integration test.

	@PersistenceContext(name = "QuarkFrameworkPU")
	public void setEntityManager(EntityManager em)
	{
		this.em = em;
	}

	public EntityManager getEntityManager()
	{
		if( em == null )
			throw new IllegalStateException( "EntityManager has not been set on DAO before usage" );
		return em;
	}

	/*
	 * *
	 * @return
	 * @uml.property name="entityBeanType"
	 */
	public Class<T> getEntityBeanType()
	{
		return entityBeanType;
	}

	public T findById(ID id, boolean lock)
	{
		T entity;
		if( lock )
		{
			entity = getEntityManager().find( getEntityBeanType(), id );
			em.lock( entity, javax.persistence.LockModeType.WRITE );
		}
		else
		{
			entity = getEntityManager().find( getEntityBeanType(), id );
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll()
	{
		return getEntityManager().createQuery( "from " + getEntityBeanType().getName() ).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty)
	{
		// Using Hibernate, more difficult with EntityManager and EJB-QL
		org.hibernate.Criteria crit = ( (org.hibernate.ejb.HibernateEntityManager) getEntityManager() ).getSession()
				.createCriteria( getEntityBeanType() );
		org.hibernate.criterion.Example example = org.hibernate.criterion.Example.create( exampleInstance );
		for( String exclude : excludeProperty )
		{
			example.excludeProperty( exclude );
		}
		crit.add( example );
		return crit.list();
	}

	public T makePersistent(T entity)
	{
		return getEntityManager().merge( entity );
	}

	public void makeTransient(T entity)
	{
		getEntityManager().remove( entity );
	}

	public void flush()
	{
		getEntityManager().flush();
	}

	public void clear()
	{
		getEntityManager().clear();
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(org.hibernate.criterion.Criterion... criterion)
	{
		// Using Hibernate, more difficult with EntityManager and EJB-QL
		org.hibernate.Session session = ( (org.hibernate.ejb.HibernateEntityManager) getEntityManager() ).getSession();
		org.hibernate.Criteria crit = session.createCriteria( getEntityBeanType() );
		for( org.hibernate.criterion.Criterion c : criterion )
		{
			crit.add( c );
		}
		return crit.list();
	}

}
