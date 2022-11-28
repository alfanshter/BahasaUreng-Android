package com.liamahu.bahasaureng.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SoalKataResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("sukses")
	private int sukses;

	@SerializedName("message")
	private String message;

	public List<DataItem> getData(){
		return data;
	}

	public int getSukses(){
		return sukses;
	}

	public String getMessage(){
		return message;
	}
}