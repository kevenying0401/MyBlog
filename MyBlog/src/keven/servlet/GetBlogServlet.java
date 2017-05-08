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
import keven.domain.Comment;
import keven.domain.User;
import keven.utils.DataSourceUtils;

/**
 * 看博客
 */
public class GetBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置编码
		request.setCharacterEncoding("utf-8");
		// 接收参数
		String id = request.getParameter("id");

		// 读取博文内容
		Blog blog = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select id,title,content,createdtime as createdTime from blog where id=?";
		try {

			List<Blog> list = (ArrayList) qr.query(sql, new BeanListHandler<Blog>(Blog.class), id);// 执行数据库查询操作
			blog = list.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库查询错误！");
		}
		// 读取博文的评论
		sql = "select id,username,content,createdtime from comment where blog_id=? order by id desc";
		List<Comment> commentList = null;
		try {
			commentList = (List) qr.query(sql, new BeanListHandler(Comment.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("blog", blog);
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher("/displayBlog.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
