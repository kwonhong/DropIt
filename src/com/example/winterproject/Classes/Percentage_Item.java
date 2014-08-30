package com.example.winterproject.Classes;

import android.widget.Spinner;

public class Percentage_Item {
	String name;
	Spinner spinner;
	long id;
	int percentage;
	
	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public Percentage_Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Percentage_Item(String name, Spinner spinner) {
		this.name = name;
		this.spinner = spinner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Spinner getSpinner() {
		return spinner;
	}

	public void setSpinner(Spinner spinner) {
		this.spinner = spinner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
