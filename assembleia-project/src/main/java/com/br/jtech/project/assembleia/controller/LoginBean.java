package com.br.jtech.project.assembleia.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import com.br.jtech.project.assembleia.dao.DaoLogin;
import com.br.jtech.project.assembleia.entidade.Login;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

@ViewScoped
@ManagedBean(name = "loginBean")
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ConnectionUtil connectionUtil;
	
	private Login login = new Login();
	
	private DaoLogin<Login> daoLogin = new DaoLogin<Login>();
	
	
	public String logar() {

		if (daoLogin.buscaUsuario(login.getUsuario(), login.getSenha()) != 0) {

			// adicionar o usuário na sessão usuarioLogado
						FacesContext context = FacesContext.getCurrentInstance();
						ExternalContext externalContext = context.getExternalContext();
						externalContext.getSessionMap().put("usuarioLogado", login);
			
			return "logado.jsf";

		} else {

			return "index.jsf";
		}

	}
	
	
	public String deslogar() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");

		@SuppressWarnings("static-access")
		HttpServletRequest httpServletRequest = (HttpServletRequest) context.getCurrentInstance().getExternalContext()
				.getRequest();

		httpServletRequest.getSession().invalidate();

		return "index.jsf";
	}


	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public DaoLogin<Login> getDaoLogin() {
		return daoLogin;
	}


	public void setDaoLogin(DaoLogin<Login> daoLogin) {
		this.daoLogin = daoLogin;
	}


	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}


	public void setConnectionUtil(ConnectionUtil connectionUtil) {
		this.connectionUtil = connectionUtil;
	}
	
}