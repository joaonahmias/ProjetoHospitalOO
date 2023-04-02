package Dominio;


import java.sql.Date;
import java.sql.Time;

public class Internacao {
	
	private int codigo;
	private Date dataEntrada;
	private Time horaEntrada;
	private Date dataSaida;
	private Time horaSaida;
	private String causas;
	private String status;
	private Paciente paciente;
	private Leito leito;
	
	public Internacao() {
		
	}
	
	public Internacao(int codigo,Date dataEntrada,Time horaEntrada,Date dataSaida, Time horaSaida,String causas,String status) {
		this.codigo = codigo;
		this.dataEntrada = dataEntrada;
		this.horaEntrada = horaEntrada;
		this.dataSaida = dataSaida;
		this.horaSaida = horaSaida;
		this.causas = causas;
		this.status = status;
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Time getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(Time horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Time getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(Time horaSaida) {
		this.horaSaida = horaSaida;
	}
	public String getCausas() {
		return causas;
	}
	public void setCausas(String causas) {
		this.causas = causas;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Leito getLeito() {
		return leito;
	}
	public void setLeito(Leito leito) {
		this.leito = leito;
	}
	
}