package keven.domain;

public class Category {
	private Integer id;
	private String name;
	private Integer level;
	public Category() {
	}
	public Category(Integer id, String name, Integer level) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	

}
