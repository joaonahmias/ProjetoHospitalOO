package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Paciente;

public class PacienteDAO {
	
	private Conexao c;
	
	private String CP = "INSERT INTO paciente(cpf,nome,telefone,endereco,data_nascimento) VALUES (?,?,?,?,?)";
	private String BPC = "SELECT * FROM paciente where cpf = ?";
	private String DP= "DELETE FROM paciente WHERE cpf = ?";
	private String BPN = "SELECT * FROM Paciente WHERE UPPER(nome) LIKE ?";
	private String RP = "SELECT * FROM paciente";
	private String AP = "UPDATE paciente SET cpf = ?,nome = ? ,telefone = ? ,endereco = ? ,data_nascimento = ? WHERE cpf = ? ";
	
	
	public PacienteDAO(){
		c = new Conexao("postgres","123","jdbc:postgresql://localhost:5432/Hospital");
	}
	
	public void criarPaciente(Paciente p) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(CP);
			instrucao.setString(1, p.getcpf());
			instrucao.setString(2, p.getnome());
			instrucao.setString(3,p.gettelefone());
			instrucao.setString(4, p.getendereco());
			instrucao.setDate(5,p.getdataNascimento());	
			instrucao.execute();
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro na criação de paciente."+e.getMessage());
		}
	}
	
	public Paciente buscarPacientepeloCpf(String auxCpfPaciente) {
		Paciente p = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BPC);
			instrucao.setString(1, auxCpfPaciente);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				p = new Paciente(rs.getString("cpf"),rs.getString("nome"),rs.getString("telefone"),rs.getString("endereco"),rs.getDate("data_nascimento"));
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na busca");
		}
		return p;
	}
	
	
	public void removerPaciente(String auxCpfPaciente) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(DP);
			instrucao.setString(1,auxCpfPaciente);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro na exclusão do paciente."+e.getMessage());
		}
	}
	
	public ArrayList<Paciente> buscarPacientepeloNome(String auxNomePaciente){
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		Paciente p;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BPN);
			instrucao.setString(1, "%"+auxNomePaciente+"%");
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				p = new Paciente(rs.getString("cpf"),rs.getString("nome"),rs.getString("telefone"),rs.getString("endereco"),rs.getDate("data_nascimento"));
				pacientes.add(p);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Busca de Paciente");
		}
		return pacientes;
		
	}
	
	public ArrayList<Paciente> relatorioPacientes (){
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		Paciente p;
		try {
			c.conectar();
			Statement instrucao = c.getminhaconexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RP);
			while(rs.next()) {
				p = new Paciente(rs.getString("cpf"),rs.getString("nome"),rs.getString("telefone"),rs.getString("endereco"),rs.getDate("data_nascimento"));
				pacientes.add(p);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Busca de Paciente");
		}
		return pacientes;
		
	}
	
	public void alterarPaciente(Paciente p, String auxCpfPaciente) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(AP);
			instrucao.setString(1, p.getcpf());
			instrucao.setString(2, p.getnome());
			instrucao.setString(3,p.gettelefone());
			instrucao.setString(4, p.getendereco());
			instrucao.setDate(5,p.getdataNascimento());
			instrucao.setString(6,auxCpfPaciente);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro na alteração de paciente."+e.getMessage());
		}
	}
	
	
	
}
