package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoCondominio;
import model.Condominio;

/**
 * Servlet implementation class CondominioServletFuncionario
 */
@WebServlet("/condominioServletFuncionario")
public class CondominioServletFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DaoCondominio daoCondominio = new DaoCondominio();
	
    public CondominioServletFuncionario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			String acao = request.getParameter("acao");
			
		if (acao.equalsIgnoreCase("listarcondfuncionario")) {
			RequestDispatcher view = request.getRequestDispatcher("/funcionarioservice.jsp");
			request.setAttribute("condominios", daoCondominio.listarCondominio());
			view.forward(request, response);
			
			
		}
		else if (acao.equalsIgnoreCase("sair")) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.invalidate();
			response.sendRedirect("/login.jsp");

		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
//			String acao = request.getParameter("acao");

			Condominio condominio = new Condominio();
			condominio.setNome(nome);
			condominio.setTelefone(telefone);
			condominio.setCep(cep);
			condominio.setRua(rua);
			condominio.setBairro(bairro);
			condominio.setCidade(cidade);
			condominio.setEstado(estado);

			String msg = null;
			boolean podeInserir = true;

			if (nome == null || nome.isEmpty() || nome.trim() == " ") {
				msg = "Campo nome vazio";
				podeInserir = false;
			} else if (telefone == null || telefone.isEmpty() || telefone.trim() == " ") {
				msg = "campo telefone vazio";
				podeInserir = false;
			} else if (cep == null || cep.isEmpty() || cep.trim() == " ") {
				msg = "Campo cep vazio";
				podeInserir = false;
			} else if (rua == null || rua.isEmpty() || rua.trim() == " ") {
				msg = "Campo rua vazio";
				podeInserir = false;
			} else if (bairro == null || bairro.isEmpty() || bairro.trim() == " ") {
				msg = "Camppo bairro vazio";
				podeInserir = false;
			} else if (cidade == null || cidade.isEmpty() || cidade.trim() == " ") {
				msg = "Campo cidade vazio";
				podeInserir = false;
			} else if (estado == null || estado.isEmpty() || estado.trim() == " ") {
				msg = "Campo Estado Vazio";
				podeInserir = false;
			} else if (podeInserir) {
				daoCondominio.salvarCondominio(condominio);
				msg = "Salvo com sucesso!!";
			}

			if (msg != null) {
				request.setAttribute("msg", msg);
			}

			RequestDispatcher view = request.getRequestDispatcher("/funcionarioservice.jsp");
			request.setAttribute("condominios", daoCondominio.listarCondominio());
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	}
