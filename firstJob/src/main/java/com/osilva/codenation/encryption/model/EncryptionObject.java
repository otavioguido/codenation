package com.osilva.codenation.encryption.model;

public class EncryptionObject {
	private int numero_casas;
	private String token;
	private String cifrado;
	private String decifrado;
	private String resumo_criptografico;
	
	
	
	public String getDecifrado() {
		return decifrado;
	}



	public int getNumero_casas() {
		return numero_casas;
	}



	public String getCifrado() {
		return cifrado;
	}



	public void setDecifrado(String decifrado) {
		this.decifrado = decifrado;
	}



	public void setResumo_criptografico(String resumo_criptografico) {
		this.resumo_criptografico = resumo_criptografico;
	}



	@Override
	public String toString() {
		return "EncryptionObject [numero_casas=" + numero_casas + ", token=" + token + ", cifrado=" + cifrado
				+ ", decifrado=" + decifrado + ", resumo_criptografico=" + resumo_criptografico + "]";
	}
	
	
}
