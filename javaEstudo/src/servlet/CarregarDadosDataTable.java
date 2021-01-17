package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUserLogado;
import entidade.UserLogado;

@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DaoUserLogado daoUserLogado = new DaoUserLogado();
	
    public CarregarDadosDataTable() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		
		List<UserLogado> logados = daoUserLogado.listar();
		
		if(!logados.isEmpty()) {
			
			String data = "";
			int totalUsuarios = logados.size();
			int index = 1;
			for (UserLogado userLogado : logados) {
				
				
				data += " [ "+
					    "\""+userLogado.getId()+"\","+
					    "\""+userLogado.getLogin()+"\""+
					    "]";
				if(index < totalUsuarios) {
					data += ",";
				}
				index ++;
			}
			
	String json = "{"+
			  "\"draw\": 1, "+
			  "\"recordsTotal\": "+logados.size()+","+
			  "\"recordsFiltered\": "+logados.size()+","+
			  "\"data\": [ "+ data + "]"+// fechamento do data
			"}";//fechamento do Json
		
		response.setStatus(200);//Resposta completa OK
		response.getWriter().write(json);//Json de resposta (escreve a resposta http)
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		response.setStatus(500);
	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
