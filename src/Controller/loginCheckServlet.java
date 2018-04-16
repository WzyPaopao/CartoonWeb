package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.userService;

/**
 * Servlet implementation class loginCheckServlet
 */
@WebServlet("/loginCheckServlet")
public class loginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = new String("");
		String password = new String("");
		HttpSession session = request.getSession();
		
		/*if( !userName.equals("") ){
			userName = request.getParameter("userName");
			password = request.getParameter("password");
			session.setAttribute("userName", userName);
		}
		else if( userName.equals("") ){
			userName = (String)session.getAttribute("userName");
			password = (String)session.getAttribute("password");
		}*/
		
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		session.setAttribute("userName", userName);
		
		Boolean flag;
		flag = userService.loginCheck(userName, password);
		//flag = false;
		
		if( flag ){
			session.setAttribute("loginStatus", true);
			response.sendRedirect("./topBookServlet");
			//response.sendRedirect("topBookServlet");
		}//Of if -------the password is right, go to the index page
		else{
			response.sendRedirect("LoginPage.jsp");
		}//Of else --------------the password is not right, refresh the login page
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
