package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"})/*Intercepta todas as requisiçoes que vierem do projeto ou mapeamento*/
public class FilterAutenticacao extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private static Connection connection;
       
    public FilterAutenticacao() {
    }

    /*Encerra os processo quando o servidor é parado*/
    //Mataria os processo de conexao com banco
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*Intercepta as requisiçoes e as respostas no sistemas*/
	/*Tudo que fizer no sistema vai passar por aqui*/
	/*Validação de autenticação*/
	//Dar commit e rollback de transaçoes do banco
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		try {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath();/*Url que está sendo acessada*/
		
		/*Validar se está logado senao redireciona para a tela de login*/
		if(usuarioLogado == null  &&
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin1")) {/*Não está logado*/
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login!");
			redireciona.forward(request, response);
			return;/*Para a execução e redireciona para o login*/
		}else {
			chain.doFilter(request, response);
		}
		connection.commit();
	}catch (Exception e) {
		e.printStackTrace();
		RequestDispatcher redicenionar = request.getRequestDispatcher("erro.jsp");
		request.setAttribute("msg", e.getMessage());
		redicenionar.forward(request, response);
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	}

	/*Inicias os processos ou recursos quando o servidor sobe o projeto*/
	//inicia a conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
