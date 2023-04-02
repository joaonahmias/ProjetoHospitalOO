package Dominio;

import java.sql.Date;


public class Paciente {
	
	private String cpf;
	private String nome;
	private String telefone;
	private String endereco;
	private Date dataNascimento;
	
	public Paciente() {
		
	}
	
	public Paciente (String cpf, String nome, String telefone, String endereco, Date dataNascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
	}
	
	public String getcpf() {
		return cpf;
	}
	
	public void setcpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getnome() {
		return nome;
	}
	
	public void setnome(String nome) {
		this.nome = nome;
	}
	
	public String gettelefone() {
		return telefone;
	}
	
	public void settelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getendereco() {
		return endereco;
	}
	
	public void setendereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getdataNascimento() {
		return dataNascimento;
	}

	public void setdataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
	
}
