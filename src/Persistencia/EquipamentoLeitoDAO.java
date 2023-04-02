package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Dominio.Equipamento;
import Dominio.Leito;



public class EquipamentoLeitoDAO {
	
	private Conexao c;
	private String AEL = "INSERT INTO equipamento_leito  VALUES (?,?);";
	private String REL = "DELETE FROM equipamento_leito WHERE fk_equipamento = ? and fk_leito = ?";
	private String BEL = "SELECT e.codigo,e.nome FROM equipamento_leito x, Equipamento e WHERE x.fk_leito = ? AND x.fk_equipamento = e.codigo;";
	private String BLE = "SELECT l.numero,l.tipo, l.ocupado FROM leito l, equipamento e,equipamento_leito x WHERE e.codigo = ? and x.fk_equipamento = e.codigo and x.fk_leito = l.numero;";
	private String RLC = "DELETE FROM equipamento_leito WHERE fk_leito =?";
	private String REC = "DELETE FROM equipamento_leito WHERE fk_equipamento =?";
	private String VEL = "SELECT * FROM equipamento_leito WHERE fk_equipamento = ? and fk_leito = ?";
	
	public EquipamentoLeitoDAO (){
		c = new Conexao("postgres","123","jdbc:postgresql://localhost:5432/Hospital");
	}
	
	
	public void adicionarEquipamentoaoLeito(int auxCodigoEquipamento, int auxNumeroLeito) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(AEL);
			instrucao.setInt(1,auxCodigoEquipamento);
			instrucao.setInt(2,auxNumeroLeito);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Inclusao "+e.getMessage());
		}
		
		
	}
	public void removerEquipamentodoLeito(int auxCodigoEquipamento,int auxNumeroLeito) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(REL);
			instrucao.setInt(1, auxCodigoEquipamento);
			instrucao.setInt(2, auxNumeroLeito);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na remoção" + e.getMessage());
		}
	}
	
	public ArrayList <Equipamento> buscarEquipamentosnesseLeito(int auxNumeroLeito){
		Equipamento eq;
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>() ;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BEL);
			instrucao.setInt(1, auxNumeroLeito);
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				eq = new Equipamento(rs.getInt("codigo"),rs.getString("nome"));
				equipamentos.add(eq);
			}
			c.desconectar();
		} catch (Exception e) {
			System.out.println("\nErro na Busca de Equipamentos associados a esse Leito");
		}
		return equipamentos;
	}
	
	public ArrayList<Leito> buscarLeitoscomesseEquipamento(int auxCodigoEquipamento){
		Leito l;
		ArrayList<Leito> leitos = new ArrayList<Leito>() ;
		
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
		
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BLE);
			instrucao.setInt(1, auxCodigoEquipamento);
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				l = new Leito(rs.getInt("numero"),rs.getString("tipo"),rs.getBoolean("ocupado"));
				equipamentos = buscarEquipamentosnesseLeito(l.getnumero());
				l.setequipamentos(equipamentos);	
				leitos.add(l);
			}
		c.desconectar();
		} catch (Exception e) {
			System.out.println("\nErro na Busca de Leitos associados a esse Equipamento.");
		}
		return leitos;
		
	}
	
	
	public void removerEquipamentosdeLeitoemCascata(int auxNumeroLeito) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(RLC);
			instrucao.setInt(1, auxNumeroLeito);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na exclusao em Cascata do Leito");
		}
	}
	
	public void removerEquipamentoemCascata(int auxCodigoEquipamento) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(REC);
			instrucao.setInt(1,  auxCodigoEquipamento);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na exclusao em Cascata do Equipamento");
		}
	}
	
	public Boolean verificarEquipamentoLeito(int auxCodigoEquipamento, int auxNumeroLeito) {
		Boolean verificacao = false;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(VEL);
			instrucao.setInt(1,auxCodigoEquipamento);
			instrucao.setInt(2,auxNumeroLeito);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				verificacao = true;
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Inclusao "+e.getMessage());
		}
		return verificacao;
	}
	
}
