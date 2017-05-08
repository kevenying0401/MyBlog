<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="keven.domain.Comment" %>
<%@include file="header.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<style type="text/css" media="all">
        @import url("/MyBlog/admin/css/screen.css");
</style>
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
<display:table name="list" pagesize="10" class="its" requestURI="/comment" id="c">
	<display:column property="id" title="编号" sortable="true"/>
	<display:column property="title" sortable="true" title="博文标题"/>
	<display:column property="username" sortable="true" title="评论人"/>
	<display:column property="content" title="内容" />
	<display:column property="createdtime" title="日期" sortable="true"/>
	<display:column title="操作">
		<a href="/MyBlog/comment?method=delete&id=${c.id}" onclick="javascript:return del()"><img src="/MyBlog/admin/images/delete.gif" border="0"/></a>
	</display:column>
</display:table>
<%@include file="footer.jsp"%>
