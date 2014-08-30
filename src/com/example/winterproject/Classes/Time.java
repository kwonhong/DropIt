package com.example.winterproject.Classes;

public class Time {

	private int GoalTime;
	private int Time;
	private int progress;
	private String name;
	private String course;
	private int id;
	
	public Time() {
		Time = 0;
		GoalTime = 0;
		progress = 0;
	}
	
	public int getGoalTime() {
		return GoalTime;
	}

	public void setGoalTime(int goalTime) {
		GoalTime = goalTime;
	}

	public int getTime() {
		return Time;
	}

	public void setTime(int time) {
		Time = time;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
}
