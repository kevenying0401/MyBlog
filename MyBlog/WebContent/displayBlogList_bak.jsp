<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="keven.domain.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/MyBlog/admin/css/homeStyle.css" />
<title>显示博客列表</title>
</head>
<% List list=(List)request.getAttribute("list");
	Blog blog=null;
%>
<body>
<%
for(int i=0;i<list.size();i++)
{
	blog=(Blog)list.get(i);
%>

<table width="543" height="125" border="0">
  <tr>
    <td><%=blog.getCreatedTime() %></td>
  </tr>
  <tr>
    <td><a href="http://localhost:8080/MyBlog/getblog?id=<%=blog.getId() %>"><%=blog.getTitle() %></a></td>
  </tr>
  <tr>
    <td><% 
    	String content=blog.getContent();
        String newContent="";
        if(content.length()<100){
             newContent=content;
        }
        else{
        	newContent=content.substring(0, 100);
        }
        out.print(newContent+" ...");
    %></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<p>&nbsp;</p>

<%} %>
</body>
</html>
