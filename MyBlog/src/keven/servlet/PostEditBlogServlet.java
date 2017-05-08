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
 * Servlet implementation class PostEditBlogServlet
 */
public class PostEditBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 做session验证 若是登陆的用户才可以进行后台的操作
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/MyBlog/admin/login.jsp");
		}
		else{
		//设置编码
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String categoryId=request.getParameter("category");
		/*System.out.println(id);
		System.out.println(title);
		System.out.println(content);*/
		String message ="";
		int result=0;
		try {
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			String sql="update blog set title=?,content=? ,category_id=? where id=?";
			String [] params={title,content,categoryId,id};
			result=qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new RuntimeException("数据库查询错误！");
		}
		
		if(result==1)
		{
			message="修改博文成功！";
		}
		else{
			message="修改博文失败！";
		}
		//System.out.println(message);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/admin/editResult.jsp").forward(request, response);
		}
	}

}
