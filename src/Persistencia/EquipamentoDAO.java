package Persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Equipamento;


public class EquipamentoDAO {
	private Conexao c;
	private String BEQ = "SELECT * FROM equipamento";
	private String CEQ = "INSERT INTO equipamento (codigo,nome) VALUES (?,?)";
	private String REQ = "DELETE FROM equipamento WHERE codigo = ?";
	private String BC = "SELECT * FROM equipamento WHERE codigo = ?";
	private String AE = "UPDATE equipamento SET codigo = ? ,nome = ? WHERE codigo = ?";
	private String BN = "SELECT * FROM equipamento WHERE UPPER(nome) LIKE ?;";
	
	public EquipamentoDAO() {
		c = new Conexao ("postgres", "123", "jdbc:postgresql://localhost:5432/Hospital");
	}
	
	public ArrayList<Equipamento> emitirRelatorioEquipamentos() {
		Equipamento eq;
		ArrayList<Equipamento> equipamentos = new ArrayList <Equipamento>();
		
		try {
			c.conectar();
			Statement instrucao = c.getminhaconexao().createStatement();
			ResultSet rs = instrucao.executeQuery(BEQ);
			
			while(rs.next()){
				eq = new Equipamento(rs.getInt("codigo"),rs.getString("nome"));
				equipamentos.add(eq);
			}
			
			c.desconectar();
			
			
		}catch(Exception e) {
			System.out.println("Erro no Relatório");
		};
		
		return equipamentos ;
	}
	
	public void CriarEquipamento(Equipamento eq) {
			try {
				c.conectar();
				PreparedStatement instrucao = c.getminhaconexao().prepareStatement(CEQ);
				instrucao.setInt(1, eq.getcodigo());
				instrucao.setString(2, eq.getnome());
				instrucao.execute();
				c.desconectar();
			}catch(Exception e) {
				System.out.println("Erro no Insert ");
			}

	}
	
	public void removerEquipamento(int auxCodigoEquipamento) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(REQ);
			instrucao.setInt(1,auxCodigoEquipamento);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro no Delete");
		}
	}
	
	
	public Equipamento buscarEquipamentopeloCodigo(int auxCodigoEquipamento) {
		Equipamento eq = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BC);
			instrucao.setInt(1, auxCodigoEquipamento);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				eq = new Equipamento(rs.getInt("codigo"),rs.getString("nome"));
			}
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro na Busca."+e.getMessage());
		}
		return eq;
	}
	
	public void alterarEquipamento(Equipamento eq, int auxCodigoEquipamento) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(AE);
			instrucao.setInt(1,eq.getcodigo());
			instrucao.setString(2,eq.getnome());
			instrucao.setInt(3,auxCodigoEquipamento);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro na Alteracao do Equipamento.");
		};
	}
	
	public ArrayList<Equipamento> buscarEquipamentopeloNome(String auxNomeEquipamento){
		Equipamento eq;
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BN);
			instrucao.setString(1,"%"+auxNomeEquipamento+"%");
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
			 eq = new Equipamento(rs.getInt("codigo"),rs.getString("nome"));
			 equipamentos.add(eq);
			}
		}catch(Exception e) {
			System.out.println("\nErro na Busca do Equipamento.");
		}
		return equipamentos;
	}
	
}
