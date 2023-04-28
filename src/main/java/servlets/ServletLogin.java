package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

import dao.DAOLoginRepository;


@WebServlet(urlPatterns = {"/principal/ServletLogin1", "/ServletLogin1"}) /*Mapeamento da URL que vem da tela*/
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
       
    public ServletLogin() {
    }

    /*Recebe os dados pela URL em parametros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String acao = request.getParameter("acao");
		if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("ogout")) {
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		} else {
		doPost(request, response);
		}
	}

	/*Recebe os dados enviado por um formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
		
				if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
					
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);
				
				if(daoLoginRepository.validarAutenticacao(modelLogin)){	
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					
					if(url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				}else {
					RequestDispatcher redicenionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Informe o login e senha corretamente!");
					redicenionar.forward(request, response);
				}
				
				
				} else {
					RequestDispatcher redicenionar = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "Informe o login e senha corretamente!");
					redicenionar.forward(request, response);
				}
		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redicenionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redicenionar.forward(request, response);
		}
	}

}
