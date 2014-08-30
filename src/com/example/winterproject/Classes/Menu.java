package com.example.winterproject.Classes;

public class Menu {
	long id;
    String menu;
    int image_id;
    
    public Menu() {
    }
    
    public Menu(int image_id, String menu) {
    	this.image_id = image_id;
    	this.menu = menu;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
}
