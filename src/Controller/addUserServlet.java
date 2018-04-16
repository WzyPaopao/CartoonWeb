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
 * Servlet implementation class addUserServlet
 */
@WebServlet("/addUserServlet")
public class addUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = new String("");
		String password1 = new String("");
		String password2 = new String("");
		String userSex = new String("");
		
		userName = request.getParameter("newUserName");
		password1 = request.getParameter("password1");
		password2 = request.getParameter("password2");
		//userSex = request.getParameter("userSex");
		
		if( !( password1.equals(password2) ) ){
			response.sendRedirect("./LoginPage.jsp");
		}//Of if
		
		Boolean flag = false;
		flag = userService.addUser(userName, password1, userSex);
		
		if( flag ){
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("password", password1);
			
			response.sendRedirect("loginCheckServlet?userName=" + userName + "&password=" + password1 );
		}//Of if  -----------add user success, alert success, after 5s to the index
		else{
			response.sendRedirect("./LoginPage.jsp");
		}//Of else  ------------add user failed, refresh this page
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
