package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.book;
import Service.userService;
import Service.viewService;

/**
 * Servlet implementation class viewBookInfoServlet
 */
@WebServlet("/viewBookInfoServlet")
public class viewBookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewBookInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookName = new String("");
		book thebook = new book();
		HttpSession session = request.getSession();
		String userName = new String("");
		
		if( (Boolean)session.getAttribute("loginStatus") ){
			userName = session.getAttribute("userName").toString();
		}
		
		bookName = new String(request.getParameter("bookName").getBytes("ISO8859-1"), "UTF-8");
		System.out.println("bookName is " + bookName);
		thebook = viewService.getBookInfo(bookName);
		
		Boolean isFocus = userService.isFocus(userName, bookName);
		
		session.setAttribute("thebook", thebook);
		session.setAttribute("bookName", bookName);
		session.setAttribute("isFocus", isFocus);
		
		
		
		response.sendRedirect("./viewBookInfoPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
