package com.example.winterproject.Classes;

public class Work {
	long id;
    String course;
    String item;
    String work_name;
    int mark;
    int out_of;
    int worth;
    
    public Work () {
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getWork_name() {
		return work_name;
	}
	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public int getOut_of() {
		return out_of;
	}
	public void setOut_of(int out_of) {
		this.out_of = out_of;
	}

	public int getWorth() {
		return worth;
	}

	public void setWorth(int worth) {
		this.worth = worth;
	}
	
    
    
}
