package com.br.jtech.project.assembleia.dao;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.br.jtech.project.assembleia.entidade.Login;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

public class DaoLogin<E> extends DaoGeneric<Login> implements Serializable{

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager = ConnectionUtil.getEntityManager();
	
	private DaoGeneric<Login> daoGeneric = new DaoGeneric<Login>();
	
public Long buscaUsuario(String login, String senha) {
		
		try {
			senha = cripografar(senha, "MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Long usuario = null;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
        	usuario = (Long) entityManager
                    .createQuery("select count(l) from Login l where l.usuario = '"+login+"' and l.senha = '"+senha+"'")
                    .getSingleResult();

        	transaction.commit();
        	
        	
        	
            return usuario;
        	
        	}
	
	public static String cripografar(String input, String tipoAlgoritmo) throws NoSuchAlgorithmException {
	    MessageDigest mDigest = MessageDigest.getInstance(tipoAlgoritmo);
	    byte[] result = mDigest.digest(input.getBytes());
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < result.length; i++) {
	        sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	    }

	    return sb.toString();
	}
	
	public void SalvarLogin(Login login) throws Exception {
		
		String senha = cripografar(login.getSenha(), "MD5");
		login.setSenha(senha);
		daoGeneric.salvar(login);
	}
	
	
}
