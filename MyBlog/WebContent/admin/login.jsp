<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath() %>/admin/css/style.css" type="text/css" rel="stylesheet"> 	
</head>

<body>
<div id="container">	
<div id="banner" align="center">
		<h1 align="center">童话镇</h1>
</div>

<div id="menu">
</div>
<br/>
<div id="main">


<br />
<form id="form1" name="form1" method="post" action="/MyBlog/user">
<input  type="hidden" name="method" value ="login"/>
  <table width="331" height="84" border="0" align="center">
    <tr>
      <td>用户名：</td>
      <td><label>
        <input type="text" name="username" id="username" />
      </label></td>
    </tr>
    <tr>
      <td>密      码：</td>
      <td><label>
        <input type="password" name="password" id="password" />
      </label></td>
    </tr >
    <tr >
       <td colspan="2">     <% String message=(String )request.getAttribute("message"); 
	if(message!=null)
	{
		out.print("<font color='red'>"+message+"</font>");
	}
%></td>
    </tr>
    
    <tr>
      <td ><label>
        <input type="submit" name="button" id="button" value="登录" />
      </label></td>
      <td ><label>
      <a href="<%=request.getContextPath()%>/admin/register.jsp">注册</a>
 	  </label></td>
    </tr>
  </table>
</form>
<p>&nbsp; </p>
</body>
</html>
