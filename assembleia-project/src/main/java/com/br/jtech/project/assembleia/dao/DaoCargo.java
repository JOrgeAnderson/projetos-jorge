package com.br.jtech.project.assembleia.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.br.jtech.project.assembleia.entidade.Cargo;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

public class DaoCargo<E>  extends DaoGeneric<Cargo> implements Serializable{

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager = ConnectionUtil.getEntityManager();
	
	public Long ValidarCargo(String nome) {
		
		Long cargo = null;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
        	cargo = (Long) entityManager
                    .createQuery("select count(c) from Cargo c where c.nome = '"+nome+"'").getSingleResult();

        	transaction.commit();
        	
            return cargo;
	}
}
