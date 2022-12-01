package com.liamahu.bahasaureng.model.soalkalimat;

import com.google.gson.annotations.SerializedName;

public class SoalKalimatModel {

	@SerializedName("soal")
	private String soal;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("jawaban")
	private String jawaban;

	public String getSoal(){
		return soal;
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

	public String getJawaban(){
		return jawaban;
	}
}