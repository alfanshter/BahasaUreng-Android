package com.liamahu.bahasaureng.model.jawabpilihanganda;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class JawabPilihanGandaResponse {

	@SerializedName("data")
	private List<JawabPilihanGandaModel> data;

	@SerializedName("nilai")
	private int nilai;

	@SerializedName("sukses")
	private int sukses;

	@SerializedName("nilai_total")
	private int nilaiTotal;

	@SerializedName("message")
	private String message;

	@SerializedName("jumlahsoal")
	private int jumlahsoal;

	public List<JawabPilihanGandaModel> getData(){
		return data;
	}

	public int getNilai(){
		return nilai;
	}

	public int getSukses(){
		return sukses;
	}

	public int getNilaiTotal(){
		return nilaiTotal;
	}

	public String getMessage(){
		return message;
	}

	public int getJumlahsoal(){
		return jumlahsoal;
	}
}