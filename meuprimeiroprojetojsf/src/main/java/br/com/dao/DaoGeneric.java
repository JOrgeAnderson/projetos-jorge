package br.com.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;


@Named
public class DaoGeneric<E> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private JPAUtil jpaUtil;
	
	public void salvar(E entidade) {//INSERT

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
		entityManager.clear();
	}

	public E merge(E entidade) {//UPDATE

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		E retorno = entityManager.merge(entidade);

		transaction.commit();

		return retorno;
	}

	public void delete(E entidade) {//DELETE
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.remove(entidade);
		
		transaction.commit();
	
	}
	
	public void deletePorId(E entidade) {//DELETE (WHERE ID)

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Object id = jpaUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = "+ id).executeUpdate();

		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<E> getListEntity(Class<E> entidade){//SELECT FROM *
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<E> retorno = entityManager.createQuery("from "+ entidade.getName()).getResultList();
		
		transaction.commit();
		
		return retorno;
	}
	
	public E consultar(Class<E> entidade, String codigo) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		E objeto = (E) entityManager.find(entidade, Long.parseLong(codigo));
		transaction.commit();
		return objeto;
		
		
	}

}
