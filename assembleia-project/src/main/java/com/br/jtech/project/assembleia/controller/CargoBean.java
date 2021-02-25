package com.br.jtech.project.assembleia.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.br.jtech.project.assembleia.dao.DaoCargo;
import com.br.jtech.project.assembleia.entidade.Cargo;

@ViewScoped
@ManagedBean(name = "cargoBean")
public class CargoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Cargo cargo = new Cargo();
	
	private DaoCargo<Cargo> daoGeneric = new DaoCargo<Cargo>();
	
	private List<Cargo> cargos = new ArrayList<Cargo>();
	
	
public String salvar() throws IOException {

		

		if (daoGeneric.ValidarCargo(cargo.getNome()) == 0) {
			daoGeneric.salvar(cargo);
			cargo = new Cargo();
//				carregarPessoas();
//				mostrarMsg("Salvo com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ", "Cargo salvo com sucesso!"));
			return "";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ", "Cargo já exiiste!"));
			return "";

		}
	}

	public String atualizar() throws IOException {

		cargo = daoGeneric.updateMerge(cargo);
		cargo = new Cargo();
//		carregarPessoas();
//		mostrarMsg("Salvo com sucesso!");

		return "";

	}

	public String novo() {

		cargo = new Cargo();

		return "";
	}

	public String limpar() {

		cargo = new Cargo();

		return "";
	}

	public String remove() throws Exception {
		daoGeneric.deletarPoId(cargo);
		cargo = new Cargo();
//		carregarPessoas();
//		mostrarMsg("Removido com sucesso!");
		return "";
	}

	@PostConstruct
	public void carregarCargos() {
		cargos = daoGeneric.listar(Cargo.class);

	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public DaoCargo<Cargo> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoCargo<Cargo> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
}
