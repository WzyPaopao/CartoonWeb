package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.book;
import Model.directory;
import Service.bookService;

/**
 * Servlet implementation class changeInfoBookServlet
 */
@WebServlet("/changeInfoBookServlet")
public class changeInfoBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeInfoBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		book abook = new book();
		List<directory> listDic = new ArrayList();
		HttpSession session = request.getSession();
		
		abook.setBookname( new String(request.getParameter("bookName").getBytes("ISO8859-1"), "UTF-8") );
		System.out.println("--------------------bookName is " + new String(request.getParameter("bookName").getBytes("ISO8859-1"), "UTF-8"));
		abook.setIntroduction( new String(request.getParameter("introduction").getBytes("ISO8859-1"), "UTF-8") );
		abook.setAuthor( (String)session.getAttribute("userName") );
		abook.setCategory( new String(request.getParameter("category").getBytes("ISO8859-1"), "UTF-8") );
		abook.setLike( 0 );
		abook.setComment("");
		abook.setDic( listDic );
		bookService.uploadTheBook( abook );
		
		response.sendRedirect("userCenterPage.jsp");;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
