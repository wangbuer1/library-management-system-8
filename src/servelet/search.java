package servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.Database;
import DB.punish_record;


/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String punish_record_id=request.getParameter("punish_record_id");
		ArrayList a = null;
		HttpSession session=request.getSession();
		if(request.getParameter("search_book_id")!=null) {//查找书籍
			byte b1[]=request.getParameter("search_book_id").getBytes("ISO-8859-1");
			String id=new String(b1,"UTF-8");
			
				 try {
					a=Database.getDatabase().search_book(id);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			session.setAttribute("searched_book", a.get(0));
			response.sendRedirect("book_info.jsp");
		}
		if(request.getParameter("search_user")!=null)//查找单个用户
		{
			byte b1[]=request.getParameter("search_user").getBytes("ISO-8859-1");
			String id=new String(b1,"UTF-8");
			
				
					try {
						if(session.getAttribute("identity").equals("reader"))
						a=Database.getDatabase().search_reader(id);
						else
				a=Database.getDatabase().search_admin(Integer.parseInt(id));
					} catch (NumberFormatException | InstantiationException | IllegalAccessException
							| InvocationTargetException | NoSuchMethodException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			session.setAttribute("person_info", a.get(0));
			response.sendRedirect("person_info.jsp");
		}
		if(request.getParameter("id")!=null)//查找所有读者
		{

				try {
					a=Database.getDatabase().search_reader("");
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			session.setAttribute("result", a);
			response.sendRedirect("managereader.jsp");
		}
		if(request.getParameter("borrow_record")!=null)//查找读者的借阅历史
		{
				 try {
					a=Database.getDatabase().search_borrow_record((String)session.getAttribute("id"),"");
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			session.setAttribute("borrow_record", a);
			response.sendRedirect("history.jsp");
		}
		if(request.getParameter("all_book")!=null)//查找所有图书
		{
		
				 try {
					a=Database.getDatabase().search_book("");
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			session.setAttribute("book", a);
			response.sendRedirect("searchbook.jsp");
		}
		if(request.getParameter("reader_id")!=null)//查找处罚记录
		{
		byte b1[]=request.getParameter("reader_id").getBytes("ISO-8859-1");
		String reader_id=new String(b1,"UTF-8");
		
			 try {
				a=Database.getDatabase().search_punish_record(reader_id);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
		session.setAttribute("punishment", a);
		session.setAttribute("reader_id", reader_id);
		response.sendRedirect("punishment.jsp");
		}
		
		if(punish_record_id!=null)//恢复借还书权利
		{
				try {
					Database.getDatabase().recover_rights(punish_record_id);
					a=Database.getDatabase().search_punish_record((String)session.getAttribute("reader_id"));
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			session.setAttribute("punishment", a);
			response.sendRedirect("punishment.jsp");
		}
		
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		ArrayList a = null;
		HttpSession session=request.getSession();
		request.getParameter("reader_id");
		if(request.getParameter("history_info")!=null) {//查找借阅历史
			byte b1[]=request.getParameter("history_info").getBytes("ISO-8859-1");
			String s=new String(b1,"UTF-8");
			
				 try {
					a=Database.getDatabase().search_borrow_record((String)session.getAttribute("id"), s);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				 session.setAttribute("borrow_record", a);
					response.sendRedirect("history.jsp");
		}
		if(request.getParameter("reader_id")!=null)//查找处罚记录
		{
		byte b1[]=request.getParameter("reader_id").getBytes("ISO-8859-1");
		String reader_id=new String(b1,"UTF-8");
			 try {
				a=Database.getDatabase().search_punish_record(reader_id);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
		session.setAttribute("punishment", a);
		session.setAttribute("reader_id", reader_id);
		response.sendRedirect("punishment.jsp");
		}
		if(request.getParameter("book_id_name")!=null)//查找图书
		{
		byte b1[]=request.getParameter("book_id_name").getBytes("ISO-8859-1");
		String s=new String(b1,"UTF-8");
		PrintWriter out=response.getWriter();

			 try {
				a=Database.getDatabase().search_book(s);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		session.setAttribute("book", a);
		response.sendRedirect("searchbook.jsp");
		}
		
		if(request.getParameter("_reader_id")!=null)//查找读者
		{
			byte b2[]=request.getParameter("_reader_id").getBytes("ISO-8859-1");
			String _reader_id=new String(b2,"UTF-8");
			PrintWriter out=response.getWriter();
				 try {
					a=Database.getDatabase().search_reader(_reader_id);
				} catch (NumberFormatException | InstantiationException | IllegalAccessException
						| InvocationTargetException | NoSuchMethodException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			session.setAttribute("result", a);
			response.sendRedirect("managereader.jsp");
		}
		
		
	}

}
