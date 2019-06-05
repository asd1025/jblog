package com.cafe24.jblog2.vo;

public class CategoryVo {
private int no;
private String blog_id;
private String name;
private String description;
private String reg_date;

private int totalCount;

public int getTotalCount() {
	return totalCount;
}
public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
}
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public String getBlog_id() {
	return blog_id;
}
public void setBlog_id(String blog_id) {
	this.blog_id = blog_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getReg_date() {
	return reg_date;
}
public void setReg_date(String reg_date) {
	this.reg_date = reg_date;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
@Override
public String toString() {
	return "CategoryVo [no=" + no + ", blog_id=" + blog_id + ", name=" + name + ", description=" + description
			+ ", reg_date=" + reg_date + ", totalCount=" + totalCount + "]";
}
 

}
