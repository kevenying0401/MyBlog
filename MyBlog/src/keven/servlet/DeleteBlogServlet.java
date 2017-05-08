package keven.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import keven.domain.Blog;
import keven.domain.User;
import keven.utils.DataSourceUtils;

/**
 * Servlet implementation class DeleteBlogServlet
 */
public class DeleteBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 做session验证 若是登陆的用户才可以进行后台的操作
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/MyBlog/admin/login.jsp");
		} else {
			// 设置编码
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			try {
				QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
				String sql = "delete  from blog where id=?";
				qr.update(sql, id);// 执行数据删除询操作
				request.getRequestDispatcher("/adminbloglist").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				// throw new RuntimeException("数据库删除错误！");
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
