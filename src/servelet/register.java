package servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.Database;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8"); 
		byte b1[]=request.getParameter("name").getBytes("UTF-8");
		String name=new String(b1,"UTF-8");
		byte b2[]=request.getParameter("pwd").getBytes("UTF-8");
		String pwd=new String(b2);
		byte b3[]=request.getParameter("tel").getBytes("UTF-8");
		String tel=new String(b3,"UTF-8");
		byte b4[]=request.getParameter("sex").getBytes("UTF-8");
		String sex=new String(b4,"UTF-8");
		int id=0;
		HttpSession session=request.getSession();  
		if(request.getParameter("id")!=null)
		{
		byte b5[]=request.getParameter("id").getBytes("UTF-8");
		id=Integer.parseInt(new String(b5,"UTF-8"));
		}
		if(id>0)//�޸ĸ�����Ϣ
		{
			 session=request.getSession();
			
				try {
					Database.getDatabase().modify_info(String.valueOf(id), pwd, name, sex, tel,(String) session.getAttribute("identity"));
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.setAttribute("message", "�޸���Ϣʧ�ܣ�����������Ҫ4λ�ַ����ϣ��绰��Ҫ11λ���֣�����");
					response.sendRedirect("search?search_user="+id); 
					return;
				}
		
			
			response.sendRedirect("search?search_user="+id);
		}
		else//ע��
		{
	
			try {
				id=Database.getDatabase().insertreader(pwd,name,sex,tel);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
					| SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute("message", "ע��ʧ�ܣ�����������Ҫ4λ�ַ����ϣ��绰��Ҫ11λ���֣�����");
				response.sendRedirect("login.jsp"); 
				return;
			}
		
		
		out.print("ע��ɹ�������id�ǣ�"+id+"<br>�����Ʊ�������id");
		response.setHeader("Refresh","2;login.jsp"); 
		}
	}

}
