package br.com.project_school.connection;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnection implements Serializable{

	private static final long serialVersionUID = 1L;

	
	public static EntityManagerFactory factory = null;
	
	
	static {
		init();
	}
	
	
	private static void init() {
		
		try {
			
			if(factory == null) {
				factory = Persistence.createEntityManagerFactory("project-school");
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static EntityManager getEntityManager() {//retorna a conexao com o banco
		System.out.println("CONECTADO COM SUCESSO!");
		return factory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entity) {// Rteorna a primary key da entidade
		
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
		
	}
	
}
