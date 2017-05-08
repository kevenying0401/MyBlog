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

import keven.domain.Category;
import keven.domain.User;
import keven.utils.DataSourceUtils;

/**
 * 分类页面
 */
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 做session验证 若是登陆的用户才可以进行后台的操作
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/MyBlog/admin/login.jsp");
		} else {
			// 设置编码
			request.setCharacterEncoding("utf-8");
			String method = request.getParameter("method");
			if (method.equals("add")) {
				add(request, response,user);
			} else if (method.equals("delete")) {
				delete(request, response);
			} else if (method.equals("edit")) {
				preEdit(request, response);
			} else if (method.equals("update")) {
				update(request, response);
			} else if (method.equals("list")) {
				list(request, response,user);
			} else {
				list(request, response,user);

			}
		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		String sql = "SELECT u.`username`,u.`password`,c.id AS id ,NAME,LEVEL FROM users u,category c  WHERE u.`id`=c.`user_id` AND c.`user_id`=? ORDER BY LEVEL DESC ,id DESC";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		List list = null;
		try {
			list = (List) qr.query(sql, new BeanListHandler(Category.class),user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/adminCategoryList.jsp").forward(request, response);
	}

	public void preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String sql = "select * from category where id=?";
		Category category = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		try {
			category = qr.query(sql, new BeanHandler(Category.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("category", category);
		request.getRequestDispatcher("/admin/editCategory.jsp").forward(request, response);

	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "update category set name=?,level=? where id=?";
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String level = request.getParameter("level");
		String[] params = { name, level, id };
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/category?method=list").forward(request, response);

	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String sql = "delete from category where id=?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		try {
			qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/MyBlog/category?method=list").forward(request, response);

	}

	public void add(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		// 获取表单提交的参数
		String name = request.getParameter("name");
		String level = request.getParameter("level");

		int result = 0;
		String message = "";
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "insert into category(name,level,user_id) values(?,?,?) ";
			String[] params = { name, level ,user.getId().toString()};
			result = qr.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == 1) {
			message = "博客分类添加成功！";
		} else {
			message = "博客分类添加失败！";

		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/admin/Result.jsp").forward(request, response);
	}
}
