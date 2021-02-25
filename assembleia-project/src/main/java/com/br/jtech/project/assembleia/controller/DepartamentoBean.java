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

import com.br.jtech.project.assembleia.dao.DaoDepartamento;
import com.br.jtech.project.assembleia.entidade.Departamento;

@ViewScoped
@ManagedBean(name = "departamentoBean")
public class DepartamentoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Departamento departamento = new Departamento();
	
	private DaoDepartamento<Departamento> daoGeneric = new DaoDepartamento<Departamento>();
	
	private List<Departamento> departamentos = new ArrayList<Departamento>();
	
	public String salvar() throws IOException{
		
		if(daoGeneric.ValidarDepartamento(departamento.getNome(), departamento.getCentroDeCusto()) == 0) {
		
		daoGeneric.salvar(departamento);
		departamento = new Departamento();
//		carregarPessoas();
//		mostrarMsg("Salvo com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ", "Departamento salvo com sucesso!"));
		return "";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ", "Departamento ou Centro de custo já existe!"));
			return "";
		}
}

public String atualizar() throws IOException {

departamento = daoGeneric.updateMerge(departamento);
departamento = new Departamento();
//carregarPessoas();
//mostrarMsg("Salvo com sucesso!");

return "";

}

public String novo() {

departamento = new Departamento();

return "";
}

public String limpar() {

departamento = new Departamento();

return "";
}

public String remove() throws Exception {
daoGeneric.deletarPoId(departamento);
departamento = new Departamento();
//carregarPessoas();
//mostrarMsg("Removido com sucesso!");
return "";
}

//private void mostrarMsg(String msg) {
//FacesContext context = FacesContext.getCurrentInstance();
//FacesMessage message = new FacesMessage(msg);
//context.addMessage(null, message);
//
//}
@PostConstruct
public void carregarDepartamento() {
departamentos = daoGeneric.listar(Departamento.class);

}

public Departamento getDepartamento() {
	return departamento;
}

public void setDepartamento(Departamento departamento) {
	this.departamento = departamento;
}

public DaoDepartamento<Departamento> getDaoGeneric() {
	return daoGeneric;
}

public void setDaoGeneric(DaoDepartamento<Departamento> daoGeneric) {
	this.daoGeneric = daoGeneric;
}

public List<Departamento> getDepartamentos() {
	return departamentos;
}

public void setDepartamentos(List<Departamento> departamentos) {
	this.departamentos = departamentos;
}

}
