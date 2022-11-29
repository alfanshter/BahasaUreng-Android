package com.liamahu.bahasaureng.model.kosakata;

import com.google.gson.annotations.SerializedName;

public class KosaKataModel{

	@SerializedName("kata")
	private String kata;

	@SerializedName("bahasa")
	private String bahasa;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public String getKata(){
		return kata;
	}

	public String getBahasa(){
		return bahasa;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}
}