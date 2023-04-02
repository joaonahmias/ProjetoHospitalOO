package Dominio;

import java.util.ArrayList;

public class Leito {
	
	private int numero;
	private String tipo;
	private boolean ocupado;
	private ArrayList<Equipamento> equipamentos;
	
	public Leito() {
		equipamentos = new ArrayList<Equipamento> ();
	}
	
	public Leito(int numero, String tipo) {
		this.numero = numero;
		this.tipo = tipo;	
		equipamentos = new ArrayList<Equipamento> ();
	}
	
	public Leito(int numero, String tipo, boolean ocupado) {
		this.numero = numero;
		this.tipo = tipo;	
		this.ocupado = ocupado;
	}
	
	public int getnumero() {
		return numero;
	}
	
	public void setnumero(int numero) {
		this.numero = numero;
	}
	
	public String gettipo() {
		return tipo;
	}
	
	public void settipo(String tipo) {
		this.tipo = tipo;
	}
	
	public boolean getocupado() {
		return ocupado;
	}

	public void setocupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	public ArrayList<Equipamento> getequipamentos() {
		return equipamentos;
	}

	public void setequipamentos(ArrayList<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
	
	public int equipamentoSize() {
		return equipamentos.size();
	}
	
	public Equipamento buscarEquipamento(int i) {
		return equipamentos.get(i);
	}
}
