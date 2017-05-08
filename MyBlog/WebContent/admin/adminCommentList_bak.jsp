<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="keven.domain.Comment" %>
<%@include file="header.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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

  <h2>博客评论管理</h2>
<table id="tab" >
  <tr align="center">
    <th >编号</td>
    <th >评论人</td>
    <th >内容</td>
    <th >日期</td>
    <th >操作</td>
  </tr>
  <%List list=(List)request.getAttribute("list") ;
  	for(int i=0;i<list.size();i++)
  	{
  		Comment comment=(Comment)list.get(i);
  %>
  <tr>
    <td><%=comment.getId() %></td>
    <td><%=comment.getUsername() %></td>
    <td><%=comment.getContent() %></td>
    <td><%=comment.getCreatedtime() %></td>
    <td>
    <a href="/MyBlog/comment?method=delete&id=<%=comment.getId()%>"  onclick="return del()" ><img  title="删除" src="/MyBlog/admin/images/delete.gif" border="0">
    </a>
    </td>
  </tr>
 <%} %>
</table>
<%@include file="footer.jsp"%>
