package com.br.jtech.project.assembleia.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.br.jtech.project.assembleia.entidade.Cargo;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

public class DaoDepartamento<E>  extends DaoGeneric<Cargo> implements Serializable{

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager = ConnectionUtil.getEntityManager();
	
	public Long ValidarDepartamento(String nome, String centroDeCusto) {
		
		Long departamento = null;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
        	departamento = (Long) entityManager
                    .createQuery("select count(d) from Departamento d where d.nome = '"+nome+"'"
                    		+ "and d.centrodecusto = '"+centroDeCusto+"'").getSingleResult();

        	transaction.commit();
        	
            return departamento;
	}
}
