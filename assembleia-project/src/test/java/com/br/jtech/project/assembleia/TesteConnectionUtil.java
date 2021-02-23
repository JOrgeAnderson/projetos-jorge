package com.br.jtech.project.assembleia;

import java.util.List;

import org.junit.Test;

import com.br.jtech.project.assembleia.dao.DaoGeneric;
import com.br.jtech.project.assembleia.dao.DaoLogin;
import com.br.jtech.project.assembleia.dao.DaoPessoa;
import com.br.jtech.project.assembleia.entidade.Login;
import com.br.jtech.project.assembleia.entidade.Pessoa;
import com.br.jtech.project.assembleia.entidade.TelefoneUser;

/**
 * Unit test for simple App.
 */
public class TesteConnectionUtil {
	
	private DaoPessoa<Pessoa> daoPessoa = new DaoPessoa<Pessoa>();
	
    @Test
    public void TesteConnection() {
    	
    	DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
    	
    	Pessoa pessoa = new Pessoa();
    	
    	pessoa.setCpf("8203982938");
    	pessoa.setEmail("Juju@gmail.com");
    	pessoa.setNome("Julia");
    	pessoa.setRg("2910928");
    	
    	if(daoPessoa.verificarUsuario(pessoa.getNome(), 
    				pessoa.getCpf(), 
    				pessoa.getRg(), 
    				pessoa.getEmail()) == 0) {
    		
    		daoGeneric.salvar(pessoa);
    	}else {
    		System.out.println("Usuario existente");
    	}
    	
    	
    	
	}
    
    @Test
    public void TesteInsetTelefone() {
    	
    	DaoGeneric<TelefoneUser> daoGeneric = new DaoGeneric<TelefoneUser>();
    	
    	TelefoneUser telefoneUser = new TelefoneUser();
    	Pessoa pessoa = new Pessoa();
    	pessoa.setId(3L);
    	telefoneUser.setNumero("9180798678");
    	telefoneUser.setPessoa(pessoa);
    	daoGeneric.salvar(telefoneUser);
    }
    
    @Test
    public void TesteInsertLogin() throws Exception {
    	DaoLogin<Login> daoGeneric = new DaoLogin<Login>();
    	
    	Login login = new Login();
    	
    	login.setUsuario("admin");
    	login.setSenha("12345");
//    	String senha = DaoLogin.cripografar(login.getSenha(), "MD5");
//    	login.setSenha(senha);
    	daoGeneric.SalvarLogin(login);
    	
    }
    
    @Test
	public void testeBuscar(){
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa.getNome());
		
	}
    
    @Test
	public void testeBuscar2(){
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		
		Pessoa pessoa = daoGeneric.pesquisar(1L, Pessoa.class);
		
		System.out.println(pessoa.getNome());
		
	}
    
    @Test
	public void testeUpdate(){
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		
		Pessoa pessoa = daoGeneric.pesquisar(1L , Pessoa.class);
		
		pessoa.setEmail("jojo@email.com");
		pessoa.setNome("Jorge Anderson");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa.getNome());
		
	}
    
    @Test
	public void testeDelete() throws Exception{
		
    	Pessoa pessoa = daoPessoa.pesquisar(3L , Pessoa.class);

    	daoPessoa.removerPessoa(pessoa);
//		
//		daoGeneric.deletarPoId(pessoa);
		
		
	}
    
    @Test
	public void testeConsultar(){
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
		
		List<Pessoa> list = daoGeneric.listar(Pessoa.class);
		
		for (Pessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa.getNome());
			System.out.println("--------------------------------------------------");
		}
		
		
	}
    
}
