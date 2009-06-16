package com.quarks.c.service.transactionInterceptor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.quarks.c.service.IModelService;
import com.quarks.c.service.impl.AbstractServiceImpl;
import com.quarks.d.dao.GenericDAO;

@Aspect
public class TransactionInterceptor
{
//	@Pointcut("execution(* com.quarks.c.service.MessageService.*(..))")
//	public void messageService()
//	{}
//
//
//	@Before("messageService()")
//	public void beginTx( JoinPoint jp )
//	{
//		Object obj = jp.getTarget();
//
//		IModelService modelService = (IModelService)obj;
//
//		EntityManagerFactory emf = modelService.getEntityManagerFactory();
//
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		modelService.setEntityManager( em );
//
//		setEntityManagerForDAO( (AbstractServiceImpl)modelService,em );
//	}
//
//
//	@AfterThrowing("messageService()")
//	public void rollTx( JoinPoint jp )
//	{
//		Object obj = jp.getTarget();
//
//		IModelService modelService = (IModelService)obj;
//		EntityManager em = modelService.getEntityManager();
//
//		em.getTransaction().rollback();
//		em.close();
//	}
//
//	@AfterReturning("messageService()")
//	public void commitTx( JoinPoint jp )
//	{
//		Object obj = jp.getTarget();
//
//		IModelService modelService = (IModelService)obj;
//		EntityManager em = modelService.getEntityManager();
//
//		em.getTransaction().commit();
//		em.close();
//	}
//
//	@SuppressWarnings("unchecked")
//	private void setEntityManagerForDAO( AbstractServiceImpl abstractService,EntityManager em )
//	{
//		List<GenericDAO> daoList = abstractService.getDAO();
//		for( GenericDAO dao: daoList )
//			dao.setEntityManager( em );
//	}
}