package Entities;

import java.time.LocalDate;

public class Atendimento {
	
	private LocalDate data;
	private String cpf;
	private int codFunc;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getCodFunc() {
		return codFunc;
	}
	public void setCodFunc(int codFunc) {
		this.codFunc = codFunc;
	}

}
