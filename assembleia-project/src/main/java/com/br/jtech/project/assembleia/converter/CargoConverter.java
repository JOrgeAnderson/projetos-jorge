package com.br.jtech.project.assembleia.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.br.jtech.project.assembleia.entidade.Cargo;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

@FacesConverter(forClass = Cargo.class, value = "cargoConverter")
public class CargoConverter implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCargo) {
		EntityManager entityManager = ConnectionUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Cargo cargo = (Cargo) entityManager.find(Cargo.class, Long.parseLong(codigoCargo));
		
		return cargo;
		
		
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cargo) {
		if(cargo == null) {
			
			return null;
		}if(cargo instanceof Cargo) {
			
			return ((Cargo) cargo).getId().toString();
		}else {
			return cargo.toString();
		}
	}

}
