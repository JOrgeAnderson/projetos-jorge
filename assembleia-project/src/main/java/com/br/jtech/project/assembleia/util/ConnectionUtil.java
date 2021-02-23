package com.br.jtech.project.assembleia.util;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static EntityManagerFactory factory = null;
	
	static {
		
		init();
	}
	
	private static void init() {
		
		try {
			
			if(factory == null) {
				factory = Persistence.createEntityManagerFactory("assembleia-project");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static EntityManager getEntityManager() {
		
		return factory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entity) {//retorna a primary key

		return factory.getPersistenceUnitUtil().getIdentifier(entity);
		
	}
}
