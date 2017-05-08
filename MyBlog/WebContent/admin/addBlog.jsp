<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="header.jsp"%>
<%@ page import="java.util.List"%>
<%@page import="keven.domain.Category" %>
 	
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!-- 这里的CKEDITOR.replace是将富文本编辑器中的内容代替 textArea中的内容-->
 window.onload=function(){
	 
	 CKEDITOR.replace('content');
 }
</script>


<h2>发博文</h2>   
<form id="form1" name="form1" method="post" action="http://localhost:8080/MyBlog/addblog">
<table id="tab">
<tr>
    <td>主题: </td>
    <td><input name="title" type="text" id="title" size="60" /></td>
</tr>

<tr>
    <td>分类: </td>
    <td>  <select name="category" id="select">
        <% List list=(List)request.getAttribute("list");
        	for(int i=0;i<list.size();i++)
        	{
        		Category c=(Category)list.get(i);
        %>
        	<option value="<%=c.getId()%>"><%=c.getName()%></option>
        	}
        	
        <% }%>
         
        </select>
</td>
</tr>


<tr>
    <td colspan="2">内容: <br/>
		<textarea name="content" cols="100" rows="18" id="content"></textarea>
    </td>
</tr>

<tr>
    <td colspan="2">
					<input type="submit" name="submit" value="创建"/>
		    </td>
</tr>
</table>
</form>
<%@include file="footer.jsp" %>