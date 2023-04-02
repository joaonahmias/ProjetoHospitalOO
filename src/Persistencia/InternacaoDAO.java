package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Equipamento;
import Dominio.Internacao;
import Dominio.Leito;
import Dominio.Paciente;

public class InternacaoDAO {
	private Conexao c;
	
	private String EI = "INSERT INTO internacao(codigo,data_entrada,hora_entrada,causas,status,fk_leito,fk_paciente) VALUES(?,?,?,?,'A',?,?);";
	private String BIAP = "SELECT * FROM internacao WHERE fk_paciente = ? AND status = 'A';";
	private String SI = "UPDATE internacao SET data_saida=?,hora_saida=?,status='I' WHERE codigo=?;";
	private String BIAL  = "SELECT * FROM internacao WHERE fk_leito = ? AND status = 'A';";
	private String BIAC = "SELECT * FROM internacao WHERE codigo = ? AND status = 'A';";
	private String BIC = "SELECT * FROM internacao WHERE codigo =?;";
	private String BIP = "SELECT * FROM internacao WHERE fk_paciente = ?;";
	private String BIL = "SELECT * FROM internacao WHERE fk_leito = ?;";
	private String AIA = "UPDATE internacao SET codigo = ?,data_entrada=?,hora_entrada=?, causas=?,fk_leito=?,fk_paciente=? WHERE codigo=?";
	private String AII = "UPDATE internacao SET codigo = ?,data_entrada=?,hora_entrada=?,data_saida=?,hora_saida=?, causas=?,fk_leito=?,fk_paciente=? WHERE codigo=?";
	private String RIG = "SELECT * FROM internacao;";
	private String RIA = "SELECT * FROM internacao WHERE status = 'A';";
	private String RII = "SELECT * FROM internacao WHERE status = 'I';";
	private String EIL = "DELETE FROM internacao WHERE fk_leito = ?;";
	private String EIP = "DELETE FROM internacao WHERE fk_paciente = ?;";
	
	public InternacaoDAO() {
		c = new Conexao("postgres","123","jdbc:postgresql://localhost:5432/Hospital");
	}
	public void entradaDeInternacao(Internacao it, int auxNumeroLeito, String auxCpfPaciente) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(EI);
			instrucao.setInt(1, it.getCodigo());
			instrucao.setDate(2, it.getDataEntrada());
			instrucao.setTime(3, it.getHoraEntrada());
			instrucao.setString(4,it.getCausas());
			instrucao.setInt(5,auxNumeroLeito);
			instrucao.setString(6,auxCpfPaciente);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro Entrada de Internação."+e.getMessage());
			
		}
	}
	
	public Internacao BuscarInternacaoAtivapeloCodigo(int auxCodigoInternacao) {
		Internacao it = null;
		
		LeitoDAO lDAO = new LeitoDAO();
		Leito l;
	
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento>equipamentos = new ArrayList<Equipamento>();
		
		PacienteDAO pDAO = new PacienteDAO();
		Paciente p;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BIAC);
			instrucao.setInt(1, auxCodigoInternacao);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				it = new Internacao(rs.getInt("codigo"),rs.getDate("data_entrada"),rs.getTime("hora_entrada"),rs.getDate("data_saida"),rs.getTime("hora_saida"),rs.getString("causas"),rs.getString("status"));
				l = lDAO.buscarLeitopeloNumero( rs.getInt("fk_leito"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(rs.getInt("fk_leito"));
				l.setequipamentos(equipamentos);
				it.setLeito(l);
				p = pDAO.buscarPacientepeloCpf(rs.getString("fk_paciente"));
				it.setPaciente(p);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na Busca de Internações ativas pelo código.");
		}
		return it;
	}
	
	public Internacao BuscarInternacaoAtivadePaciente(String auxCpfPaciente) {
		Internacao it = null;
		
		LeitoDAO lDAO = new LeitoDAO();
		Leito l;
	
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento>equipamentos = new ArrayList<Equipamento>();
		
		PacienteDAO pDAO = new PacienteDAO();
		Paciente p;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BIAP);
			instrucao.setString(1, auxCpfPaciente);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				it = new Internacao(rs.getInt("codigo"),rs.getDate("data_entrada"),rs.getTime("hora_entrada"),rs.getDate("data_saida"),rs.getTime("hora_saida"),rs.getString("causas"),rs.getString("status"));
				l = lDAO.buscarLeitopeloNumero( rs.getInt("fk_leito"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(rs.getInt("fk_leito"));
				l.setequipamentos(equipamentos);
				it.setLeito(l);
				p = pDAO.buscarPacientepeloCpf(rs.getString("fk_paciente"));
				it.setPaciente(p);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na Busca de Internações ativas de Paciente.");
		}
		return it;
	}
	
	public Internacao buscarInternacaoAtivaPeloLeito(int auxNumeroLeito) {
		Internacao it = null;
		
		LeitoDAO lDAO = new LeitoDAO();
		Leito l;
	
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento>equipamentos = new ArrayList<Equipamento>();
		
		PacienteDAO pDAO = new PacienteDAO();
		Paciente p;
		
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BIAL);
			instrucao.setInt(1, auxNumeroLeito);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				it = new Internacao(rs.getInt("codigo"),rs.getDate("data_entrada"),rs.getTime("hora_entrada"),rs.getDate("data_saida"),rs.getTime("hora_saida"),rs.getString("causas"),rs.getString("status"));
				l = lDAO.buscarLeitopeloNumero( rs.getInt("fk_leito"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(rs.getInt("fk_leito"));
				l.setequipamentos(equipamentos);
				it.setLeito(l);
				p = pDAO.buscarPacientepeloCpf(rs.getString("fk_paciente"));
				it.setPaciente(p);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Busca de Internação.");
		}
		return it;
	}
	
	
	public void saidaDeInternacao(Internacao it) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(SI);
			instrucao.setDate(1, it.getDataSaida());
			instrucao.setTime(2, it.getHoraSaida());
			instrucao.setInt(3, it.getCodigo());
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Saída de Internação.");
		}
	}
	
	public Internacao buscarInternacaoPeloCodigo(int auxCodigoInternacao) {
		Internacao it = null;
		
		LeitoDAO lDAO = new LeitoDAO();
		Leito l;
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento>equipamentos = new ArrayList<Equipamento>();
		
		PacienteDAO pDAO = new PacienteDAO();
		Paciente p;
		
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BIC);
			instrucao.setInt(1, auxCodigoInternacao);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				it = new Internacao(rs.getInt("codigo"),rs.getDate("data_entrada"),rs.getTime("hora_entrada"),rs.getDate("data_saida"),rs.getTime("hora_saida"),rs.getString("causas"),rs.getString("status"));
				l = lDAO.buscarLeitopeloNumero(rs.getInt("fk_leito"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(rs.getInt("fk_leito"));
				l.setequipamentos(equipamentos);
				it.setLeito(l);
				p = pDAO.buscarPacientepeloCpf(rs.getString("fk_paciente"));
				it.setPaciente(p);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Busca de Internação.");
		}
		return it;
	}
	
	public ArrayList<Internacao>buscarInternacaoPeloPaciente(String auxCpfPaciente) {
		Internacao it = null;
		ArrayList<Internacao>internacoes = new ArrayList<Internacao>();
		
		LeitoDAO lDAO = new LeitoDAO();
		Leito l;
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento>equipamentos = new ArrayList<Equipamento>();
		
		PacienteDAO pDAO = new PacienteDAO();
		Paciente p;
		
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BIP);
			instrucao.setString(1, auxCpfPaciente);
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				it = new Internacao(rs.getInt("codigo"),rs.getDate("data_entrada"),rs.getTime("hora_entrada"),rs.getDate("data_saida"),rs.getTime("hora_saida"),rs.getString("causas"),rs.getString("status"));
				l = lDAO.buscarLeitopeloNumero(rs.getInt("fk_leito"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(rs.getInt("fk_leito"));
				l.setequipamentos(equipamentos);
				it.setLeito(l);
				p = pDAO.buscarPacientepeloCpf(rs.getString("fk_paciente"));
				it.setPaciente(p);
				internacoes.add(it);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Busca de Internação.");
		}
		return internacoes;
	}
	
	public ArrayList<Internacao>buscarInternacaoPeloLeito(int auxNumeroLeito) {
		Internacao it = null;
		ArrayList<Internacao>internacoes = new ArrayList<Internacao>();
		
		LeitoDAO lDAO = new LeitoDAO();
		Leito l;
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento>equipamentos = new ArrayList<Equipamento>();
		
		PacienteDAO pDAO = new PacienteDAO();
		Paciente p;
		
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(BIL);
			instrucao.setInt(1, auxNumeroLeito);
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				it = new Internacao(rs.getInt("codigo"),rs.getDate("data_entrada"),rs.getTime("hora_entrada"),rs.getDate("data_saida"),rs.getTime("hora_saida"),rs.getString("causas"),rs.getString("status"));
				l = lDAO.buscarLeitopeloNumero(rs.getInt("fk_leito"));
				equipamentos = elDAO.buscarEquipamentosnesseLeito(rs.getInt("fk_leito"));
				l.setequipamentos(equipamentos);
				it.setLeito(l);
				p = pDAO.buscarPacientepeloCpf(rs.getString("fk_paciente"));
				it.setPaciente(p);
				internacoes.add(it);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Busca de Internação.");
		}
		return internacoes;
	}
	
	
	
	
	public void AlterarInternacaoAtiva(Internacao it,int auxNumeroLeito, String auxCpfPaciente,int auxCodigoInternacao) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(AIA);
			instrucao.setInt(1, it.getCodigo());
			instrucao.setDate(2, it.getDataEntrada());
			instrucao.setTime(3, it.getHoraEntrada());
			instrucao.setString(4,it.getCausas());
			instrucao.setInt(5,auxNumeroLeito);
			instrucao.setString(6,auxCpfPaciente);
			instrucao.setInt(7,auxCodigoInternacao);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Alteração da Internação.");
		}
	}
	
	public void AlterarInternacaoInativa(Internacao it,int auxNumeroLeito, String auxCpfPaciente,int auxCodigoInternacao) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(AII);
			instrucao.setInt(1, it.getCodigo());
			instrucao.setDate(2, it.getDataEntrada());
			instrucao.setTime(3, it.getHoraEntrada());
			instrucao.setDate(4, it.getDataSaida());
			instrucao.setTime(5, it.getHoraSaida());
			instrucao.setString(6,it.getCausas());
			instrucao.setInt(7,auxNumeroLeito);
			instrucao.setString(8,auxCpfPaciente);
			instrucao.setInt(9,auxCodigoInternacao);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Alteração da Internação.");
		}
	}
	
	public ArrayList<Internacao> relatoriodeInternacoesGeral(){
		ArrayList<Internacao> internacoes = new ArrayList<Internacao>();
		Internacao it = null;
		
		LeitoDAO lDAO = new LeitoDAO();
		Leito l;
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento>equipamentos = new ArrayList<Equipamento>();
		
		PacienteDAO pDAO = new PacienteDAO();
		Paciente p;
		
		try {
			c.conectar();
			Statement instrucao = c.getminhaconexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RIG);
			while(rs.next()) {
				
					it = new Internacao(rs.getInt("codigo"),rs.getDate("data_entrada"),rs.getTime("hora_entrada"),rs.getDate("data_saida"),rs.getTime("hora_saida"),rs.getString("causas"),rs.getString("status"));
					l = lDAO.buscarLeitopeloNumero(rs.getInt("fk_leito"));
					equipamentos = elDAO.buscarEquipamentosnesseLeito(rs.getInt("fk_leito"));
					l.setequipamentos(equipamentos);
					it.setLeito(l);
					p = pDAO.buscarPacientepeloCpf(rs.getString("fk_paciente"));
					it.setPaciente(p);
					internacoes.add(it);
				
			}
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro no Relatorio de Leitos.");
		}
		return internacoes;
	}
	
	public ArrayList<Internacao> relatoriodeInternacoesAtivas(){
		ArrayList<Internacao> internacoes = new ArrayList<Internacao>();
		Internacao it = null;
		
		LeitoDAO lDAO = new LeitoDAO();
		Leito l;
		
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento>equipamentos = new ArrayList<Equipamento>();
		
		PacienteDAO pDAO = new PacienteDAO();
		Paciente p;
		
		try {
			c.conectar();
			Statement instrucao = c.getminhaconexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RIA);
			while(rs.next()) {
				
					it = new Internacao(rs.getInt("codigo"),rs.getDate("data_entrada"),rs.getTime("hora_entrada"),rs.getDate("data_saida"),rs.getTime("hora_saida"),rs.getString("causas"),rs.getString("status"));
					l = lDAO.buscarLeitopeloNumero(rs.getInt("fk_leito"));
					equipamentos = elDAO.buscarEquipamentosnesseLeito(rs.getInt("fk_leito"));
					l.setequipamentos(equipamentos);
					it.setLeito(l);
					p = pDAO.buscarPacientepeloCpf(rs.getString("fk_paciente"));
					it.setPaciente(p);
					internacoes.add(it);
				
			}
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro no Relatorio de Leitos.");
		}
		return internacoes;
	}
	
	public ArrayList<Internacao> relatoriodeInternacoesInativas(){
		ArrayList<Internacao> internacoes = new ArrayList<Internacao>();
		Internacao it = null;
		
		LeitoDAO lDAO = new LeitoDAO();
		Leito l;
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		ArrayList<Equipamento>equipamentos = new ArrayList<Equipamento>();
		
		PacienteDAO pDAO = new PacienteDAO();
		Paciente p;
		
		try {
			c.conectar();
			Statement instrucao = c.getminhaconexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RII);
			while(rs.next()) {
					it = new Internacao(rs.getInt("codigo"),rs.getDate("data_entrada"),rs.getTime("hora_entrada"),rs.getDate("data_saida"),rs.getTime("hora_saida"),rs.getString("causas"),rs.getString("status"));
					l = lDAO.buscarLeitopeloNumero(rs.getInt("fk_leito"));
					equipamentos = elDAO.buscarEquipamentosnesseLeito(rs.getInt("fk_leito"));
					l.setequipamentos(equipamentos);
					it.setLeito(l);
					p = pDAO.buscarPacientepeloCpf(rs.getString("fk_paciente"));
					it.setPaciente(p);
					internacoes.add(it);
			}
			c.desconectar();
		}catch(Exception e){
			System.out.println("\nErro no Relatorio de Leitos.");
		}
		return internacoes;
	}
	
	public void removerInternacoesDeLeitoemCascata(int auxNumeroLeito) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(EIL);
			instrucao.setInt(1, auxNumeroLeito);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Eclusão das Internacões de Leito.");
		}
	}
	
	public void removerInternacoesDePacienteemCascata(String auxCpfPaciente) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getminhaconexao().prepareStatement(EIP);
			instrucao.setString(1, auxCpfPaciente);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("\nErro na Eclusão das internações de paciente.");
		}
	}
	
}
