package filter;

import java.io.IOException;
import java.sql.Connection;

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

import connectionjdbc.SingleConnection;
import model.Usuario;

@WebFilter(urlPatterns = {"/*"})
public class FilterAutenticacao implements Filter{
	
	@SuppressWarnings("unused")
	private static Connection connection;
	
	@Override
		public void destroy() {
		}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpSession session = servletRequest.getSession();
		
		String urlParaAutenicar = servletRequest.getServletPath();
		
		Usuario usuario = (Usuario) session.getAttribute("userLogado");
		
		if(usuario == null && !urlParaAutenicar.equalsIgnoreCase("/verificaLogin")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		connection = SingleConnection.getConnection();
	}
	
}
