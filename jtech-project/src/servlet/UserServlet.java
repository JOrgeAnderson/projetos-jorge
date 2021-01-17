package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import model.Usuario;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoUsuario daoUsuario = new DaoUsuario();

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
			
			if (acao.equalsIgnoreCase("listarusuarios")) {

				RequestDispatcher dispatcher = request.getRequestDispatcher("/listadeusuarios.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
				dispatcher.forward(request, response);
			}

			else if (acao.equalsIgnoreCase("editaruser")) {

				Usuario usuario = daoUsuario.consultarUsuario(user);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/atualizardadosusuario.jsp");
				request.setAttribute("user", usuario);
				dispatcher.forward(request, response);
			}
			
			else if(acao != null && acao.equalsIgnoreCase("excluir") && user != null) {
				
				daoUsuario.deletarUsuario(user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/listadeusuarios.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String login = request.getParameter("login");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String categoria = request.getParameter("categoria");

			Usuario usuario = new Usuario();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setNome(nome);
			usuario.setLogin(login);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setCategoria(categoria);

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			boolean podeInserir = true;

			if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Usuário já existe');");
				out.println("location='cadastrouser.jsp';");
				out.println("</script>");
				podeInserir = false;
			} else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
				daoUsuario.salvarUsuario(usuario);

				out.println("<script type=\"text/javascript\">");
				out.println("alert('Usuário salvo com sucesso');");
				out.println("location='cadastrouser.jsp';");
				out.println("</script>");

			}
			if (id != null && !id.isEmpty()) {
				if (!daoUsuario.validarUpdateLogin(login, id)) {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Login existente.');");
					out.println("</script>");
				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoUsuario.atualizarUsuario(usuario);
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Dados Atualizados');");
					out.println("location='userServlet?acao=listarusuarios';");
					out.println("</script>");

				}

			}

			if (!podeInserir) {
				request.setAttribute("user", usuario);
			}

			out.println("<script type=\"text/javascript\">");
			out.println("location='userServlet?acao=listarusuarios';");
			out.println("</script>");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
