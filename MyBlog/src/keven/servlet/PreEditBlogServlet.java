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
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import keven.domain.Blog;
import keven.domain.Category;
import keven.domain.User;
import keven.utils.DataSourceUtils;

/**
 * 先将博文显示
 */
public class PreEditBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 做session验证 若是登陆的用户才可以进行后台的操作
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
	//	System.out.println(user);
		if (user == null) {
			response.sendRedirect("/MyBlog/admin/login.jsp");
		} else {
			// 设置编码
			request.setCharacterEncoding("utf-8");

			// 接收参数
			String id = request.getParameter("id");
			Blog blog = null;
			List categorys=null;
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select id,title,content,category_id as categoryid from blog where id=?";
			try {
				
				List list = (List) qr.query(sql, new BeanListHandler<Blog>(Blog.class), id);// 执行数据库查询操作
				blog = (Blog)list.get(0);
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("数据库查询错误！");
			}
			
			sql="select id,name from category order by level desc,id desc";
			
			try {
				categorys=(List)qr.query(sql, new BeanListHandler(Category.class));
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			request.setAttribute("blog", blog);
			request.setAttribute("categorys",categorys);
			request.getRequestDispatcher("/admin/editBlog.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
