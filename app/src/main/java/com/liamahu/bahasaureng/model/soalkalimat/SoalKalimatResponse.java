package com.liamahu.bahasaureng.model.soalkalimat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SoalKalimatResponse{

	@SerializedName("data")
	private List<SoalKalimatModel> data;

	@SerializedName("sukses")
	private int sukses;

	@SerializedName("message")
	private String message;

	public List<SoalKalimatModel> getData(){
		return data;
	}

	public int getSukses(){
		return sukses;
	}

	public String getMessage(){
		return message;
	}
}