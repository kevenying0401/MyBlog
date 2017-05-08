<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="keven.domain.User" %>

<%--权限验证 --%>
<%User user=(User)session.getAttribute("user"); 
	if(user==null)
	{
		//response.sendRedirect("/MyBlog/admin/login.jsp");
		request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
	}
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link href="<%=request.getContextPath() %>/admin/css/style.css" type="text/css" rel="stylesheet"> 	
</head>
<body>

<div id="container">	
<div id="banner" >
		<h1 align="center">童话镇</h1>
</div>

<div id="menu">
	<%if(user!=null){
	out.print("欢迎回来,"+user.getUsername());
}
    %>
     | <a href="http://localhost:8080/MyBlog/userhome">首页</a>
 	 | <a href="http://localhost:8080/MyBlog/preaddblog">发博文</a>
	 | <a href="http://localhost:8080/MyBlog/adminbloglist">博文管理</a>
	 | <a href="http://localhost:8080/MyBlog/admin/addCategory.jsp">添加分类</a>
	 | <a href="http://localhost:8080/MyBlog/category?method=list">分类管理</a>
 	 | <a href="http://localhost:8080/MyBlog/comment?method=list">评论管理</a>
	 | <a href="http://localhost:8080/MyBlog/admin/changePassword.jsp">修改密码</a>
	 | <a href="http://localhost:8080/MyBlog/user?method=logout">退出</a>
</div>
<br/>
<div id="main">