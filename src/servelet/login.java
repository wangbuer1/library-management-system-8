package servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.Database;


/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8"); 
		PrintWriter out=response.getWriter();
		byte b1[]=request.getParameter("id").getBytes("UTF-8");
		String id=new String(b1,"UTF-8");
		byte b2[]=request.getParameter("pwd").getBytes("UTF-8");
		String pwd=new String(b2);
		byte b3[]=request.getParameter("identity").getBytes("UTF-8");
		String identity=new String(b3);
		boolean is_punished = false;
			String name = null;
			try {
				name = Database.getDatabase().checklogin(id, pwd, identity);
				 is_punished =Database.getDatabase().is_punished(id);//查看是否存在未缴款记录
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException
					| NoSuchMethodException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(name.equals(""))
				{
				HttpSession session=request.getSession();  
				session.setAttribute("message", "登录失败！！！");
				response.sendRedirect("login.jsp");
				}
			else
			{
				HttpSession session=request.getSession();   
				session.setAttribute("id",id);//登陆成功，在session中存入已经登陆用户名
				session.setAttribute("name", name);
				session.setAttribute("identity",identity);//登陆成功，在session中存入身份
				if(is_punished)
				session.setAttribute("is_punished", true);
				else
				session.setAttribute("is_punished", false);
				Cookie cookie1=new Cookie("id",id);//用户名和密码存入cookie
				Cookie cookie2=new Cookie("pwd",pwd);
				response.addCookie(cookie1);
				response.addCookie(cookie2);
				session.setAttribute("success","登陆成功！！！");
				session.setAttribute("message", null);
				if(identity.equals("reader"))
				response.sendRedirect("Homepage_reader.jsp"); 
				else
					response.sendRedirect("Homepage_admin.jsp"); 
			}
		
	}
	}


