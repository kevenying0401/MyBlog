<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="keven.domain.Blog" %>
<%@include file="header.jsp"%>

<% List list=(List)request.getAttribute("list"); 
	Blog blog=null;
%>
<script type="text/javascript">		
			function del() {
				var msg = "您真的确定要删除吗？\n\n请确认！";
				if (confirm(msg)==true){
						return true;
					}else{
						return false;
					}
				}
</script>

  <h2>博客文章管理</h2>
<table id="tab" >

    <tr>
      <th>编号</th>
      <th>主题</th>
      <th>分类</th>
      <th>日期</th>
      <th>操作</th>
    </tr>
<%for(int i=0;i<list.size();i++)
{
	blog=(Blog)list.get(i);	
%>
    <tr>
      <td><%=blog.getId() %></td>
      <td><%=blog.getTitle() %></td>
      <td><%=blog.getCategory() %></td>
      <td><%=blog.getCreatedTime() %></td>
      
      <td><a href="/MyBlog/preeditblog?id=<%=blog.getId()%>"><img title="修改" src="/MyBlog/admin/images/edit.gif" border="0"></a>
      <a href="/MyBlog/deleteblog?id=<%=blog.getId()%>" onclick="return del()"><img title="删除" src="/MyBlog/admin/images/delete.gif" border="0"></a>
      </td>
    </tr>
 <%} %>
 </table>

<%@include file="footer.jsp"%>
