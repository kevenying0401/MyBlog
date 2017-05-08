<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="keven.domain.*"%>
<%@include file="header.jsp" %>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/admin/css/homeStyle.css" />

<body>
	<div id="container">

		<div id="center">
			<div class="content">
				<%
					Blog blog = (Blog) request.getAttribute("blog");
					List list = (List) request.getAttribute("commentList");
				%>
				<table id="tab">
					<tr>
						<td><h2><%=blog.getTitle()%></h2></td>
					</tr>
					<tr>
						<td><%=blog.getContent()%></td>
					</tr>

					<tr>
						<td><%=blog.getCreatedTime()%></td>
					</tr>

					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="79">
							<%
								if (list != null) {
									for (int i = 0; i < list.size(); i++) {
										Comment comment = (Comment) list.get(i);
							%>
							<table id="tab">
								<tr>
									<td><%=comment.getUsername()%>:</td>
								</tr>
								<tr>
									<td><%=comment.getContent()%></td>
								</tr>
								<tr>
									<td><%=comment.getCreatedtime()%></td>
								</tr>
							</table> <br><br> <%
 	}
 	}
 %>
									<p>&nbsp;</p>
						</td>
					</tr>
					<tr>
						<td><form id="form1" name="" method="post" action="/MyBlog/comment">
							<input type="hidden" name="method" value="add" /> 
							<input type="hidden" name="blog_id" value="<%=blog.getId()%>" />
								<table id="tab">
									<tr>
										<td>评论:</td>
										<td><label> <input name="username" type="text"
												id="username" size="20" />
										</label></td>
									</tr>
									<tr>
										<td>内容：</td>
										<td><label> <textarea name="content" cols="40"
													rows="10" id="content"></textarea>
										</label></td>
									</tr>
									<tr>
										<td><label> <input type="submit" name="button"
												id="button" value="提交" />
										</label></td>
										<td>&nbsp;</td>
									</tr>

								</table>
							</form></td>
					</tr>
				</table>
				<br clear="all" />
			</div>
			<!-- end content -->
		</div>
		<!-- end center -->

		<div id="right">
			<div class="sidebar">
				<h2>分类</h2>
				<ul>
					<li><a href="/MyBlog">全部</a></li>
					<%
						List categorys = (List) request.getAttribute("categorys");
						for (int i = 0; i < categorys.size(); i++) {
							Category category = (Category) categorys.get(i);
					%>
					<li><a href="/MyBlog/userhome?cid=<%=category.getId()%>"><%=category.getName()%></a></li>
					<%
						}
					%>
				</ul>

				<h2>最近的主题</h2>
				<ul>
					<%
						List recentBlogs = (List) request.getAttribute("blogs");
						for (int i = 0; i < recentBlogs.size(); i++) {
							Blog blog2 = (Blog) recentBlogs.get(i);
					%>
					<li><a href="/MyBlog/userhome?method=get&id=<%=blog2.getId()%>"
						target="_blank"><%=blog2.getTitle()%></a></li>
					<%
						}
					%>
				</ul>
				<h2>最近的评论</h2>
				<ul>
					<%
						List recentComments = (List) request.getAttribute("comments");
						for (int i = 0; i < recentComments.size(); i++) {
							Comment comment = (Comment) recentComments.get(i);
					%>
					<li><a
						href="/MyBlog/userhome?method=get&id=<%=comment.getBlogId()%>"
						target="_blank"><%=comment.getContent()%></a></li>
					<%
						}
					%>
				</ul>


			</div>
			<!-- end sidebar -->
		</div>
		<!-- end right -->
	</div>
	<!-- end container -->
</body>
</html>
<%@include file="footer.jsp"%>