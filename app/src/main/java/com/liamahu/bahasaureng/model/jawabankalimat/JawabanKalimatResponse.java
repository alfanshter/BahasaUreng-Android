package com.liamahu.bahasaureng.model.jawabankalimat;

import com.google.gson.annotations.SerializedName;

public class JawabanKalimatResponse{

	@SerializedName("nilai")
	private int nilai;

	@SerializedName("sukses")
	private int sukses;

	@SerializedName("message")
	private String message;

	@SerializedName("jumlah_soal")
	private int jumlahSoal;

	public int getNilai(){
		return nilai;
	}

	public int getSukses(){
		return sukses;
	}

	public String getMessage(){
		return message;
	}

	public int getJumlahSoal(){
		return jumlahSoal;
	}
}