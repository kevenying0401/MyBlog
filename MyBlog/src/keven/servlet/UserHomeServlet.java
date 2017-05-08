package keven.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import keven.domain.Blog;

import keven.domain.Category;
import keven.domain.Comment;
import keven.domain.User;
import keven.utils.DataSourceUtils;

/**
 * 看博客
 */
public class UserHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置编码
		request.setCharacterEncoding("utf-8");
		String method = (String) request.getParameter("method");
		//System.out.println(method);
		if (method == null) {
			main(request, response);
			request.getRequestDispatcher("/admin/userMain.jsp").forward(request, response);
		} else if (method.equals("get")) {
			main(request, response);
			get(request, response);

			request.getRequestDispatcher("/admin/userDisplayBlog.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
//显示首页右边的导航的信息
	public void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//博客的id
		String id = request.getParameter("id");
		//分类的编号
		String cid = request.getParameter("cid");
		String sql=null;
		if(cid==null)
		{
			 sql = "select b.id as id,title,content,createdtime,name as category,c.id as categoryid  from blog b,category c where  category_id=c.id order by b.id desc  limit 0,3";
			
		}
		else
		{
			 sql = "select b.id as id,title,content,createdtime,name as category,c.id as categoryid  from blog b,category c where  category_id=c.id and category_id="+cid+" order by b.id desc  limit 0,3";
				
		}
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

		// 查询最新博文
		List blogs = null;
		try {
			blogs = (List) qr.query(sql, new BeanListHandler<Blog>(Blog.class));// 执行数据库查询操作
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("blogs", blogs);

		// 查询最新分类
		sql = "SELECT * FROM category ORDER BY LEVEL DESC ,id DESC";
		List categorys = null;
		try {
			// 这里的Category 类 在java 系统中也有这样的类 刚开始没看清出 就导错了包 导致半天 空指针异常
			categorys = (List) qr.query(sql, new BeanListHandler(Category.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("categorys", categorys);

		// 最新评论
		sql = "select id,username,content,blog_id as blogId from comment order by id desc limit 0,4";
		List comments = null;
		try {
			comments = (List) qr.query(sql, new BeanListHandler(Comment.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("comments", comments);
		
		//查询用户
//		sql="select username from users where id="+uid;
//		User user=null;
//		try {
//			user=(User)qr.query(sql, new BeanHandler(User.class));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//		request.setAttribute("user", user);
	}
//获取首页左边的信息
	public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 接收参数
		//博客的编号
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
		

	}

}
