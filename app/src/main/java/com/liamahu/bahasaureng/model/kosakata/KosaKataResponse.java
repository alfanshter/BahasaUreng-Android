package com.liamahu.bahasaureng.model.kosakata;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class KosaKataResponse{

	@SerializedName("data")
	private List<KosaKataModel> data;

	@SerializedName("sukses")
	private int sukses;

	@SerializedName("message")
	private String message;

	public List<KosaKataModel> getData(){
		return data;
	}

	public int getSukses(){
		return sukses;
	}

	public String getMessage(){
		return message;
	}
}