package com.example.winterproject.Classes;

public class Course {
	long id;
    String course;
    int image_id;
    int goal;
    String created_at;
    
    public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}
	
    // constructors
    public Course() {
    }
 
    public Course(String course, int image_id) {
        this.course = course;
        this.image_id = image_id;
    }
 
    public Course(int id, String course, int image_id) {
        this.id = id;
        this.course = course;
        this.image_id = image_id;
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

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
 
   
}