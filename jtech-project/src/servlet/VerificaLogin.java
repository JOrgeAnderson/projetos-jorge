package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoLogin;
import model.Usuario;

@WebServlet("/verificaLogin")
public class VerificaLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoLogin daoLogin = new DaoLogin();

	public VerificaLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String user = request.getParameter("usuario");
			String senha = request.getParameter("senha");
			DaoLogin daoLogin = new DaoLogin();

			if (daoLogin.VerificaLogin(user, senha)) {

				Usuario usuario = new Usuario();

				if (daoLogin.verificaCategoria(user)) {

					HttpServletRequest req = (HttpServletRequest) request;
					HttpSession session = req.getSession();
					session.setAttribute("userLogado", usuario);

					out.println("<script type=\"text/javascript\">");
//					   out.println("alert('Bem-vindo ao Sistema da J-Tech');");
					out.println("location='condominioServlet?acao=listarcond';");
					out.println("</script>");
					
				}

				else if (!daoLogin.verificaCategoria(user)) {

					HttpServletRequest req = (HttpServletRequest) request;
					HttpSession session = req.getSession();
					session.setAttribute("userLogado", usuario);

					out.println("<script type=\"text/javascript\">");
					out.println("location='condominioServletFuncionario?acao=listarcondfuncionario';");
					out.println("</script>");
				

				}
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Usuário/E-mail ou senha inválida');");
				out.println("location='login.jsp';");
				out.println("</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
