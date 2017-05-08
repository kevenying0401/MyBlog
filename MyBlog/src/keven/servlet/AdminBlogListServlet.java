package keven.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * 看博客
 */
public class AdminBlogListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		// 做session验证 若是登陆的用户才可以进行后台的操作
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/MyBlog/admin/login.jsp");
		} else {
			// 接收参数
			String id = request.getParameter("id");
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "SELECT u.username,u.`password` ,b.id AS id ,title,content,createdtime, c.name AS category FROM users u,blog b,category c WHERE b.category_id=c.id  AND u.`id`=b.`user_id`  AND u.`id`=? ORDER BY b.id DESC";
			try {
				
				List list = (List) qr.query(sql, new BeanListHandler<Blog>(Blog.class),user.getId());// 执行数据库查询操作
				request.setAttribute("list", list);
				request.getRequestDispatcher("/admin/adminBlogList.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				// throw new RuntimeException("数据库查询错误！");
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
