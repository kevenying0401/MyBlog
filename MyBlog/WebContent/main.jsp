<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="keven.domain.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>童话镇的主页</title>
<link rel="stylesheet" type="text/css" href="/MyBlog/admin/css/homeStyle.css" />
</head>
<body>
<div id="container">	
	<div id="banner">
		<h1 align="center"><a href="/blog">童话镇</a></h1>
	</div>

<div id="center">
<div class="content">
    <!-- list blog begin  -->
    <%List blogs=(List)request.getAttribute("blogs");
    for(int i=0;i<blogs.size();i++)
    {
    	Blog blog=(Blog)blogs.get(i);
    	
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
    	String date=sdf.format(blog.getCreatedTime());
    	
    	sdf=new SimpleDateFormat("HH:mm:ss");
    	String time=sdf.format(blog.getCreatedTime());
    %>
   	<h2><%=date %></h2>
    <div class="entry">
	    <a id="6"></a>	
		<h3><a href="/MyBlog/home?method=get&id=<%=blog.getId()%>" target="_blank"><%=blog.getTitle() %></a></h3>
			<% 
    	String content=blog.getContent();
        String newContent="";
        if(content.length()<200){
             newContent=content;
        }
        else{
        	newContent=content.substring(0, 200);
        }
        out.print(newContent+" ...");
    %>
		<p class="posted"><%=time %>&nbsp;&nbsp;|<a href="/MyBlog/home?cid=<%=blog.getCategoryId()%>"><%=blog.getCategory() %></a> </p>
    </div>
<%} %>
	<!-- 产生分页的连接-->
   	&nbsp; 1/2 &nbsp;<a href="tm?method=h&p=2">&gt;&gt;</a>
   	
 <!-- end list -->	

<br clear="all" />
</div><!-- end content -->
</div><!-- end center -->

<div id="right">
<div class="sidebar">
        <ul>
    	<li><a href="/MyBlog/admin/login.jsp">登录</a></li>&nbsp;&nbsp;
    	<li><a href="/MyBlog/admin/register.jsp">注册</a></li>
      </ul>
  	     <h2>分类</h2>
   <ul>		
	<li><a href="/MyBlog">全部</a></li>
	<% List categorys=(List)request.getAttribute("categorys");
		for(int i=0;i<categorys.size();i++)
		{
			Category category=(Category)categorys.get(i);

	%>
	    <li><a href="/MyBlog/home?cid=<%=category.getId()%>"><%=category.getName() %></a></li>
       <%} %>
       </ul>

  	    <h2>最近的主题</h2>
  <ul>		
  <%
  List recentBlogs=(List)request.getAttribute("blogs");
  for(int i=0;i<recentBlogs.size();i++)
  {
	  Blog blog=(Blog)recentBlogs.get(i);
  
  %>
	    <li><a href="/MyBlog/home?method=get&id=<%=blog.getId()%>" target="_blank"><%=blog.getTitle() %></a></li>
        <%} %>
      </ul>
  	    <h2>最近的评论</h2>
	  <ul>
	  <%
  List recentComments=(List)request.getAttribute("comments");
  for(int i=0;i<recentComments.size();i++)
  {
	  Comment comment=(Comment)recentComments.get(i);
  
  %>	
	    <li><a href="/MyBlog/home?method=get&id=<%=comment.getBlogId()%>" target="_blank"><%=comment.getContent() %></a></li>
        <%} %>
      </ul>
  	   	
</div><!-- end sidebar -->	
</div><!-- end right -->
</div><!-- end container -->
</body>
</html>