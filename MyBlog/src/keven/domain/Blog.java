package keven.domain;

import java.util.Date;

public class Blog {
	private Integer id;
	private String title;
	private String content;
	private Date createdTime;
	private String category;
	private Integer categoryId;
	public Blog() {
		super();
		
	}

	

	public Blog(Integer id, String title, String content, Date createdTime, String category, Integer categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdTime = createdTime;
		this.category = category;
		this.categoryId = categoryId;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	
	

}
