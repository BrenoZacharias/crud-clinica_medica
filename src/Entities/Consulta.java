package Entities;

import java.time.LocalDate;

public class Consulta {
	
	private int id;
	private String descricao;
	private String cpf;
	private String crm;
	private LocalDate dataConsulta;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getData() {
		return dataConsulta;
	}

	public void setData(LocalDate data) {
		this.dataConsulta = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
}
