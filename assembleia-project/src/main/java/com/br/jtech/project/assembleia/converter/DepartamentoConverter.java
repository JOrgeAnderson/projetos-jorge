package com.br.jtech.project.assembleia.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.br.jtech.project.assembleia.entidade.Departamento;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

@FacesConverter(forClass = Departamento.class, value = "departamentoConverter")
public class DepartamentoConverter implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoDepartamento) {
		EntityManager entityManager = ConnectionUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Departamento departamento = (Departamento) entityManager.find(Departamento.class, Long.parseLong(codigoDepartamento));
		
		return departamento;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object departamento) {
		if(departamento == null) {
			
			return null;
		}if(departamento instanceof Departamento) {
			
			return ((Departamento) departamento).getId().toString();
		}else {
			return departamento.toString();
		}
	}

}
