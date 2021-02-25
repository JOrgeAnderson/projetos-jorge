package com.br.jtech.project.assembleia.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.br.jtech.project.assembleia.entidade.Login;
import com.br.jtech.project.assembleia.util.ConnectionUtil;

@WebFilter(urlPatterns = "/*")
@ManagedBean
public class FilterAutenticacao implements Serializable, Filter{

	private static final long serialVersionUID = 1L;

	private ConnectionUtil connectionUtil;
	
	@SuppressWarnings("static-access")
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		connectionUtil.getEntityManager();
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Login usuarioLogado = (Login) session.getAttribute("usuarioLogado");

		String url = req.getServletPath();
		
			if(!url.equalsIgnoreCase("index.jsf") && usuarioLogado == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
				dispatcher.forward(request, response);
				return;
			}
				
		
		//executa as açõesdo request e response
		chain.doFilter(request, response);
		
	}
	
	@Override
	public void destroy() {
	}

}
