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
 * Servlet implementation class changeUserInfoServlet
 */
@WebServlet("/changeUserInfoServlet")
public class changeUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = new String( request.getParameter("name").getBytes("ISO8859-1"), "UTF-8" );
		String sex = new String( request.getParameter("sex").getBytes("ISO8859-1"), "UTF-8" );
		String self = new String( request.getParameter("self").getBytes("ISO8859-1"), "UTF-8" );
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		
		Boolean result = userService.changeUserInfo(userName, name, sex, self);
		if( result ){
			System.out.println("-------信息修改成功");
		}//Of if
		
		response.sendRedirect("theCenterServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
