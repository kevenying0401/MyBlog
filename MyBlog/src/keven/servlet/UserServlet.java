package keven.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import keven.domain.User;
import keven.utils.DataSourceUtils;

/**
 * 用户
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if (method == null || method.equals("")) {
			method = "";
		}
		if (method.equals("login")) {
			login(request, response);
		}
		else if (method.equals("logout")) {
			logout(request, response);
		}
		else if (method.equals("change")) {
			changePassword(request, response);
		}
		else if (method.equals("register")) {
			register(request, response);
		}
		
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String newPassword=request.getParameter("newPassword");
		if(!(username.equals(""))&&password.equals(newPassword))
		{
			try {
				QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
				String sql="insert into users(username,password) values(?,?)";
				
				String [] params={username,password};
				qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				request.setAttribute("message", "注册成功，请登录");
				request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
			
		}
		else if(!(password.equals(newPassword))||username.equals(""))
		{
			request.setAttribute("message", "两次密码输入不相同，请重新输入");
			request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
		}
		
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String sql = "select * from users where username=? and password=?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String[] params = { username, password };
		User user = null;

		try {
			user = (User) qr.query(sql, new BeanHandler(User.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user != null) {
			//将User对象放在Session 域对象中
			HttpSession session = request.getSession();
			//设置session 的生命周期是10分钟
			//session.setMaxInactiveInterval(600);
			session.setAttribute("user", user);
			request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "用户名或密码不正确,若您没有账户，请先注册！");
			request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		}

	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			session.invalidate();//session 失效
			response.sendRedirect("/MyBlog");
	}
	public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPassword=request.getParameter("oldPassword");
		String newPassword=request.getParameter("newPassword");
		String reNewPassword=request.getParameter("reNewPassword");
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		String password=user.getPassword();
		if(!password.equals(oldPassword))
		{
			request.setAttribute("message", "您输入的原密码有错误");
			request.getRequestDispatcher("/admin/Result.jsp").forward(request, response);
		}
		else if(newPassword.equals(reNewPassword))
		{
			String sql="update users set password=? where id=?";
			String[] params={newPassword,user.getId()+""};
			int result=0;
			String message="";
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			try {
				result=qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(result==0)
			{
				message="修改密码失败！";
			}
			else
			{
				message="修改密码成功！";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/admin/Result.jsp").forward(request, response);
			//response.sendRedirect("/MyBlog/admin/Result.jsp");
		}
		else{
			request.setAttribute("message", "您输入两次新密码不相同");
			request.getRequestDispatcher("/admin/Result.jsp").forward(request, response);
		}
	
	}

}
