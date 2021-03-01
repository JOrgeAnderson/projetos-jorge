package com.br.jtech.project.assembleia.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.br.jtech.project.assembleia.entidade.TelefoneUser;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

public class DaoTelefones<E> extends DaoGeneric<TelefoneUser> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager = ConnectionUtil.getEntityManager();
	
	public List<TelefoneUser> listarTelefone(Long idPessoa) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<TelefoneUser> lista = entityManager.createQuery("select t from TelefoneUser t where t.pessoa = "+idPessoa).getResultList();
		
		transaction.commit();
		
		return lista;
	}
}
