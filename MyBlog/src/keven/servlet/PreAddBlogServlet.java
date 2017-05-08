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

import keven.domain.Category;
import keven.domain.User;
import keven.utils.DataSourceUtils;

/**
 * 读取所有分类信息
 */
public class PreAddBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		list(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 做session验证 若是登陆的用户才可以进行后台的操作
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//System.out.println(user.getUsername());
		if (user == null) {
			response.sendRedirect("/MyBlog/admin/login.jsp");
		} else {

			String sql = "select id,name,level from category order by level desc ,id desc";
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			List list = null;
			try {
				list = (List) qr.query(sql, new BeanListHandler(Category.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("/admin/addBlog.jsp").forward(request, response);
		}
	}

}
