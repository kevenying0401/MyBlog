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
import keven.domain.Comment;
import keven.domain.User;
import keven.utils.DataSourceUtils;

/**
 * 评论
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String method = request.getParameter("method");
			// System.out.println(method);
			if(method==null)
			{
				method="";
			}
			else if (method.equals("add"))// 添加评论
			{
				add(request, response);
			}
			else{
				HttpSession session =request.getSession();
				User user=(User) session.getAttribute("user");
				if(user==null)
				{
					response.sendRedirect("/blog/admin/login.jsp");
				}
				else{
					if (method.equals("list")) {
						list(request, response,user);
					} else if (method.equals("delete")) {
						delete(request, response,user);
					}else {
						list(request, response,user);
					}
				}
			}
		}
	

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String blog_id = request.getParameter("blog_id");
		String username = request.getParameter("username");
		String content = request.getParameter("content");
		// System.out.println(blog_id);
		// System.out.println(username);
		// System.out.println(content);
		if (username == null || username.equals(""))// 如果是游客或是匿名的情况,先去登陆在评论
		{
			username="匿名";
		} 

			try {
				QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
				String sql = "insert into comment (username,content,blog_id,createdtime) values(?,?,?,now())";
				String[] params = { username, content, blog_id};
				qr.update(sql, params);
			} catch (Exception e) {
				e.printStackTrace();
			}

			response.sendRedirect("/MyBlog/home?method=get&id=" + blog_id);
		}

	

	public void list(HttpServletRequest request, HttpServletResponse response, User user)
			throws ServletException, IOException {
		String sql = "SELECT c.id ,c.username ,c.content,c.createdtime, b.`id` AS blogId ,b.title AS title,u.`id` AS userId FROM blog b,COMMENT c ,users u WHERE b.`id`=c.`blog_id` AND b.`user_id`=u.`id`AND u.`id`=? ORDER BY c.id DESC";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		List list = null;
		//System.out.println(user.getUsername());
		try {
			list = (List) qr.query(sql, new BeanListHandler(Comment.class),user.getId().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/adminCommentList.jsp").forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response, User user)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String sql = "delete from comment where id=?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list(request, response,user);
	}

}
