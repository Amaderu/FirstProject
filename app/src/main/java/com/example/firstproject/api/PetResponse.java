package com.example.firstproject.api;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PetResponse {
	@SerializedName("id")
	@Expose
	private int id;

	@SerializedName("category")
	@Expose
	private Category category;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("photoUrls")
	@Expose
	private List<String> photoUrls;

	@SerializedName("tags")
	@Expose
	private List<TagsItem> tags;

	@SerializedName("status")
	@Expose
	private String status;

	public List<String> getPhotoUrls(){
		return photoUrls;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public Category getCategory(){
		return category;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public String getStatus(){
		return status;
	}
}