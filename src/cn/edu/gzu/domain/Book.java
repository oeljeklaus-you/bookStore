package cn.edu.gzu.domain;

public class Book {
	private String id;
	private String bname;
	private String author;
	private int  pageNum;
	private float price;
	private String description;//描述
	private String path;//存放文件的目录
	private String oldImageName;//原来文件名
	private String newImageName;//新文件名（保证文件不重名）
	private Category category;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int  getPageNum() {
		return pageNum;
	}
	public void setPageNum(int  pageNum) {
		this.pageNum = pageNum;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOldImageName() {
		return oldImageName;
	}
	public void setOldImageName(String oldImageName) {
		this.oldImageName = oldImageName;
	}
	public String getNewImageName() {
		return newImageName;
	}
	public void setNewImageName(String newImageName) {
		this.newImageName = newImageName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bname=" + bname + ", author=" + author
				+ ", pageNum=" + pageNum + ", price=" + price
				+ ", description=" + description + ", path=" + path
				+ ", oldImageName=" + oldImageName + ", newImageName="
				+ newImageName + ", category=" + category + "]";
	}
	

}
