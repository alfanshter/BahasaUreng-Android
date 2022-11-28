package com.liamahu.bahasaureng.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("jawaban")
	private List<JawabanItem> jawaban;

	@SerializedName("gambar")
	private String gambar;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public List<JawabanItem> getJawaban(){
		return jawaban;
	}

	public String getGambar(){
		return gambar;
	}
}