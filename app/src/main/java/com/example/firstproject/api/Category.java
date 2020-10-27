package com.example.firstproject.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category{

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("id")
	@Expose
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}