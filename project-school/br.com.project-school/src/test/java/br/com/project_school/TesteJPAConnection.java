package br.com.project_school;

import org.junit.Test;

import br.com.project_school.connection.JPAConnection;

public class TesteJPAConnection {
	
	
	@Test
	public void TesteConnection() {
		JPAConnection.getEntityManager();
		
		
	}
	
}
