package Persistencia;

import java.util.ArrayList;

import Dominio.Equipamento;
import Dominio.Leito;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LeitoDAO {
	
	private Conexao c;
	private String BLO = "SELECT * FROM leito WHERE ocupado = 'true';";
	private String BLD = "SELECT * FROM leito WHERE ocupado = 'false';";
	private String CL = "INSERT INTO leito(numero,tipo,ocupado) VALUES (?,?,'false');";
	private String RL = "DELETE FROM leito WHERE numero = ?";
	private String AL = " UPDATE leito SET numero = ? ,tipo = ? WHERE numero = ? ";
	private String BL = "SELECT * FROM leito WHERE numero =?;";
	private String REL = "SELECT * FROM leito;";
	private String VEO = "SELECT ocupado FROM leito WHERE numero = ? ;";
	private String OL = "UPDATE leito SET ocupado = 'true' WHERE numero =?";
	private String DL = "UPDATE leito SET ocupado = 'false' WHERE numero =?";
	
	
	
	
	
	public LeitoDAO() {
		c = new Conexao("postgres","123","jdbc:postgresql://localhost:5432/Hospital");
		
	}
	
	public ArrayList<Leito> buscarLeitosOcupados(){
		
		Leito l;
		ArrayList<Leito> LeitosOcupados = new ArrayList<Leito>();
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
		
		try {
			c.conectar();
			Statement instrucao = c.getminhaconexao().createStatement();
			ResultSet rs = instrucao.executeQuery(BLO);
			while(rs.next()) {
				l = new Leito(rs.getInt("numero"),rs.getString("tipo"),rs.getBoolean("ocupado"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(l.getnumero());
				l.setequipamentos(equipamentos);
				LeitosOcupados.add(l);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na Busca de Leitos Ocupados.");
		}
		return LeitosOcupados;
	}
	
	public ArrayList<Leito> buscarLeitosDisponiveis(){
		
		Leito l;
		ArrayList<Leito> LeitosDisponiveis = new ArrayList<Leito>();
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
		
		try {
			c.conectar();
			Statement instrucao = c.getminhaconexao().createStatement();
			ResultSet rs = instrucao.executeQuery(BLD);
			while(rs.next()) {
				l = new Leito(rs.getInt("numero"),rs.getString("tipo"),rs.getBoolean("ocupado"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(l.getnumero());
				l.setequipamentos(equipamentos);
				LeitosDisponiveis.add(l);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na Busca de Leitos Ocupados."+e.getMessage());
		}
		return LeitosDisponiveis;
	}
		
	public void criarLeito(Leito l) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(CL);
			instrucao.setInt(1,l.getnumero());
			instrucao.setString(2,l.gettipo());
			instrucao.execute();
			c.desconectar();
		}catch(Exception e){
			System.out.println("Erro na Criação do Leito.");
		}
	}
	
	public void removerLeito(int auxNumeroLeito) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(RL);
			instrucao.setInt(1,auxNumeroLeito);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Remoçao do Leito.");
		}
	}
	
	
	public void alterarLeito(Leito l,int auxNumeroLeito) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(AL);
			instrucao.setInt(1,l.getnumero());
			instrucao.setString(2,l.gettipo());
			instrucao.setInt(3,auxNumeroLeito);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro na Alteracao do Leito.");
		};
	}
	
	public Leito buscarLeitopeloNumero(int auxNumeroLeito) {
		
		Leito l = null;
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
		
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BL);
			instrucao.setInt(1, auxNumeroLeito);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				l = new Leito(rs.getInt("numero"),rs.getString("Tipo"), rs.getBoolean("ocupado"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(l.getnumero());
				l.setequipamentos(equipamentos);	
			}
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro na Busca.");
		}
		return l;
	}
	
	public ArrayList<Leito> relatoriodeLeitos(){
		
		ArrayList<Leito> leitos = new ArrayList<Leito>();
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
		
		Leito l;
		try {
			c.conectar();
			Statement instrucao = c.getminhaconexao().createStatement();
			ResultSet rs = instrucao.executeQuery(REL);
			while(rs.next()) {
				l = new Leito(rs.getInt("numero"),rs.getString("tipo"),rs.getBoolean("ocupado"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(l.getnumero());
				l.setequipamentos(equipamentos);
				leitos.add(l);
			}
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro no Relatorio de Leitos.");
		}
		return leitos;
	}
	
	public Boolean verificarOcupacao(int auxNumeroLeito) {
		Boolean ocupado=false;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(VEO);
			instrucao.setInt(1,auxNumeroLeito);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				ocupado = rs.getBoolean("ocupado");
			}
			c.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na Verificacao do Leito."+e.getMessage());
		}
		return ocupado;
	}
	
	public void ocuparLeito(int auxNumeroLeito) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(OL);
			instrucao.setInt(1, auxNumeroLeito);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Ocupação do Leito.");
		}
	}
	
	public void desocuparLeito(int auxNumeroLeito) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(DL);
			instrucao.setInt(1, auxNumeroLeito);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na desocupação do Leito.");
		}
	}
	
	
}
