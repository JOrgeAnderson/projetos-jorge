package com.br.jtech.project.assembleia.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.br.jtech.project.assembleia.dao.DaoPessoa;
import com.br.jtech.project.assembleia.dao.DaoTelefones;
import com.br.jtech.project.assembleia.entidade.Pessoa;
import com.br.jtech.project.assembleia.entidade.TelefoneUser;

@ManagedBean(name = "telefoneBean")
@ViewScoped
public class TelefoneBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa = new Pessoa();
	private DaoPessoa<Pessoa> daoPessoa = new DaoPessoa<Pessoa>();
	private DaoTelefones<TelefoneUser> daoTelefones = new DaoTelefones<TelefoneUser>();
	private TelefoneUser telefone = new TelefoneUser();
	
	@PostConstruct
	public void init() {
		
		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigouser");
		pessoa = daoPessoa.pesquisar(Long.parseLong(coduser), Pessoa.class);
	}
	
	public String salvar() {
		telefone.setPessoa(pessoa);
		daoTelefones.salvar(telefone);
		pessoa = daoPessoa.pesquisar(pessoa.getId(), Pessoa.class);
		telefone = new TelefoneUser();
		daoTelefones.listarTelefone(pessoa.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ", "Salvo com sucesso!"));
		return "";
	}
	
	public String removeTelefone() throws Exception {
		
		daoTelefones.deletarPoId(telefone);
		pessoa = daoPessoa.pesquisar(pessoa.getId(), Pessoa.class);
		telefone = new TelefoneUser();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ", "Telefone Removido!"));
		
		return "";
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public DaoPessoa<Pessoa> getDaoPessoa() {
		return daoPessoa;
	}
	public void setDaoPessoa(DaoPessoa<Pessoa> daoPessoa) {
		this.daoPessoa = daoPessoa;
	}
	public DaoTelefones<TelefoneUser> getDaoTelefones() {
		return daoTelefones;
	}
	public void setDaoTelefones(DaoTelefones<TelefoneUser> daoTelefones) {
		this.daoTelefones = daoTelefones;
	}
	public TelefoneUser getTelefone() {
		return telefone;
	}
	public void setTelefone(TelefoneUser telefone) {
		this.telefone = telefone;
	}
	
}
