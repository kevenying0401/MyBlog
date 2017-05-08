package keven.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;

import keven.domain.User;
import keven.utils.DataSourceUtils;

/**
 * 写博客
 */
public class AddBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		// 做session验证 若是登陆的用户才可以进行后台的操作
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//System.out.println(user);
		if (user == null) {
			response.sendRedirect("/MyBlog/admin/login.jsp");
		} else {

			// 获取表单提交的参数
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String categoryId = request.getParameter("category");
			String userId=user.getId().toString();
			System.out.println(userId);
			/*
			 * System.out.println(title); System.out.println(content);
			 * System.out.println(categoryId);
			 */
			// 将获取到的参数写入数据库
			// 这里我用到c3p0数据库连接池 和dbUtil 来简化对数据库的操作
			int result = 0;

			try {
				QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
				String sql = "insert into blog(title,content,category_id,user_id,createdtime) values (?,?,?,?,now())";
				String[] params = { title, content, categoryId,userId };
				result = qr.update(sql, params);// 执行数据库插入操作

			} catch (SQLException e) {
				e.printStackTrace();
				//throw new RuntimeException("数据库写入错误！");
			}
			// 返回浏览器端的信息

			String message = "";
			if (result == 1)// 若插入成功 则要提示插入成功 否则提示失败
			{
				message = "添加博文成功！";
			} else {
				message = "添加博文失败，请重新添加";
			}
			// 使用域对象 设置回传信息
			request.setAttribute("message", message);
			// 请求转发 将回传信息显示在 请求转发的页面上
			request.getRequestDispatcher("/admin/Result.jsp").forward(request, response);

		}
	}

}
