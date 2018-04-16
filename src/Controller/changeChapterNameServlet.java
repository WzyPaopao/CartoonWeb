package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.bookService;

/**
 * Servlet implementation class changeChapterNameServlet
 */
@WebServlet("/changeChapterNameServlet")
public class changeChapterNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeChapterNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String chapterName = new String( request.getParameter("chapterName").getBytes("ISO8859-1"), "UTF-8" );
		System.out.println("-----------chapterName is " + chapterName);
		Boolean f = bookService.changeChapterName(chapterName);
		
		if( f ){
			System.out.println("||||||||||||||||||||||||||||||||ÐÞ¸Ä³É¹¦");
		}
		
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
