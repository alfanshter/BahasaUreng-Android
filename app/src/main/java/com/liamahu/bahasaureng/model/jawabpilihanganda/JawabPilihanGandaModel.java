package com.liamahu.bahasaureng.model.jawabpilihanganda;

import com.google.gson.annotations.SerializedName;

public class JawabPilihanGandaModel {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("jawaban")
	private String jawaban;

	@SerializedName("id_pilihanganda")
	private int idPilihanganda;

	@SerializedName("is_true")
	private int isTrue;

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

	public int getIdPilihanganda(){
		return idPilihanganda;
	}

	public int getIsTrue(){
		return isTrue;
	}
}