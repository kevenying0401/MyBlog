<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="keven.domain.Blog" %>
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

  <h2>博客文章管理</h2>
<display:table name="list" pagesize="6" class="its" requestURI="/adminbloglist" id="c">
	<display:column property="id" title="编号" sortable="true"/>
	<display:column property="title" sortable="true" title="主题"/>
	<display:column property="category" title="分类" />
	<display:column property="createdTime" title="日期" sortable="true"/>
	<display:column title="操作">
		<a href="/MyBlog/preeditblog?method=edit&id=${c.id}" ><img src="/MyBlog/admin/images/edit.gif" border="0"/></a>
		<a href="/MyBlog/deleteblog?method=delete&id=${c.id}" onclick="javascript:return del()"><img src="/MyBlog/admin/images/delete.gif" border="0"/></a>
	</display:column>
</display:table>
<%@include file="footer.jsp"%>
