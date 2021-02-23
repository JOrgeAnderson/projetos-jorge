package com.br.jtech.project.assembleia.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.br.jtech.project.assembleia.entidade.Pessoa;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

public class DaoPessoa<E> extends DaoGeneric<Pessoa> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager = ConnectionUtil.getEntityManager();

	public void removerPessoa(Pessoa pessoa) throws Exception{
		entityManager.getTransaction().begin();
		entityManager.createQuery("delete from TelefoneUser t where t.pessoa = "+ pessoa.getId()).executeUpdate();
		entityManager.getTransaction().commit();
		
		super.deletarPoId(pessoa);
		
	}
	
	public Long verificarUsuario(String nome, String cpf, String rg, String email) {
		
		Long pessoas = null;
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		pessoas = (Long) entityManager.createQuery("select count(0) from Pessoa p where "
				+ "p.nome = '"+nome+"' or p.cpf = '"+cpf+"' or p.rg = '"+rg+"' or p.email = '"+email+"'").getSingleResult();
		
		transaction.commit();
		
		
		return pessoas;
		
	}
	
}
