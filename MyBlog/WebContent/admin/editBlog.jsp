<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="keven.domain.Blog" %>
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

<% Blog blog=(Blog)request.getAttribute("blog") ;%>
<h2>修改博文</h2>
<form id="form1" name="form1" method="post" action="/MyBlog/posteditblog">
<input type="hidden" name ="id" value="<%=blog.getId() %>">
  <table id="tab">
    <tr>
      <td >主题：</td>
      <td>
        <input name="title" type="text" id="title" size="100" value="<%=blog.getTitle()%>"/>
      </td>
    </tr>
    <tr>
      <td>类别：</td>
      <td>
        <select name="category" id="select">
        <% List categorys=(List)request.getAttribute("categorys");
        int oldCid=blog.getCategoryId();
        	for(int i=0;i<categorys.size();i++)
        	{
        		Category c=(Category)categorys.get(i);
        		if(c.getId().equals(oldCid))
        		{
        %>
          <option value="<%=c.getId() %>" selected="selected"><%=c.getName() %></option>
          <%}else{%>
          	<option value="<%=c.getId() %>" ><%=c.getName() %></option>
        	<%}}%>
        </select>
     </td>
    </tr>
    <tr>
      <td colspan="2">内容：<br/>
      
        <textarea name="content" cols="100" rows="18" id="content" ><%=blog.getContent()%></textarea>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" name="submit"  value="更新" />
      </td>

    </tr>
    
    </tr>
  </table>
</form>
<%@include file="footer.jsp"%>