package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoCalculaDataFinal;

@WebServlet("/pages/calcularDataFinal")
public class CalcularDataFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		DaoCalculaDataFinal dataFinal = new DaoCalculaDataFinal();
	
    public CalcularDataFinal() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*08:00-12:00 e 13:00 e 17:30*/
		/*1 dia é igual a 8 horas*/
		
		try {
			int horaDia = 8;
			Date dataCaculada = null;
			Double totalDeDia = 0.0;
			
			String data = request.getParameter("data");
			int tempo = Integer.parseInt(request.getParameter("tempo"));
			
			if(tempo <= horaDia) {// mesmo dia
				
				Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				Calendar calendar = Calendar.getInstance();
				
				calendar.setTime(dateInformada);
				calendar.add(Calendar.DATE, 1);
				dataCaculada = calendar.getTime();
				totalDeDia = 1.0;
				
			}else {
				
				totalDeDia = (double) (tempo/horaDia);
				
				if(totalDeDia < 1) {
					dataCaculada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				}else {
					Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
					Calendar calendar = Calendar.getInstance();
					
					calendar.setTime(dateInformada);
					calendar.add(Calendar.DATE, totalDeDia.intValue());
					dataCaculada = calendar.getTime();
				}
			}
			
			dataFinal.gravaDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(dataCaculada));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/datas.jsp");
			request.setAttribute("datafinal", new SimpleDateFormat("dd/MM/yyyy").format(dataCaculada));
			request.setAttribute("dias", totalDeDia);
			dispatcher.forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
