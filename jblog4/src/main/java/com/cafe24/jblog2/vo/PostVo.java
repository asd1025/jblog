package com.cafe24.jblog2.vo;

public class PostVo {
private int no;
private int category_no;
private String title;
private String content;
private String reg_date;

private String name;

public int getNo() {
	return no;
}

public void setNo(int no) {
	this.no = no;
}

public int getCategory_no() {
	return category_no;
}

public void setCategory_no(int category_no) {
	this.category_no = category_no;
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

public String getReg_date() {
	return reg_date;
}

public void setReg_date(String reg_date) {
	this.reg_date = reg_date;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@Override
public String toString() {
	return "PostVo [no=" + no + ", category_no=" + category_no + ", title=" + title + ", content=" + content
			+ ", reg_date=" + reg_date + ", name=" + name + "]";
}
 

}
