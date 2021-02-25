package com.br.jtech.project.assembleia.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import com.br.jtech.project.assembleia.dao.DaoPessoa;
import com.br.jtech.project.assembleia.dao.DaoTelefones;
import com.br.jtech.project.assembleia.entidade.Pessoa;
import com.br.jtech.project.assembleia.entidade.TelefoneUser;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private ConnectionUtil connectionUtil;
	
	private Pessoa pessoa = new Pessoa();
	
	private DaoPessoa<Pessoa> daoGeneric = new DaoPessoa<Pessoa>();
	
	private DaoTelefones<TelefoneUser> daoTelefone = new DaoTelefones<TelefoneUser>();
	
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();


public String salvar() throws IOException{
		
		
		
		if(daoGeneric.verificarUsuario(pessoa.getNome(), pessoa.getCpf(), pessoa.getRg(), pessoa.getEmail()) == 0) {
		
				daoGeneric.salvar(pessoa);
				pessoa = new Pessoa();
				carregarPessoas();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ", "Salvo com sucesso"));
			
		return "";
		
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ", "Usuário já existente!"));
			return "";
		}

	}

	public String novo() {

		pessoa = new Pessoa();

		return "";
	}

	public String limpar() {

		pessoa = new Pessoa();

		return "";
	}

	public String remove() throws Exception {
		daoGeneric.deletarPoId(pessoa);
		pessoa = new Pessoa();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ", "Usuário removido com sucesso!"));
//		carregarPessoas();
//		mostrarMsg("Removido com sucesso!");
		return "";
	}

	
	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.listar(Pessoa.class);

	}
	
	public List<SelectItem> getCargos(){
		
		return daoGeneric.listaCargos();
	}

	public List<SelectItem> getDepartamentos(){
		
		return daoGeneric.listaDepartamentos();
	}
	
	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}

	public void setConnectionUtil(ConnectionUtil connectionUtil) {
		this.connectionUtil = connectionUtil;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoPessoa<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoPessoa<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public DaoTelefones<TelefoneUser> getDaoTelefone() {
		return daoTelefone;
	}

	public void setDaoTelefone(DaoTelefones<TelefoneUser> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}
	
}
