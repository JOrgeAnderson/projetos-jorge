package com.br.jtech.project.assembleia.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.br.jtech.project.assembleia.entidade.Departamento;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

public class DaoDepartamento<E>  extends DaoGeneric<Departamento> implements Serializable{

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager = ConnectionUtil.getEntityManager();
	
	
	public Long ValidarDepartamento(String nome, String centroDeCusto) {
		
		Long departamento = null;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
        	departamento = (Long) entityManager
                    .createQuery("select count(d) from Departamento d where d.nome = '"+nome+"'"
                    		+ "or d.centroDeCusto = '"+centroDeCusto+"'").getSingleResult();

        	transaction.commit();
        	
            return departamento;
	}

	public List<Departamento> pesquisar(String campopesquisa) {
		
		Query query = super.getEntityManager().createQuery("from Departamento where nome like '%"+campopesquisa+"%'");
		
		return query.getResultList();
	}
	
	
}
