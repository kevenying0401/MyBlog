package keven.domain;

import java.util.Date;

public class Comment implements java.io.Serializable{
	private Integer id;
	private String username;
	private String  content;
	private Date createdtime;
	private Integer  blogId;
	private String title; 
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Comment(Integer id, String username, String content, Date createdtime, Integer blogId, String category,
			Integer categoryId) {
		super();
		this.id = id;
		this.username = username;
		this.content = content;
		this.createdtime = createdtime;
		this.blogId = blogId;
		this.title=title;
	}

	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	
	

}
