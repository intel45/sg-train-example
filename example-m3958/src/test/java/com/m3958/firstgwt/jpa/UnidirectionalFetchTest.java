package com.m3958.firstgwt.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.m3958.firstgwt.model.Asset;



public class UnidirectionalFetchTest {
	
	private static final String PERSISTENCE_UNIT_NAME = "p-unit";
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	
	@Before
	public void create(){
		EntityManager em = factory.createEntityManager();
		em.close();
	}
	

	@Test
	public void testMe(){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//1、manyside类型，oneside属性，oneside类型。
		String qs = "SELECT a FROM Lgb AS l, IN(l.attachments) AS a WHERE a.fileSize > 55 ORDER BY a.createdAt DESC ";
		Query q = em.createQuery(qs);
		List<Asset> as = q.getResultList();
		
		em.getTransaction().commit();
		em.close();

	}
	
	@After
	public void destroy(){
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();

	}
}