package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.*;

import DB.myDB;

/**
 * Servlet implementation class Myseverlet
 */
@WebServlet("/myseverlet")
public class myServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public myServlet() {
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
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Contentseverlet");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String url = "/index.jsp";
		String type = request.getParameter("action");
		String id = "";

		request.setCharacterEncoding("UTF-8");
		if (type.equals("login")) {
			id = request.getParameter("id");
			String password = request.getParameter("password");
			myDB mdb = new myDB();
			try {
				mdb.getConnection();
				if (mdb.checkLogin(id, password)) {
					url = "/Contentseverlet";
					HttpSession session = request.getSession();
					session.setAttribute("name", mdb.getName());
					session.setAttribute("id", id);
					
					if (mdb.getUserType().trim().equals("admin")) {
						System.out.println("admin set--");
						session.setAttribute("flag", 1);// 判断管理员权限
					}
				} else {
					HttpSession session = request.getSession();
					request.setAttribute("msg", "<script>alert(\"用密码不匹配，请检查后重试！\")</script>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// System.out.println("id = " + id + "password = " + password);
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals("register")) {
			// request.setCharacterEncoding("UTF-8");

			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			if (password1.equals(password2)) {
				String sid = request.getParameter("sid");
				String usertype = new String(request.getParameter("usertype").getBytes("ISO-8859-1"), "utf-8");
				String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "utf-8");
				myDB db = new myDB();
				try {
					db.getConnection();
					int flag = 0;
					if (db.countUser(sid) == 0) {
						// System.out.println("ok");
						flag = db.insertUser(password1, sid, username, usertype);
						if (flag == 1)
							request.setAttribute("msg", "<script>alert(\"注册成功！\")</script>");
						else {
							url="/index.jsp";
							request.setAttribute("msg", "<script>alert(\"注册失败！\")</script>");
						}
					}
					else {
						url="/login.jsp";
						request.setAttribute("msg", "<script>alert(\"用户已存在，请登录！\")</script>");
					}
					db.closeConn();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				url = "/register.jsp";
				request.setAttribute("msg", "<script>alert(\"两次密码输入不同！\")</script>");
			}
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
		// RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		// requestDispatcher.forward(request, response);
	}

}
