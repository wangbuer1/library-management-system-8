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

/**
 * Servlet implementation class add
 */
@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public add() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		ArrayList a = null;
		HttpSession session = request.getSession();
		
		if (request.getParameter("id") != null) {//加入黑名单
			byte b1[] = request.getParameter("id").getBytes("UTF-8");
			String id = new String(b1, "UTF-8");
			byte b2[] = request.getParameter("choice").getBytes("UTF-8");
			String choice = new String(b2, "UTF-8");
			PrintWriter out = response.getWriter();

			try {
				Database.getDatabase().add_into_black_list(id, choice);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.sendRedirect("search?id=0");
		}
		if (request.getParameter("book_id") != null) {//借书
			byte b1[] = request.getParameter("book_id").getBytes("UTF-8");
			String book_id = new String(b1, "UTF-8");
			PrintWriter out = response.getWriter();

			try {
				Database.getDatabase().borrow((String) session.getAttribute("id"), book_id);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute("book", null);
			response.sendRedirect("search?all_book=true");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		if (request.getParameter("open") != null) {//黑名单字段的动态删除
			byte b1[] = request.getParameter("open").getBytes("ISO-8859-1");
			String open = new String(b1, "UTF-8");

			try {
				if (open.equals("true"))
					Database.getDatabase().creat_blacklist();
				else
					Database.getDatabase().delete_blacklist();
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.sendRedirect("manage_rights.jsp");
		}
		if (request.getParameter("modify_book_id") != null) {//修改书籍信息
			byte b1[] = request.getParameter("modify_book_id").getBytes("ISO-8859-1");
			String book_id = new String(b1, "UTF-8");
			byte b2[] = request.getParameter("book_name").getBytes("ISO-8859-1");
			String book_name = new String(b2, "UTF-8");
			byte b3[] = request.getParameter("author").getBytes("ISO-8859-1");
			String author = new String(b3, "UTF-8");
			byte b4[] = request.getParameter("publisher").getBytes("ISO-8859-1");
			String publisher = new String(b4, "UTF-8");
			byte b5[] = request.getParameter("other_info").getBytes("ISO-8859-1");
			String other_info = new String(b5, "UTF-8");
			if (book_name.equals("") || author.equals("") || publisher.equals("")) {
				session.setAttribute("message", "书名、作者、出版社不可以为空！！！");
				response.sendRedirect("search?search_book_id=" + book_id);
				return;
			}

			System.out.print(
					book_id + "    " + book_name + "  " + author + "          " + publisher + "        " + other_info);
			PrintWriter out = response.getWriter();

			try {
				Database.getDatabase().modify_book(book_id, book_name, author, publisher, other_info);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute("message", e.getMessage());
				response.sendRedirect("search?search_book_id=" + book_id);
				return;

			}
			response.sendRedirect("search?all_book=true");
		}
		if (request.getParameter("add_book") != null) {//添加书籍
			byte b1[] = request.getParameter("book_id").getBytes("ISO-8859-1");
			String book_id = new String(b1, "UTF-8");
			byte b2[] = request.getParameter("book_name").getBytes("ISO-8859-1");
			String book_name = new String(b2, "UTF-8");
			byte b3[] = request.getParameter("author").getBytes("ISO-8859-1");
			String author = new String(b3, "UTF-8");
			byte b4[] = request.getParameter("publisher").getBytes("ISO-8859-1");
			String publisher = new String(b4, "UTF-8");
			byte b5[] = request.getParameter("other_info").getBytes("ISO-8859-1");
			String other_info = new String(b5, "UTF-8");
			if (book_name.equals("") || author.equals("") || publisher.equals("")) {
				System.out.print("---------------------------------------------");
				session.setAttribute("message", "书名、作者、出版社不可以为空！！！");
				response.sendRedirect("add_book.jsp");
				return;
			}
			try {
				Database.getDatabase().insert_book(book_id, book_name, author, publisher, other_info);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				session.setAttribute("message", e.getMessage());
				response.sendRedirect("add_book.jsp");
				return;
			}
			session.setAttribute("book", null);
			response.sendRedirect("search?all_book=true");
		}

		if (request.getParameter("max_keeping_days") != null) {//更新最长借阅时间

			byte b1[] = request.getParameter("max_keeping_days").getBytes("ISO-8859-1");
			String time = new String(b1, "UTF-8");

			try {
				Database.getDatabase().max_keeping_days(time);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute("book", null);
			response.sendRedirect("search?all_book=true");
		}
	}

}
