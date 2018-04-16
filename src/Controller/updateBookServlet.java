package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Service.bookService;

/**
 * Servlet implementation class updateBookServlet
 */
@WebServlet("/updateBookServlet")
public class updateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//上传文件储存目录
		private static final String UPLOAD_DIRECTORY = "upload";
		
	//上传配置
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;    //3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40;   //40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50;    //50MB
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		//文件上传
		if( !ServletFileUpload.isMultipartContent(request) ){
			PrintWriter out = response.getWriter();
			out.println("Error: 表单必须包含enctype = multipart/form-data");
			out.flush();
			
			return;
		}//Of if
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		upload.setHeaderEncoding("utf-8");
		
		String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
		
		File uploadDir = new File(uploadPath);
		String imageUrl = new String("");
		if( !uploadDir.exists() ){
			uploadDir.mkdirs();
		}//Of if
		
		try{
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = (List<FileItem>)upload.parseRequest( request );
			
			if( formItems != null && formItems.size() > 0 ){
				for( FileItem item : formItems ){
					if( !item.isFormField() ){
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						
						imageUrl = filePath;
						System.out.println("--------filePath: " + filePath);
						
						item.write(storeFile);
						session.setAttribute("message", "文件上传成功");
					}//Of if
				}//Of for
			}//Of if
			
		}//Of try
		catch( Exception e ){
			System.out.println("------E: " + e);
		}//Of catch
		
		/*getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);*/
		
		//数据库增加记录
		String bookName = (String)session.getAttribute("bookName");
		Calendar now = Calendar.getInstance();
		StringBuilder upDate = new StringBuilder("");
		
		upDate.append( now.get(Calendar.YEAR) + "年" );
		upDate.append( now.get(Calendar.MONTH) + "月" );
		upDate.append( now.get(Calendar.DAY_OF_MONTH) + "日" );
		
		System.out.println( "-----------今天的日期: " + upDate );
		
		
		
		bookService.updateBook(bookName, imageUrl, upDate.toString());
		response.sendRedirect("userCenterPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
