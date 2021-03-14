package com.br.jtech.project.assembleia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.br.jtech.project.assembleia.dao.DaoDepartamento;
import com.br.jtech.project.assembleia.entidade.Departamento;

public class LazyDataTableModelDepartamento<T> extends LazyDataModel<Departamento>{

	private DaoDepartamento<Departamento> dao = new DaoDepartamento<Departamento>();
	
	private List<Departamento> list = new ArrayList<Departamento>();
	
	private String sql = " from Departamento";
	
	@Override
	public List<Departamento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		list = dao.getEntityManager().createQuery(getSql()).setFirstResult(first).setMaxResults(pageSize).getResultList();
	
		sql = " from Departamento ";
		
		setPageSize(pageSize);
		Integer qtdRegistro = Integer.parseInt(dao.getEntityManager().createQuery("select count(1) "+getSql()).getSingleResult().toString());
		setRowCount(qtdRegistro);
		
		return list;
		

	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public void pesquisa(String campopesquisa) {
		
		sql += " where nome like '%"+campopesquisa+"%'";
	}
}
