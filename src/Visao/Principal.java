package Visao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Equipamento;
import Dominio.Internacao;
import Dominio.Leito;
import Dominio.Paciente;
import Persistencia.LeitoDAO;
import Persistencia.PacienteDAO;
import Persistencia.EquipamentoDAO;
import Persistencia.EquipamentoLeitoDAO;
import Persistencia.InternacaoDAO;


public class Principal {
	
	
	public static void main(String [] args) {
		
		Scanner ler = new Scanner(System.in);
		int opPrincipal,opSecundario,opTerciario,i,i2;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		
		LeitoDAO lDAO = new LeitoDAO ();
		ArrayList<Leito>leitos = new ArrayList<Leito>();
		Leito l,l2;
		int auxNumeroLeito;
		Boolean ocupado;
		
		EquipamentoDAO eqDAO = new EquipamentoDAO();
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
		Equipamento eq,eq2;
		int auxCodigoEquipamento;
		String auxNomeEquipamento;
		
		EquipamentoLeitoDAO elDAO = new EquipamentoLeitoDAO();
		
		Paciente p;
		PacienteDAO pDAO = new PacienteDAO();
		String auxCpfPaciente;
		ArrayList<Paciente> pacientes =  new ArrayList<Paciente>();
		String auxNomePaciente;
		
		
		Internacao it,it2,itativa;
		InternacaoDAO itDAO = new InternacaoDAO();
		int auxCodigoInternacao;
		String status;
		ArrayList<Internacao> internacoes = new ArrayList<Internacao>();
		
		do {
			System.out.println("\nMenu Principal");
			System.out.println("--------------------------------------------------------------");
			System.out.println("1 - Internação");
			System.out.println("2 - Leitos");
			System.out.println("3 - Equipamentos");
			System.out.println("4 - Pacientes");
			System.out.println("5 - Sair");
			System.out.println("--------------------------------------------------------------");
			opPrincipal = ler.nextInt();
			switch(opPrincipal) {
				case 1:System.out.println("\nInternação");
				System.out.println("--------------------------------------------------------------");
				System.out.println("1 - Buscar Internação");
				System.out.println("2 - Entrada de Internacao");
				System.out.println("3 - Saida de Internacao");
				System.out.println("4 - Alterar Internação");
				System.out.println("5 - Relatório de Internações");
				System.out.println("6 - Voltar ao Menu Principal");
				System.out.println("--------------------------------------------------------------");
				opSecundario = ler.nextInt();
				switch(opSecundario) {
					case 1:System.out.println("\nBuscar Internação");
					System.out.println("\n--------------------------------------------------------------");
					System.out.println("1 - Buscar internação pelo código");
					System.out.println("2 - Buscar internação por paciente");
					System.out.println("3 - Buscar internação pelo leito");
					System.out.println("4 - Voltar ao Menu Principal");
					System.out.println("--------------------------------------------------------------");
					opTerciario = ler.nextInt();
					switch(opTerciario) {
						case 1:System.out.println("\nDigite o código da internação");
						auxCodigoInternacao = ler.nextInt();
						ler.nextLine();
						it = itDAO. buscarInternacaoPeloCodigo(auxCodigoInternacao);
						if(it==null) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Internação não encontrada.");
						}
						else {
							System.out.println("\nInternação encontrada.\n");
							System.out.println("--------------------------------------------------------------");
							System.out.println("Código: "+it.getCodigo());
							System.out.println("Data de entrada: "+it.getDataEntrada());
							System.out.println("Hora de entrada: "+it.getHoraEntrada());
							System.out.println("Data de saída: "+it.getDataSaida());
							System.out.println("Hora de saída: "+it.getHoraSaida());
							System.out.println("Causas: "+it.getCausas());
							System.out.println("Status: "+it.getStatus());
							System.out.println("\nPaciente");
							System.out.println("\nNome: "+it.getPaciente().getnome());
							System.out.println("Cpf: "+it.getPaciente().getcpf());
							System.out.println("Endereco: "+it.getPaciente().getendereco());
							System.out.println("Telefone: "+it.getPaciente().gettelefone());
							System.out.println("Data de Nascimento: "+it.getPaciente().getdataNascimento());
							System.out.println("\nLeito");
							System.out.println("\nNumero: "+it.getLeito().getnumero());
							System.out.println("Tipo: "+it.getLeito().gettipo());
							System.out.println("Ocupado: "+it.getLeito().getocupado());
							equipamentos = it.getLeito().getequipamentos();
							for(i=0;i<equipamentos.size();i++) {
								if(i==0) {
									System.out.println("\nEquipamentos");
								}
								System.out.println("\nCódigo: "+equipamentos.get(i).getcodigo());
								System.out.println("Nome: "+equipamentos.get(i).getnome());
							}
						}
						break;
						case 2:ler.nextLine();
						System.out.println("\nDigite o Cpf do paciente: ");
						auxCpfPaciente = ler.nextLine();
						p = pDAO.buscarPacientepeloCpf(auxCpfPaciente);
						if(p==null) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Paciente não encontrado.");
						}
						else {
							internacoes = itDAO.buscarInternacaoPeloPaciente(auxCpfPaciente);
							if(internacoes.size()<=0) {
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Internação(es) não encontrada(s).");
							}
							else {
								for(i=0;i<internacoes .size();i++) {
									if(i==0) {
										System.out.println("\nInternação(es) encontrada(s).\n");
									}
									System.out.println("--------------------------------------------------------------");
									System.out.println("Código: "+internacoes.get(i).getCodigo());
									System.out.println("Data de entrada: "+internacoes.get(i).getDataEntrada());
									System.out.println("Hora de entrada: "+internacoes.get(i).getHoraEntrada());
									System.out.println("Data de saída: "+internacoes.get(i).getDataSaida());
									System.out.println("Hora de saída: "+internacoes.get(i).getHoraSaida());
									System.out.println("Causas: "+internacoes.get(i).getCausas());
									System.out.println("Status: "+internacoes.get(i).getStatus());
									System.out.println("\nPaciente");
									System.out.println("\nNome: "+internacoes.get(i).getPaciente().getnome());
									System.out.println("Cpf: "+internacoes.get(i).getPaciente().getcpf());
									System.out.println("Endereco: "+internacoes.get(i).getPaciente().getendereco());
									System.out.println("Telefone: "+internacoes.get(i).getPaciente().gettelefone());
									System.out.println("Data de Nascimento: "+internacoes.get(i).getPaciente().getdataNascimento());
									System.out.println("\nLeito");
									System.out.println("\nNumero: "+internacoes.get(i).getLeito().getnumero());
									System.out.println("Tipo: "+internacoes.get(i).getLeito().gettipo());
									System.out.println("Ocupado: "+internacoes.get(i).getLeito().getocupado());
									equipamentos = internacoes.get(i).getLeito().getequipamentos();
									for(i2=0;i2<equipamentos.size();i2++) {
										if(i2==0) {
											System.out.println("\nEquipamentos");
										}
										System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
										System.out.println("Nome: "+equipamentos.get(i2).getnome());
									}
								}
								
							
							}
						}
						break;
						case 3:
						System.out.println("\nDigite o número do leito: ");
						auxNumeroLeito = ler.nextInt();
						ler.nextLine();
						l = lDAO.buscarLeitopeloNumero(auxNumeroLeito);
						if(l==null) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Leito não encontrado");
						}
						else {
							internacoes = itDAO.buscarInternacaoPeloLeito(auxNumeroLeito);
							if(internacoes.size()<=0) {
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Internação(es) não encontrada(s).");
							}
							else {
								for(i=0;i<internacoes .size();i++) {
									if(i==0) {
										System.out.println("\nInternação(es) encontrada(s).\n");
									}
									System.out.println("--------------------------------------------------------------");
									System.out.println("Código: "+internacoes.get(i).getCodigo());
									System.out.println("Data de entrada: "+internacoes.get(i).getDataEntrada());
									System.out.println("Hora de entrada: "+internacoes.get(i).getHoraEntrada());
									System.out.println("Data de saída: "+internacoes.get(i).getDataSaida());
									System.out.println("Hora de saída: "+internacoes.get(i).getHoraSaida());
									System.out.println("Causas: "+internacoes.get(i).getCausas());
									System.out.println("Status: "+internacoes.get(i).getStatus());
									System.out.println("\nPaciente");
									System.out.println("\nNome: "+internacoes.get(i).getPaciente().getnome());
									System.out.println("Cpf: "+internacoes.get(i).getPaciente().getcpf());
									System.out.println("Endereco: "+internacoes.get(i).getPaciente().getendereco());
									System.out.println("Telefone: "+internacoes.get(i).getPaciente().gettelefone());
									System.out.println("Data de Nascimento: "+internacoes.get(i).getPaciente().getdataNascimento());
									System.out.println("\nLeito");
									System.out.println("\nNumero: "+internacoes.get(i).getLeito().getnumero());
									System.out.println("Tipo: "+internacoes.get(i).getLeito().gettipo());
									System.out.println("Ocupado: "+internacoes.get(i).getLeito().getocupado());
									equipamentos = internacoes.get(i).getLeito().getequipamentos();
									for(i2=0;i2<equipamentos.size();i2++) {
										if(i2==0) {
											System.out.println("\nEquipamentos");
										}
										System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
										System.out.println("Nome: "+equipamentos.get(i2).getnome());
									}
								}
							}
						}
						
						break;
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 2:
						System.out.println("\nDigite o código da internação: ");
						auxCodigoInternacao = ler.nextInt();
						it = itDAO.buscarInternacaoPeloCodigo(auxCodigoInternacao);
						if(it==null) {
							ler.nextLine();
							it = new Internacao();
							it.setCodigo(auxCodigoInternacao);
							System.out.println("Digite a data de entrada da internação: ");
							try {
								it.setDataEntrada(new java.sql.Date(sdf.parse(ler.nextLine()).getTime()));
							
							} catch (Exception e) {
								System.out.println("\nData inválida.");
								break;
							}
							System.out.println("Digite a hora de entrada da Internação: ");
							try {
								it.setHoraEntrada(new java.sql.Time(sdf2.parse(ler.nextLine()).getTime()));
							
							} catch (Exception e) {
								System.out.println("\nHora inválida.");
								break;
							}
							System.out.println("Digite o Cpf do paciente: ");
							auxCpfPaciente = ler.nextLine();
							p = pDAO.buscarPacientepeloCpf(auxCpfPaciente);
							if(p==null) {
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Paciente não encontrado.");
							}
							else {
								itativa = itDAO.BuscarInternacaoAtivadePaciente(auxCpfPaciente);
								if(itativa == null) {
									System.out.println("Digite a causa da internação: ");
									it.setCausas(ler.nextLine());
									System.out.println("Digite o Leito da Internação: ");
									auxNumeroLeito = ler.nextInt();
									ler.nextLine();
									l = lDAO.buscarLeitopeloNumero(auxNumeroLeito);
									if(l==null) {
										System.out.println("\n--------------------------------------------------------------");
										System.out.println("Leito não encontrado");
									}else {
										ocupado = lDAO.verificarOcupacao(auxNumeroLeito);
										if(ocupado==true) {
											System.out.println("\nLeito Ocupado\n");
											itativa = itDAO.buscarInternacaoAtivaPeloLeito(auxNumeroLeito);
											System.out.println("--------------------------------------------------------------");
											System.out.println("Numero: "+itativa.getLeito().getnumero());
											System.out.println("Tipo: "+itativa.getLeito().gettipo());
											System.out.println("Ocupado: "+itativa.getLeito().getocupado());
											System.out.println("\nInternação");
											System.out.println("\nCódigo: "+itativa.getCodigo());
											System.out.println("Status: "+itativa.getStatus());
											System.out.println("\nCpf: "+itativa.getPaciente().getcpf());
											System.out.println("\nNome: "+itativa.getPaciente().getnome());
										}
										else {
											itDAO.entradaDeInternacao(it, auxNumeroLeito, auxCpfPaciente);
											lDAO.ocuparLeito(auxNumeroLeito);
											System.out.println("\n--------------------------------------------------------------");
											System.out.println("Operação realizada.");
										}
										
									}
								}
								else {
									System.out.println("\nPaciente já internado.\n");
									System.out.println("--------------------------------------------------------------");
									System.out.println("Código: "+itativa.getCodigo());
									System.out.println("Data de entrada: "+itativa.getDataEntrada());
									System.out.println("Hora de entrada: "+itativa.getHoraEntrada());
									System.out.println("Data de saída: "+itativa.getDataSaida());
									System.out.println("Hora de saída: "+itativa.getHoraSaida());
									System.out.println("Causas: "+itativa.getCausas());
									System.out.println("Status: "+itativa.getStatus());
									System.out.println("\nPaciente");
									System.out.println("\nNome: "+itativa.getPaciente().getnome());
									System.out.println("Cpf: "+itativa.getPaciente().getcpf());
									System.out.println("Endereco: "+itativa.getPaciente().getendereco());
									System.out.println("Telefone: "+itativa.getPaciente().gettelefone());
									System.out.println("Data de Nascimento: "+itativa.getPaciente().getdataNascimento());
									System.out.println("\nLeito");
									System.out.println("\nNumero: "+itativa.getLeito().getnumero());
									System.out.println("Tipo: "+itativa.getLeito().gettipo());
							
								}
							
							}
						
						}
						else {
							ler.nextLine();
							System.out.println("\nCódigo de Internação já cadastrado.\n");
							System.out.println("--------------------------------------------------------------");
							System.out.println("Código: "+it.getCodigo());
							System.out.println("Data de entrada: "+it.getDataEntrada());
							System.out.println("Hora de entrada: "+it.getHoraEntrada());
							System.out.println("Data de saída: "+it.getDataSaida());
							System.out.println("Hora de saída: "+it.getHoraSaida());
							System.out.println("Causas: "+it.getCausas());
							System.out.println("Status: "+it.getStatus());
							System.out.println("\nPaciente");
							System.out.println("\nNome: "+it.getPaciente().getnome());
							System.out.println("Cpf: "+it.getPaciente().getcpf());
							System.out.println("Endereco: "+it.getPaciente().getendereco());
							System.out.println("Telefone: "+it.getPaciente().gettelefone());
							System.out.println("Data de Nascimento: "+it.getPaciente().getdataNascimento());
							System.out.println("\nLeito");
							System.out.println("\nNumero: "+it.getLeito().getnumero());
							System.out.println("Tipo: "+it.getLeito().gettipo());
							System.out.println("Ocupado: "+it.getLeito().getocupado());
							equipamentos = it.getLeito().getequipamentos();
							for(i=0;i<equipamentos.size();i++) {
								if(i==0) {
									System.out.println("\nEquipamentos");
								}
								System.out.println("\nCódigo: "+equipamentos.get(i).getcodigo());
								System.out.println("Nome: "+equipamentos.get(i).getnome());
							}
						}
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nPressione enter para continuar");
						ler.nextLine();
					break;
					case 3:
					System.out.println("\nDigite o código da internação");
					auxCodigoInternacao = ler.nextInt();
					ler.nextLine();
					it = itDAO.buscarInternacaoPeloCodigo(auxCodigoInternacao);
					if(it==null) {
						ler.nextLine();
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Internação não encontrada.");
					}
					else {
					System.out.println("\n--------------------------------------------------------------");
					System.out.println("Código: "+it.getCodigo());
					System.out.println("Data de entrada: "+it.getDataEntrada());
					System.out.println("Hora de entrada: "+it.getHoraEntrada());
					System.out.println("Data de saída: "+it.getDataSaida());
					System.out.println("Hora de saída: "+it.getHoraSaida());
					System.out.println("Causas: "+it.getCausas());
					System.out.println("Status: "+it.getStatus());
					System.out.println("\nPaciente");
					System.out.println("\nNome: "+it.getPaciente().getnome());
					System.out.println("Cpf: "+it.getPaciente().getcpf());
					System.out.println("Endereco: "+it.getPaciente().getendereco());
					System.out.println("Telefone: "+it.getPaciente().gettelefone());
					System.out.println("Data de Nascimento: "+it.getPaciente().getdataNascimento());
					System.out.println("\nLeito");
					System.out.println("\nNumero: "+it.getLeito().getnumero());
					System.out.println("Tipo: "+it.getLeito().gettipo());
					System.out.println("Ocupado: "+it.getLeito().getocupado());
					equipamentos = it.getLeito().getequipamentos();
					for(i=0;i<equipamentos.size();i++) {
						if(i==0) {
							System.out.println("\nEquipamentos");
						}
						System.out.println("\nCódigo: "+equipamentos.get(i).getcodigo());
						System.out.println("Nome: "+equipamentos.get(i).getnome());
					}
					System.out.println("--------------------------------------------------------------");
						it2 = itDAO.BuscarInternacaoAtivapeloCodigo(auxCodigoInternacao);
						if(it2==null) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Internação já finalizada.");
						}
						else {
							System.out.println("\nDigite a data de saída da internação: ");
							try {
								it.setDataSaida(new java.sql.Date(sdf.parse(ler.nextLine()).getTime()));
							
							} catch (Exception e) {
								System.out.println("\nData inválida.");
								break;
							}
							System.out.println("Digite a hora de saída da Internação: ");
							try {
								it.setHoraSaida(new java.sql.Time(sdf2.parse(ler.nextLine()).getTime()));
							
							} catch (Exception e) {
								System.out.println("\nHora inválida.");
								break;
							}
							itDAO.saidaDeInternacao(it);
							lDAO.desocuparLeito(it.getLeito().getnumero());
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Operação realizada");
						}
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 4:
					System.out.println("\nAlterar leitos");
					System.out.println("--------------------------------------------------------------");
					System.out.println("1 - Alterar internação ativa");
					System.out.println("2 - Alterar internação Inativa");
					System.out.println("3 - Voltar ao Menu Principal");
					System.out.println("--------------------------------------------------------------");
					opTerciario =ler.nextInt();
					if(opTerciario==1) {
						System.out.println("\nDigite o código da internação que deseja alterar: ");
						auxCodigoInternacao = ler.nextInt();
						it = itDAO.buscarInternacaoPeloCodigo(auxCodigoInternacao);
						if(it==null|| it.getStatus().equals("I")) {
							ler.nextLine();
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Código não encontrado para uma internação Ativa");
						}
						else {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Código: "+it.getCodigo());
							System.out.println("Data de entrada: "+it.getDataEntrada());
							System.out.println("Hora de entrada: "+it.getHoraEntrada());
							System.out.println("Data de saída: "+it.getDataSaida());
							System.out.println("Hora de saída: "+it.getHoraSaida());
							System.out.println("Causas: "+it.getCausas());
							System.out.println("Status: "+it.getStatus());
							System.out.println("\nPaciente");
							System.out.println("\nNome: "+it.getPaciente().getnome());
							System.out.println("Cpf: "+it.getPaciente().getcpf());
							System.out.println("Endereco: "+it.getPaciente().getendereco());
							System.out.println("Telefone: "+it.getPaciente().gettelefone());
							System.out.println("Data de Nascimento: "+it.getPaciente().getdataNascimento());
							System.out.println("\nLeito");
							System.out.println("\nNumero: "+it.getLeito().getnumero());
							System.out.println("Tipo: "+it.getLeito().gettipo());
							System.out.println("Ocupado: "+it.getLeito().getocupado());
							equipamentos = it.getLeito().getequipamentos();
							for(i=0;i<equipamentos.size();i++) {
								if(i==0) {
									System.out.println("\nEquipamentos");
								}
								System.out.println("\nCódigo: "+equipamentos.get(i).getcodigo());
								System.out.println("Nome: "+equipamentos.get(i).getnome());
							}
							System.out.println("--------------------------------------------------------------");
							it2 = new Internacao();
							System.out.println("\nDigite o nova código da internação: ");
							auxCodigoInternacao = ler.nextInt();
							it2 = itDAO.buscarInternacaoPeloCodigo(auxCodigoInternacao);
							if(it2==null||it.getCodigo()==auxCodigoInternacao) {
								it2 = new Internacao();
								it2.setCodigo(auxCodigoInternacao);
								ler.nextLine();
								System.out.println("Digite a nova data de entrada da internação: ");
								try {
									it2.setDataEntrada(new java.sql.Date(sdf.parse(ler.nextLine()).getTime()));
								
								} catch (Exception e) {
									System.out.println("\nData inválida.");
									break;
								}
								System.out.println("Digite a hora de entrada da Internação: ");
								try {
									it2.setHoraEntrada(new java.sql.Time(sdf2.parse(ler.nextLine()).getTime()));
								} catch (Exception e) {
									System.out.println("\nHora inválida.");
									break;
								}
								System.out.println("Digite o cpf do novo paciente: ");
								auxCpfPaciente = ler.nextLine();
								p = pDAO.buscarPacientepeloCpf(auxCpfPaciente);
								if(p==null) {
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Paciente não encontrado.");
								}
								else {
									itativa = itDAO.BuscarInternacaoAtivadePaciente(auxCpfPaciente);
									if(itativa!=null&&!it.getPaciente().getcpf().equals(auxCpfPaciente)) {
										System.out.println("\nPaciente já internado.\n");
										System.out.println("--------------------------------------------------------------");
										System.out.println("Código: "+itativa.getCodigo());
										System.out.println("Data de entrada: "+itativa.getDataEntrada());
										System.out.println("Hora de entrada: "+itativa.getHoraEntrada());
										System.out.println("Data de saída: "+itativa.getDataSaida());
										System.out.println("Hora de saída: "+itativa.getHoraSaida());
										System.out.println("Causas: "+itativa.getCausas());
										System.out.println("Status: "+itativa.getStatus());
										System.out.println("\nPaciente");
										System.out.println("\nNome: "+itativa.getPaciente().getnome());
										System.out.println("Cpf: "+itativa.getPaciente().getcpf());
										System.out.println("Endereco: "+itativa.getPaciente().getendereco());
										System.out.println("Telefone: "+itativa.getPaciente().gettelefone());
										System.out.println("Data de Nascimento: "+itativa.getPaciente().getdataNascimento());
										System.out.println("\nLeito");
										System.out.println("\nNumero: "+itativa.getLeito().getnumero());
										System.out.println("Tipo: "+itativa.getLeito().gettipo());
										System.out.println("Ocupado: "+itativa.getLeito().getocupado());
										equipamentos = itativa.getLeito().getequipamentos();
										for(i=0;i<equipamentos.size();i++) {
											if(i==0) {
												System.out.println("\nEquipamentos");
											}
											System.out.println("\nCódigo: "+equipamentos.get(i).getcodigo());
											System.out.println("Nome: "+equipamentos.get(i).getnome());
										}
									}
									else {
										System.out.println("Digite a nova causa da internação: ");
										it2.setCausas(ler.nextLine());
										System.out.println("Digite o novo leito da Internação: ");
										auxNumeroLeito = ler.nextInt();
										ler.nextLine();
										l = lDAO.buscarLeitopeloNumero(auxNumeroLeito);
										if(l==null) {
											System.out.println("\n--------------------------------------------------------------");
											System.out.println("Leito não encontrado");
										}else {
											ocupado = lDAO.verificarOcupacao(auxNumeroLeito);
											if(ocupado==true && it.getLeito().getnumero()!= auxNumeroLeito) {
												System.out.println("\n--------------------------------------------------------------");
												System.out.println("Leito Ocupado");
											}
											else {
												itDAO.AlterarInternacaoAtiva(it2, auxNumeroLeito, auxCpfPaciente, it.getCodigo());
												lDAO.desocuparLeito(it.getLeito().getnumero());
												lDAO.ocuparLeito(auxNumeroLeito);
												System.out.println("\n--------------------------------------------------------------");
												System.out.println("Operação realizada.");
							
											}
											
										}
								
								
									}
								
								}
							
							}
							else {
								ler.nextLine();
								System.out.println("\nCódigo de Internação já existe.");
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Código: "+it2.getCodigo());
								System.out.println("Data de entrada: "+it2.getDataEntrada());
								System.out.println("Hora de entrada: "+it2.getHoraEntrada());
								System.out.println("Data de saída: "+it2.getDataSaida());
								System.out.println("Hora de saída: "+it2.getHoraSaida());
								System.out.println("Causas: "+it2.getCausas());
								System.out.println("Status: "+it2.getStatus());
								System.out.println("\nPaciente");
								System.out.println("\nNome: "+it2.getPaciente().getnome());
								System.out.println("Cpf: "+it2.getPaciente().getcpf());
								System.out.println("Endereco: "+it2.getPaciente().getendereco());
								System.out.println("Telefone: "+it2.getPaciente().gettelefone());
								System.out.println("Data de Nascimento: "+it2.getPaciente().getdataNascimento());
								System.out.println("\nLeito");
								System.out.println("\nNumero: "+it2.getLeito().getnumero());
								System.out.println("Tipo: "+it2.getLeito().gettipo());
								System.out.println("Ocupado: "+it2.getLeito().getocupado());
								equipamentos = it2.getLeito().getequipamentos();
								for(i=0;i<equipamentos.size();i++) {
									if(i==0) {
										System.out.println("\nEquipamentos");
									}
									System.out.println("\nCódigo: "+equipamentos.get(i).getcodigo());
									System.out.println("Nome: "+equipamentos.get(i).getnome());
								}
							}
						}
					}
							
					else if(opTerciario==2) {
						System.out.println("\nDigite o código da internação que deseja alterar: ");
						auxCodigoInternacao = ler.nextInt();
						it = itDAO.buscarInternacaoPeloCodigo(auxCodigoInternacao);
						
						if(it==null||it.getStatus().equals("A")) {
							System.out.println("\nCódigo de Internação não encontrado para uma internação Inativa.");
						}
						else{
							ler.nextLine();
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Código: "+it.getCodigo());
							System.out.println("Data de entrada: "+it.getDataEntrada());
							System.out.println("Hora de entrada: "+it.getHoraEntrada());
							System.out.println("Data de saída: "+it.getDataSaida());
							System.out.println("Hora de saída: "+it.getHoraSaida());
							System.out.println("Causas: "+it.getCausas());
							System.out.println("Status: "+it.getStatus());
							System.out.println("\nPaciente");
							System.out.println("\nNome: "+it.getPaciente().getnome());
							System.out.println("Cpf: "+it.getPaciente().getcpf());
							System.out.println("Endereco: "+it.getPaciente().getendereco());
							System.out.println("Telefone: "+it.getPaciente().gettelefone());
							System.out.println("Data de Nascimento: "+it.getPaciente().getdataNascimento());
							System.out.println("\nLeito");
							System.out.println("\nNumero: "+it.getLeito().getnumero());
							System.out.println("Tipo: "+it.getLeito().gettipo());
							System.out.println("Ocupado: "+it.getLeito().getocupado());
							equipamentos = it.getLeito().getequipamentos();
							for(i=0;i<equipamentos.size();i++) {
								if(i==0) {
									System.out.println("\nEquipamentos");
								}
								System.out.println("\nCódigo: "+equipamentos.get(i).getcodigo());
								System.out.println("Nome: "+equipamentos.get(i).getnome());
							}
							System.out.println("--------------------------------------------------------------");
							it2 = new Internacao();
							System.out.println("\nDigite o nova código da internação: ");
							auxCodigoInternacao = ler.nextInt();
							it2 = itDAO.buscarInternacaoPeloCodigo(auxCodigoInternacao);
							if(it2==null||it.getCodigo()==auxCodigoInternacao) {
								it2 = new Internacao();
								it2.setCodigo(auxCodigoInternacao);
								ler.nextLine();
								System.out.println("Digite a data de entrada da internação: ");
								try {
									it2.setDataEntrada(new java.sql.Date(sdf.parse(ler.nextLine()).getTime()));
								
								} catch (Exception e) {
									System.out.println("\nData inválida.");
									break;
								}
								System.out.println("Digite a hora de entrada da Internação: ");
								try {
									it2.setHoraEntrada(new java.sql.Time(sdf2.parse(ler.nextLine()).getTime()));
								
								} catch (Exception e) {
									System.out.println("\nHora inválida.");
									break;
								}
								System.out.println("Digite a data de saída da internação: ");
								try {
									it.setDataSaida(new java.sql.Date(sdf.parse(ler.nextLine()).getTime()));
								
								} catch (Exception e) {
									System.out.println("\nData inválida.");
									break;
								}
								System.out.println("Digite a hora de saída da Internação: ");
								try {
									it.setHoraSaida(new java.sql.Time(sdf2.parse(ler.nextLine()).getTime()));
								
								} catch (Exception e) {
									System.out.println("\nHora inválida.");
									break;
								}
								System.out.println("Digite o Cpf do paciente: ");
								auxCpfPaciente = ler.nextLine();
								p = pDAO.buscarPacientepeloCpf(auxCpfPaciente);
								if(p==null) {
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Paciente não encontrado.");
								}
								else {
										System.out.println("Digite a causa da internação: ");
										it2.setCausas(ler.nextLine());
										System.out.println("Digite o Leito da Internação: ");
										auxNumeroLeito = ler.nextInt();
										ler.nextLine();
										l = lDAO.buscarLeitopeloNumero(auxNumeroLeito);
										if(l==null) {
											System.out.println("\n--------------------------------------------------------------");
											System.out.println("Leito não encontrado");
										}
										else {
											itDAO.AlterarInternacaoAtiva(it2, auxNumeroLeito, auxCpfPaciente, it.getCodigo());
											System.out.println("--------------------------------------------------------------");
											System.out.println("\nOperação realizada.");
										}
								
								}
							
							}
							else {
								ler.nextLine();
								System.out.println("\nCódigo de Internação já existe.\n");
								System.out.println("--------------------------------------------------------------");
								System.out.println("Código: "+it2.getCodigo());
								System.out.println("Data de entrada: "+it2.getDataEntrada());
								System.out.println("Hora de entrada: "+it2.getHoraEntrada());
								System.out.println("Data de saída: "+it2.getDataSaida());
								System.out.println("Hora de saída: "+it2.getHoraSaida());
								System.out.println("Causas: "+it2.getCausas());
								System.out.println("Status: "+it2.getStatus());
								System.out.println("\nPaciente");
								System.out.println("\nNome: "+it2.getPaciente().getnome());
								System.out.println("Cpf: "+it2.getPaciente().getcpf());
								System.out.println("Endereco: "+it2.getPaciente().getendereco());
								System.out.println("Telefone: "+it2.getPaciente().gettelefone());
								System.out.println("Data de Nascimento: "+it2.getPaciente().getdataNascimento());
								System.out.println("\nLeito");
								System.out.println("\nNumero: "+it2.getLeito().getnumero());
								System.out.println("Tipo: "+it2.getLeito().gettipo());
								System.out.println("Ocupado: "+it2.getLeito().getocupado());
								equipamentos = it2.getLeito().getequipamentos();
								for(i=0;i<equipamentos.size();i++) {
									if(i==0) {
										System.out.println("\nEquipamentos");
									}
									System.out.println("\nCódigo: "+equipamentos.get(i).getcodigo());
									System.out.println("Nome: "+equipamentos.get(i).getnome());
								}
							}
						}
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 5:System.out.println("\nRelatório de Internações");
					System.out.println("--------------------------------------------------------------");
					System.out.println("1 - Relatório de Internações ativas");
					System.out.println("2 - Relatório de Internações inativas");
					System.out.println("3 - Relatório geral");
					System.out.println("4 - Voltar ao menu principal");
					System.out.println("--------------------------------------------------------------");
					opTerciario = ler.nextInt();
					ler.nextLine();
					switch(opTerciario){
						case 1:internacoes = itDAO.relatoriodeInternacoesAtivas();
						if(internacoes.size()==0) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Não há internações cadastradas.");
						}
						else {
							System.out.println("\nRelatório de Internações\n");
							for(i=0;i<internacoes.size();i++) {
								System.out.println("--------------------------------------------------------------");
								System.out.println("Código: "+internacoes.get(i).getCodigo());
								System.out.println("Data de entrada: "+internacoes.get(i).getDataEntrada());
								System.out.println("Hora de entrada: "+internacoes.get(i).getHoraEntrada());
								System.out.println("Data de saída: "+internacoes.get(i).getDataSaida());
								System.out.println("Hora de saída: "+internacoes.get(i).getHoraSaida());
								System.out.println("Causas: "+internacoes.get(i).getCausas());
								System.out.println("Status: "+internacoes.get(i).getStatus());
								System.out.println("\nPaciente");
								System.out.println("\nNome: "+internacoes.get(i).getPaciente().getnome());
								System.out.println("Cpf: "+internacoes.get(i).getPaciente().getcpf());
								System.out.println("Endereco: "+internacoes.get(i).getPaciente().getendereco());
								System.out.println("Telefone: "+internacoes.get(i).getPaciente().gettelefone());
								System.out.println("Data de Nascimento: "+internacoes.get(i).getPaciente().getdataNascimento());
								System.out.println("\nLeito");
								System.out.println("\nNumero: "+internacoes.get(i).getLeito().getnumero());
								System.out.println("Tipo: "+internacoes.get(i).getLeito().gettipo());
								System.out.println("Ocupado: "+internacoes.get(i).getLeito().getocupado());
								equipamentos = internacoes.get(i).getLeito().getequipamentos();
								for(i2=0;i2<equipamentos.size();i2++) {
									if(i2==0) {
										System.out.println("\nEquipamentos");
									}
									System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
									System.out.println("Nome: "+equipamentos.get(i2).getnome());
								}
							}
						}
						
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nPressione enter para continuar");
						ler.nextLine();
						break;
						case 2:internacoes = itDAO.relatoriodeInternacoesInativas();
						if(internacoes.size()==0) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Não há internações cadastradas.");
						}
						else {
							System.out.println("\nRelatório de Internações\n");
							for(i=0;i<internacoes.size();i++) {
								System.out.println("--------------------------------------------------------------");
								System.out.println("Código: "+internacoes.get(i).getCodigo());
								System.out.println("Data de entrada: "+internacoes.get(i).getDataEntrada());
								System.out.println("Hora de entrada: "+internacoes.get(i).getHoraEntrada());
								System.out.println("Data de saída: "+internacoes.get(i).getDataSaida());
								System.out.println("Hora de saída: "+internacoes.get(i).getHoraSaida());
								System.out.println("Causas: "+internacoes.get(i).getCausas());
								System.out.println("Status: "+internacoes.get(i).getStatus());
								System.out.println("\nPaciente");
								System.out.println("\nNome: "+internacoes.get(i).getPaciente().getnome());
								System.out.println("Cpf: "+internacoes.get(i).getPaciente().getcpf());
								System.out.println("Endereco: "+internacoes.get(i).getPaciente().getendereco());
								System.out.println("Telefone: "+internacoes.get(i).getPaciente().gettelefone());
								System.out.println("Data de Nascimento: "+internacoes.get(i).getPaciente().getdataNascimento());
								System.out.println("\nLeito");
								System.out.println("\nNumero: "+internacoes.get(i).getLeito().getnumero());
								System.out.println("Tipo: "+internacoes.get(i).getLeito().gettipo());
								System.out.println("Ocupado: "+internacoes.get(i).getLeito().getocupado());
								equipamentos = internacoes.get(i).getLeito().getequipamentos();
								for(i2=0;i2<equipamentos.size();i2++) {
									if(i2==0) {
										System.out.println("\nEquipamentos");
									}
									System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
									System.out.println("Nome: "+equipamentos.get(i2).getnome());
								}
							}
						}
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nPressione enter para continuar");
						ler.nextLine();
						break;
						case 3:
						internacoes = itDAO.relatoriodeInternacoesGeral();
						if(internacoes.size()==0) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Não há internações cadastradas.");
						}
						else {
							System.out.println("\nRelatório de Internações\n");
						for(i=0;i<internacoes.size();i++) {
							System.out.println("--------------------------------------------------------------");
							System.out.println("Código: "+internacoes.get(i).getCodigo());
							System.out.println("Data de entrada: "+internacoes.get(i).getDataEntrada());
							System.out.println("Hora de entrada: "+internacoes.get(i).getHoraEntrada());
							System.out.println("Data de saída: "+internacoes.get(i).getDataSaida());
							System.out.println("Hora de saída: "+internacoes.get(i).getHoraSaida());
							System.out.println("Causas: "+internacoes.get(i).getCausas());
							System.out.println("Status: "+internacoes.get(i).getStatus());
							System.out.println("\nPaciente");
							System.out.println("\nNome: "+internacoes.get(i).getPaciente().getnome());
							System.out.println("Cpf: "+internacoes.get(i).getPaciente().getcpf());
							System.out.println("Endereco: "+internacoes.get(i).getPaciente().getendereco());
							System.out.println("Telefone: "+internacoes.get(i).getPaciente().gettelefone());
							System.out.println("Data de Nascimento: "+internacoes.get(i).getPaciente().getdataNascimento());
							System.out.println("\nLeito");
							System.out.println("\nNumero: "+internacoes.get(i).getLeito().getnumero());
							System.out.println("Tipo: "+internacoes.get(i).getLeito().gettipo());
							System.out.println("Ocupado: "+internacoes.get(i).getLeito().getocupado());
							equipamentos = internacoes.get(i).getLeito().getequipamentos();
							for(i2=0;i2<equipamentos.size();i2++) {
								if(i2==0) {
									System.out.println("\nEquipamentos");
								}
								System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
								System.out.println("Nome: "+equipamentos.get(i2).getnome());
							}
						}
						}
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nPressione enter para continuar");
						ler.nextLine();
						break;
						case 4:
						break;
					}
				break;
				}
				break;
				case 2:System.out.println("\nLeitos");
				System.out.println("--------------------------------------------------------------");
				System.out.println("1 - Buscar Leito");
				System.out.println("2 - Criar Leito");
				System.out.println("3 - Excluir Leito");
				System.out.println("4 - Alterar Leito");
				System.out.println("5 - Relatorio de Leitos");
				System.out.println("6 - Voltar ao Menu Principal");
				System.out.println("--------------------------------------------------------------");
				opSecundario = ler.nextInt();
				switch(opSecundario){
					case 1: System.out.println("\nBuscar Leitos");
					System.out.println("--------------------------------------------------------------");
					System.out.println("1 - Buscar Leitos Disponiveis");
					System.out.println("2 - Buscar Leitos Ocupados");
					System.out.println("3 - Buscar Leito por Equipamento");
					System.out.println("4 - Buscar um leito");
					System.out.println("5 - Voltar ao Menu Principal");
					System.out.println("--------------------------------------------------------------");
					opTerciario = ler.nextInt();
					switch(opTerciario) {
						case 1:ler.nextLine();
						leitos = lDAO.buscarLeitosDisponiveis();
						if(leitos.size()==0) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Não há Leitos disponíveis.");
						}
						else {
							System.out.println("\nLeitos disponíveis\n");
						}
						for(i=0;i<leitos.size();i++) {
							System.out.println("--------------------------------------------------------------");
							System.out.println("Número: "+leitos.get(i).getnumero());
							System.out.println("Tipo: "+leitos.get(i).gettipo());
							System.out.println("Ocupado: "+leitos.get(i).getocupado());
							equipamentos = leitos.get(i).getequipamentos();
							for(i2=0;i2<equipamentos.size();i2++) {
								if(i2==0) {
									System.out.println("\nEquipamentos");	
								}
								System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
								System.out.println("Nome: "+equipamentos.get(i2).getnome());
							}
							
						}
						break;
						
						case 2:ler.nextLine();
						leitos= lDAO.buscarLeitosOcupados();
						if(leitos.size()==0) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Não há Leitos ocupados.");
						}
						else {
							System.out.println("\nLeitos Ocupados\n");
						}
						for(i=0;i<leitos.size();i++) {
							System.out.println("--------------------------------------------------------------");
							System.out.println("Número: "+leitos.get(i).getnumero());
							System.out.println("Tipo: "+leitos.get(i).gettipo());
							System.out.println("Ocupado: "+leitos.get(i).getocupado());
							equipamentos = leitos.get(i).getequipamentos();
							for(i2=0;i2<equipamentos.size();i2++) {
								if(i2==0) {
									System.out.println("\nEquipamentos");	
								}
								System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
								System.out.println("Nome: "+equipamentos.get(i2).getnome());
							}
								
						}
						break;
						case 3:
							ler.nextLine();
							System.out.println("\nEscreva o código do equipamento: ");
							auxCodigoEquipamento = ler.nextInt();
							ler.nextLine();
							eq = eqDAO.buscarEquipamentopeloCodigo(auxCodigoEquipamento);
							if(eq==null) {
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Equipamento não encontrado.");
							}
							else {
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Código: "+eq.getcodigo());
								System.out.println("Nome: "+eq.getnome());
								System.out.println("--------------------------------------------------------------");
								leitos = elDAO.buscarLeitoscomesseEquipamento(auxCodigoEquipamento);
									if(leitos.size()==0) {
										System.out.println("\n--------------------------------------------------------------");
										System.out.println("Não há leitos associados a esse equipamento.");
									}
									else {
										System.out.println("\nLeitos Encontrados\n");
									}
									for(i=0;i<leitos.size();i++) {
										System.out.println("--------------------------------------------------------------");
										System.out.println("Número: "+leitos.get(i).getnumero());
										System.out.println("Tipo: "+leitos.get(i).gettipo());
										System.out.println("Ocupado: "+leitos.get(i).getocupado());
										equipamentos = leitos.get(i).getequipamentos();
										for(i2=0;i2<equipamentos.size();i2++) {
											if(i2==0) {
												System.out.println("\nEquipamentos");	
											}
											System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
											System.out.println("Nome: "+equipamentos.get(i2).getnome());
										}
									}
							}
						break;
						case 4:
							System.out.println("\nDigite o número do leito: ");
							auxNumeroLeito = ler.nextInt();
							ler.nextLine();
							l=lDAO.buscarLeitopeloNumero(auxNumeroLeito);
							if(l==null) {
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Leito não encontrado.");
							}
							else {
								System.out.println("\nLeito encontrado.\n");
								System.out.println("--------------------------------------------------------------");
								System.out.println("Número: "+l.getnumero());
								System.out.println("Tipo: "+l.gettipo());
								System.out.println("Ocupado: "+l.getocupado());
								equipamentos = l.getequipamentos();
								for(i2=0;i2<equipamentos.size();i2++) {
									if(i2==0) {
										System.out.println("\nEquipamentos");	
									}
									System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
									System.out.println("Nome: "+equipamentos.get(i2).getnome());
								}
							}
						break;
						case 5:
						break;
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 2: 
					System.out.println("\nDigite o número do novo leito: ");
					auxNumeroLeito = ler.nextInt();
					l = lDAO.buscarLeitopeloNumero(auxNumeroLeito);
					if(l==null) {
						l = new Leito();
						l.setnumero(auxNumeroLeito);
						ler.nextLine();
						System.out.println("Digite o tipo de leito(Apartamento ou Enfermaria):");
						l.settipo(ler.nextLine());
						lDAO.criarLeito(l);
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Operação realizada.");
					}
					else {
						ler.nextLine();
						System.out.println("\nLeito já cadastrado.\n");
						System.out.println("--------------------------------------------------------------");
						System.out.println("Número: "+l.getnumero());
						System.out.println("Tipo: "+l.gettipo());
						System.out.println("Ocupado: "+l.getocupado());
						equipamentos = l.getequipamentos();
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 3:
					System.out.println("\nDigite o número do leito: ");
					auxNumeroLeito = ler.nextInt();
					l =lDAO.buscarLeitopeloNumero(auxNumeroLeito);
					if(l==null) {
						ler.nextLine();
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Leito não encontrado.");
					}
					else {
						it = itDAO.buscarInternacaoAtivaPeloLeito(l.getnumero());
						if(it==null) {
							internacoes = itDAO.buscarInternacaoPeloLeito(l.getnumero());
							if(internacoes.size()>0&&l.equipamentoSize()>0) {
								System.out.println("\nExclusão não autorizada. Há equipamentos e internações inativas associados ao leito.\n");					
								System.out.println("\nDeseja continuar exclusão?(1 -sim, 0 - nao)");
								int excluir = ler.nextInt();
								ler.nextLine();
								if(excluir==1) {
									itDAO.removerInternacoesDeLeitoemCascata(l.getnumero());
									elDAO.removerEquipamentosdeLeitoemCascata(l.getnumero());
									lDAO.removerLeito(l.getnumero());
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Operação realizada.");
								}
								else {
									break;
								}
							}
							else if(internacoes.size()>0&&l.equipamentoSize()==0) {
								System.out.println("\nExclusão não autorizada. Há internações inativas associados ao leito.\n");					
								System.out.println("\nDeseja continuar exclusão?(1 -sim, 0 - nao)");
								int excluir = ler.nextInt();
								ler.nextLine();
								if(excluir==1) {
									itDAO.removerInternacoesDeLeitoemCascata(l.getnumero());
									lDAO.removerLeito(l.getnumero());
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Operação realizada.");
								}
								else {
									break;
								}
							}	
							else if(internacoes.size()==0&&l.equipamentoSize()>0) {
								System.out.println("\nExclusão não autorizada. Há equipamentos associados ao leito.\n");					
								System.out.println("\nDeseja continuar exclusão?(1 -sim, 0 - nao)");
								int excluir = ler.nextInt();
								ler.nextLine();
								if(excluir==1) {
									elDAO.removerEquipamentosdeLeitoemCascata(auxNumeroLeito);
									lDAO.removerLeito(auxNumeroLeito);
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Operação realizada.");
								}
								else {
									break;
								}
							}
							else {
								System.out.println("\nDeseja continuar exclusão?(1 -sim, 0 - nao)");
								int excluir = ler.nextInt();
								ler.nextLine();
								if(excluir==1) {
									lDAO.removerLeito(auxNumeroLeito);
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Operação realizada.");
								}
								else {
									break;
								}
								
							}
							
						}
						else {
							ler.nextLine();
							System.out.println("\nExclusão não autorizada. Há uma Internação Ativa nesse Leito\n");
							System.out.println("--------------------------------------------------------------");
							System.out.println("Código: "+it.getCodigo());
							System.out.println("Data de entrada: "+it.getDataEntrada());
							System.out.println("Hora de entrada: "+it.getHoraEntrada());
							System.out.println("Data de saída: "+it.getDataSaida());
							System.out.println("Hora de saída: "+it.getHoraSaida());
							System.out.println("Causas: "+it.getCausas());
							System.out.println("Status: "+it.getStatus());
							System.out.println("\nPaciente");
							System.out.println("Nome: "+it.getPaciente().getnome());
							System.out.println("Cpf: "+it.getPaciente().getcpf());
							System.out.println("Endereco: "+it.getPaciente().getendereco());
							System.out.println("Telefone: "+it.getPaciente().gettelefone());
							System.out.println("Data de Nascimento: "+it.getPaciente().getdataNascimento());
							System.out.println("\nLeito");
							System.out.println("\nNumero: "+it.getLeito().getnumero());
							System.out.println("Tipo: "+it.getLeito().gettipo());
							System.out.println("Ocupado: "+it.getLeito().getocupado());
							equipamentos = it.getLeito().getequipamentos();
							for(i=0;i<equipamentos.size();i++) {
								if(i==0) {
									System.out.println("\nEquipamentos");
								}
								System.out.println("\nCódigo: "+equipamentos.get(i).getcodigo());
								System.out.println("Nome: "+equipamentos.get(i).getnome());
							}
						}
						
						
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 4: 
					System.out.println("\nDigite o número do leito que deseja alterar: ");
					auxNumeroLeito = ler.nextInt();
					l = lDAO.buscarLeitopeloNumero(auxNumeroLeito);
					if(l == null) {
						ler.nextLine();
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Leito não encontrado.");
					}
					else {
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Número: "+l.getnumero());
						System.out.println("Tipo: "+l.gettipo());
						System.out.println("Ocupado: "+l.getocupado());
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nDigite o novo número do leito: ");
						auxNumeroLeito = ler.nextInt();
						l2 = lDAO.buscarLeitopeloNumero(auxNumeroLeito);
						if(l2==null||l.getnumero()==auxNumeroLeito) {
							l2 = new Leito ();
							l2.setnumero(auxNumeroLeito);
							ler.nextLine();
							System.out.println("Digite o novo tipo de leito(Apartamento ou Enfermaria):");
							l2.settipo(ler.nextLine());
							lDAO.alterarLeito(l2,l.getnumero());
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Operação realizada.");
						}
						else {
							ler.nextLine();
							System.out.println("\nLeito já cadastrado.\n");
							System.out.println("--------------------------------------------------------------");
							System.out.println("Número: "+l.getnumero());
							System.out.println("Tipo: "+l.gettipo());
							System.out.println("Ocupado: "+l.getocupado());
						}
					
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 5: ler.nextLine();
					leitos = lDAO.relatoriodeLeitos();
					if(leitos.size()==0) {
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Não há leitos cadastrado.");
					}
					else {
						System.out.println("\nRelatório de leitos\n");
					}
					for(i=0;i<leitos.size();i++) {
						System.out.println("--------------------------------------------------------------");
						System.out.println("Número: "+leitos.get(i).getnumero());
						System.out.println("Tipo: "+leitos.get(i).gettipo());
						System.out.println("Ocupado: "+leitos.get(i).getocupado());
						equipamentos = leitos.get(i).getequipamentos();
						for(i2=0;i2<equipamentos.size();i2++) {
							if(i2==0) {
								System.out.println("\nEquipamentos");	
							}
							System.out.println("\nCódigo: "+equipamentos.get(i2).getcodigo());
							System.out.println("Nome: "+equipamentos.get(i2).getnome());
						}	
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 6:
					break;
				}
				break;
				case 3:System.out.println("\nEquipamentos");
				System.out.println("--------------------------------------------------------------");
				System.out.println("1 - Buscar Equipamento");
				System.out.println("2 - Criar Equipamento");
				System.out.println("3 - Excluir Equipamento");
				System.out.println("4 - Alterar Equipamento");
				System.out.println("5 - Adicionar Equipamento ao Leito");
				System.out.println("6 - Remover Equipamento do Leito");
				System.out.println("7 - Relatorio de Equipamentos");
				System.out.println("8 - Voltar ao Menu Principal");
				System.out.println("--------------------------------------------------------------");
				opSecundario = ler.nextInt();
				switch(opSecundario) {
					case 1:
					System.out.println("\nBuscar Equipamento");
					System.out.println("--------------------------------------------------------------");
					System.out.println("1 - Buscar pelo Nome ");
					System.out.println("2 - Buscar pelo Código");
					System.out.println("3 - Voltar ao Menu Principal");
					System.out.println("--------------------------------------------------------------");
					opTerciario = ler.nextInt();
					if(opTerciario==1) {
						ler.nextLine();
						System.out.println("\nDigite o nome do equipamento: ");
						auxNomeEquipamento = ler.nextLine().toUpperCase();
						equipamentos = eqDAO.buscarEquipamentopeloNome(auxNomeEquipamento);
						if(equipamentos.size()>0) {
							System.out.println("\nEquipamento(s) encontrado(s).");
							for(i=0; i<equipamentos.size(); i++) {
								System.out.println("\n--------------------------------------------------------------");	
								System.out.println("Código: "+equipamentos.get(i).getcodigo());
								System.out.println("Nome: "+equipamentos.get(i).getnome());
							}
						}
						else {
							System.out.println("\n--------------------------------------------------------------");	
							System.out.println("Equipamento não encontrado.");
						}
					}
					else if(opTerciario == 2) {
						System.out.println("\nDigite o código do equipamento: ");
						auxCodigoEquipamento = ler.nextInt();
						ler.nextLine();
						eq = eqDAO.buscarEquipamentopeloCodigo(auxCodigoEquipamento);
						if(eq == null) {
							System.out.println("\n--------------------------------------------------------------");	
							System.out.println("Equipamento não encontrado.");
						}
						else {
							System.out.println("\nEquipamento encontrado.");
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Código: "+eq.getcodigo());
							System.out.println("Nome: "+eq.getnome());
						}
					}
					else {
						break;
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 2:
					System.out.println("\nDigite o código do equipamento: ");
					auxCodigoEquipamento = ler.nextInt();
					ler.nextLine();
					eq = eqDAO.buscarEquipamentopeloCodigo(auxCodigoEquipamento);
					if(eq ==null) {
						eq = new Equipamento();
						eq.setcodigo(auxCodigoEquipamento);
						System.out.println("\nDigite nome do equipamento: ");
						eq.setnome(ler.nextLine());
						eqDAO.CriarEquipamento(eq);
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Operação realizada.");
					}
					else {
						System.out.println("\nEquipamento já cadastrado.");
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Código: "+eq.getcodigo());
						System.out.println("Nome: "+eq.getnome());
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 3:
						ler.nextLine();
						System.out.println("\nEscreva o código do equipamento: ");
						auxCodigoEquipamento = ler.nextInt();
						eq = eqDAO.buscarEquipamentopeloCodigo(auxCodigoEquipamento);
						if(eq==null) {
							ler.nextLine();
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Equipamento não encontrado.");
						}
						else {
							leitos = elDAO.buscarLeitoscomesseEquipamento(auxCodigoEquipamento);
							if(leitos.size()>0) {
								System.out.println("\nExclusao não autorizada. Há leitos associados a esse equipamento.\n");
								System.out.println("\nDeseja continuar a exclusão?(1 -sim, 0 - nao)");
								int excluir = ler.nextInt();
								ler.nextLine();
								if(excluir==1) {
									elDAO.removerEquipamentoemCascata(auxCodigoEquipamento);
									eqDAO.removerEquipamento(auxCodigoEquipamento);
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Operação realizada.");
								}
								else {
									break;
								}
							}
							else {
								System.out.println("\nDeseja continuar a exclusão?(1 -sim, 0 - nao)");
								int excluir = ler.nextInt();
								ler.nextLine();
								if(excluir==1) {
									eqDAO.removerEquipamento(auxCodigoEquipamento);
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Operação realizada.");
								}
								else {
									break;
								}
							}
						}
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nPressione enter para continuar");
						ler.nextLine();
					break;
					case 4: 
					System.out.println("\nDigite o código do equipamento que deseja alterar: ");
					auxCodigoEquipamento = ler.nextInt();
					eq = eqDAO.buscarEquipamentopeloCodigo(auxCodigoEquipamento );
					if(eq == null) {
						ler.nextLine();
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Equipamento não encontrado.");
					}
					else {
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Código: "+eq.getcodigo());
						System.out.println("Nome: "+eq.getnome());
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nDigite o novo código do equipamento: ");
						auxCodigoEquipamento = ler.nextInt();
						eq2 = eqDAO.buscarEquipamentopeloCodigo(auxCodigoEquipamento );
						if(eq2==null||eq.getcodigo()== auxCodigoEquipamento) {
							eq2= new Equipamento();
							eq2.setcodigo(auxCodigoEquipamento);
							ler.nextLine();
							System.out.println("Digite o novo nome do equipamento:");
							eq2.setnome(ler.nextLine());
							eqDAO.alterarEquipamento(eq2,eq.getcodigo());
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Operação realizada.");
						}
						else {
							ler.nextLine();
							System.out.println("\nEquipamento já cadastrado.");
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Código: "+eq2.getcodigo());
							System.out.println("Nome: "+eq2.getnome());
						}
						
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 5:
					System.out.println("\nDigite o código do equipamento: ");
					auxCodigoEquipamento = ler.nextInt();
					eq = eqDAO.buscarEquipamentopeloCodigo(auxCodigoEquipamento);
					if(eq==null) {
						ler.nextLine();
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Equipamento não encontrado.");
					}
					else {
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Código: "+eq.getcodigo());
						System.out.println("Nome: "+eq.getnome());
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nDigite o número do leito: ");
						auxNumeroLeito = ler.nextInt();
						ler.nextLine();
						l = lDAO.buscarLeitopeloNumero(auxNumeroLeito);
						if(l ==null) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Leito não encontrado.");
						}
						else {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Número: "+l.getnumero());
							System.out.println("Tipo: "+l.gettipo());
							System.out.println("Ocupado: "+l.getocupado());
							System.out.println("--------------------------------------------------------------");
							if(elDAO.verificarEquipamentoLeito(auxCodigoEquipamento, auxNumeroLeito)){
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Equipamento já adicionado ao leito.");
								
							}
							else {
								elDAO.adicionarEquipamentoaoLeito(auxCodigoEquipamento, auxNumeroLeito);
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Operação realizada.");
							}
						}
						
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 6:
						System.out.println("\nDigite o código do equipamento: ");
						auxCodigoEquipamento = ler.nextInt();
						eq = eqDAO.buscarEquipamentopeloCodigo(auxCodigoEquipamento);
						if(eq==null) {
							ler.nextLine();
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Equipamento não encontrado.");
						}
						else {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Código: "+eq.getcodigo());
							System.out.println("Nome: "+eq.getnome());
							System.out.println("--------------------------------------------------------------");
							System.out.println("\nDigite o número do leito: ");
							auxNumeroLeito = ler.nextInt();
							ler.nextLine();
							l = lDAO.buscarLeitopeloNumero(auxNumeroLeito);
							if(l ==null) {
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Leito não encontrado.");
							}
							else {
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Número: "+l.getnumero());
								System.out.println("Tipo: "+l.gettipo());
								System.out.println("Ocupado: "+l.getocupado());
								System.out.println("--------------------------------------------------------------");
								if(elDAO.verificarEquipamentoLeito(auxCodigoEquipamento, auxNumeroLeito)){
									elDAO.removerEquipamentodoLeito(auxCodigoEquipamento, auxNumeroLeito);
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Operação realizada.");
								}
								else {
									ler.nextLine();
									System.out.println("\n--------------------------------------------------------------");
									System.out.println("Equipamento não pertence ao leito.");
									
								}
							}
						}
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nPressione enter para continuar");
						ler.nextLine();
					break;
					case 7:ler.nextLine();
					equipamentos =  eqDAO.emitirRelatorioEquipamentos();
					
					if(equipamentos.size()==0) {
						System.out.println("\n--------------------------------------------------------------");	
						System.out.println("Não há equipamentos cadastrados.");
					}
					else {
						System.out.println("\nRelatório de equipamentos\n");
					}
					
					for(i=0; i<equipamentos.size(); i++) {
						System.out.println("--------------------------------------------------------------");
						System.out.println("Código: "+equipamentos.get(i).getcodigo());
						System.out.println("Nome: "+equipamentos.get(i).getnome());
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
					break;
					case 8:
					break;
				}
				break;
				case 4:System.out.println("\nPacientes");
				System.out.println("--------------------------------------------------------------");
				System.out.println("1 - Buscar Paciente");
				System.out.println("2 - Criar Paciente");
				System.out.println("3 - Excluir Paciente");
				System.out.println("4 - Alterar Paciente");
				System.out.println("5 - Relatório de Pacientes");
				System.out.println("6 - Voltar ao Menu Principal");
				System.out.println("--------------------------------------------------------------");
				opSecundario = ler.nextInt();
				switch(opSecundario) {
				case 1:
					System.out.println("\nBuscar Paciente");
					System.out.println("--------------------------------------------------------------");
					System.out.println("1 - Buscar Paciente pelo Nome");
					System.out.println("2 - Buscar Paciente pelo Cpf");
					System.out.println("3 - Voltar ao menu Principal");
					System.out.println("--------------------------------------------------------------");
					opTerciario = ler.nextInt();
					ler.nextLine();
					if(opTerciario==1) {
						System.out.println("\nDigite o nome do Paciente: ");
						auxNomePaciente = ler.nextLine().toUpperCase();
						pacientes = pDAO.buscarPacientepeloNome(auxNomePaciente);
						if(pacientes.size()>0) {
							System.out.println("\nPaciente(s) encontrado(s).");
							for(i=0;i<pacientes.size();i++) {
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Nome: "+pacientes.get(i).getcpf());
								System.out.println("Cpf: "+pacientes.get(i).getnome());
								System.out.println("Telefone: "+pacientes.get(i).gettelefone());
								System.out.println("Endereco: "+pacientes.get(i).getendereco());
								System.out.println("Data de Nascimento: "+pacientes.get(i).getdataNascimento());
								
							}
						}
						else {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Paciente não encontrado.");
						}
					}
					else if(opTerciario==2) {
						System.out.println("\nDigite o cpf do Paciente: ");
						auxCpfPaciente = ler.nextLine();
						p = pDAO.buscarPacientepeloCpf(auxCpfPaciente);
						if (p==null) {
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Paciente não encontrado.");
						}
						else {
							System.out.println("\nPaciente encontrado.");
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Nome: "+p.getcpf());
							System.out.println("Cpf: "+p.getnome());
							System.out.println("Telefone: "+p.gettelefone());
							System.out.println("Endereco: "+p.getendereco());
							System.out.println("Data de Nascimento: "+p.getdataNascimento());
						}
					}
					else {
						break;
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
				break;
				case 2:
				ler.nextLine();
				System.out.println("Digite o cpf: ");
				auxCpfPaciente = ler.nextLine();
				p = pDAO.buscarPacientepeloCpf(auxCpfPaciente);
				if(p==null) {
					p = new Paciente();
					p.setcpf(auxCpfPaciente);
					System.out.println("Digite o nome: ");
					p.setnome(ler.nextLine());
					System.out.println("Digite o endereço: ");
					p.setendereco(ler.nextLine());
					System.out.println("Digite o telefone: ");
					p.settelefone(ler.nextLine());
					System.out.println("Digite data de nascimento: ");
					try {
						p.setdataNascimento(new java.sql.Date(sdf.parse(ler.nextLine()).getTime()));
					} catch (ParseException e) {
						System.out.println("\nData inválida.");
						break;
					}
					pDAO.criarPaciente(p);
					System.out.println("\n--------------------------------------------------------------");
					System.out.println("Operação realizada.");
					
				}
				else {
					System.out.println("\nPaciente já cadastrado.");
					System.out.println("\n--------------------------------------------------------------");
					System.out.println("Nome: "+p.getcpf());
					System.out.println("Cpf: "+p.getnome());
					System.out.println("Telefone: "+p.gettelefone());
					System.out.println("Endereco: "+p.getendereco());
					System.out.println("Data de Nascimento: "+p.getdataNascimento());
				}
				System.out.println("--------------------------------------------------------------");
				System.out.println("\nPressione enter para continuar");
				ler.nextLine();
				break;
				case 3:
				ler.nextLine();
				System.out.println("\nDigite o cpf do paciente: ");
				auxCpfPaciente = ler.nextLine();
				p = pDAO.buscarPacientepeloCpf(auxCpfPaciente);
				if(p==null) {
					System.out.println("\n--------------------------------------------------------------");
					System.out.println("Paciente não encontrado.");
				}
				else {
					itativa = itDAO.BuscarInternacaoAtivadePaciente(p.getcpf());
					if(itativa==null) {
						internacoes = itDAO.buscarInternacaoPeloPaciente(p.getcpf());
						if(internacoes.size()>0) {
							System.out.println("\nExclusão não autorizada. Há internações inativas associadas associadas ao paciente.");
							System.out.println("\nDeseja continuar exclusão?(1 -sim, 0 - nao)");
							int excluir = ler.nextInt();
							if(excluir==1) {
								itDAO.removerInternacoesDePacienteemCascata(p.getcpf());
								pDAO.removerPaciente(p.getcpf());
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Operação realizada.");
							}
							else {
								break;
							}
						}
						else {
							System.out.println("\nDeseja continuar exclusão?(1 -sim, 0 - nao)");
							int excluir = ler.nextInt();
							if(excluir==1) {
								pDAO.removerPaciente(p.getcpf());
								System.out.println("\n--------------------------------------------------------------");
								System.out.println("Operação realizada.");
							}
							else {
								break;
							}
							
						}
						
					}
					else {
						System.out.println("\nExclusão não autorizada. O paciente possui uma internação ativa.");
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Código: "+itativa.getCodigo());
						System.out.println("Data de entrada: "+itativa.getDataEntrada());
						System.out.println("Hora de entrada: "+itativa.getHoraEntrada());
						System.out.println("Data de saída: "+itativa.getDataSaida());
						System.out.println("Hora de saída: "+itativa.getHoraSaida());
						System.out.println("Causas: "+itativa.getCausas());
						System.out.println("Status: "+itativa.getStatus());
						System.out.println("\nPaciente");
						System.out.println("\nNome: "+itativa.getPaciente().getnome());
						System.out.println("Cpf: "+itativa.getPaciente().getcpf());
						System.out.println("Endereco: "+itativa.getPaciente().getendereco());
						System.out.println("Telefone: "+itativa.getPaciente().gettelefone());
						System.out.println("Data de Nascimento: "+itativa.getPaciente().getdataNascimento());
						System.out.println("\nLeito");
						System.out.println("\nNumero: "+itativa.getLeito().getnumero());
						System.out.println("Tipo: "+itativa.getLeito().gettipo());
					}
				}
				System.out.println("--------------------------------------------------------------");
				System.out.println("\nPressione enter para continuar");
				ler.nextLine();
				break;
				case 4:
					ler.nextLine();
					System.out.println("\nDigite o cpf do paciente que deseja alterar: ");
					auxCpfPaciente = ler.nextLine();
					p = pDAO.buscarPacientepeloCpf(auxCpfPaciente);
					if(p==null) {
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Paciente não encontrado.");
					}	
					else {
						System.out.println("\n--------------------------------------------------------------");
						System.out.println("Nome: "+p.getcpf());
						System.out.println("Cpf: "+p.getnome());
						System.out.println("Telefone: "+p.gettelefone());
						System.out.println("Endereco: "+p.getendereco());
						System.out.println("Data de Nascimento: "+p.getdataNascimento());
						System.out.println("--------------------------------------------------------------");
						System.out.println("\nDigite o novo Cpf: ");
						auxCpfPaciente = ler.nextLine();
						Paciente p2 = pDAO.buscarPacientepeloCpf(auxCpfPaciente);
						if(p2==null||p.getcpf().equals(auxCpfPaciente)) {
							p2 = new Paciente();
							p2.setcpf(auxCpfPaciente);
							System.out.println("Digite o novo nome: ");
							p2.setnome(ler.nextLine());
							System.out.println("Digite o novo telefone: ");
							p2.settelefone(ler.nextLine());
							System.out.println("Digite o novo endereço: ");
							p2.setendereco(ler.nextLine());
							System.out.println("Digite a nova data de nascimento: ");
							try {
								p2.setdataNascimento(new java.sql.Date(sdf.parse(ler.nextLine()).getTime()));
				
							} catch (ParseException e) {
								System.out.println("\nData inválida.");
								break;
							}
							pDAO.alterarPaciente(p2,p.getcpf());
							System.out.println("\n--------------------------------------------------------------");
							System.out.println("Operação realizada.");
						}
						else {
							System.out.println("\nCpf já cadastrado.\n");
							System.out.println("--------------------------------------------------------------");
							System.out.println("Nome: "+p2.getcpf());
							System.out.println("Cpf: "+p2.getnome());
							System.out.println("Telefone: "+p2.gettelefone());
							System.out.println("Endereco: "+p2.getendereco());
							System.out.println("Data de Nascimento: "+p2.getdataNascimento());
							
							
						}
						
					}
					System.out.println("--------------------------------------------------------------");
					System.out.println("\nPressione enter para continuar");
					ler.nextLine();
				break;
				case 5:ler.nextLine();
				pacientes = pDAO.relatorioPacientes();
				if(pacientes.size()>0) {
					System.out.println("\nRelatório de pacientes\n");
					for(i=0;i<pacientes.size();i++) {
						System.out.println("--------------------------------------------------------------");
						System.out.println("Nome: "+pacientes.get(i).getcpf());
						System.out.println("Cpf: "+pacientes.get(i).getnome());
						System.out.println("Telefone: "+pacientes.get(i).gettelefone());
						System.out.println("Endereco: "+pacientes.get(i).getendereco());
						System.out.println("Data de Nascimento: "+pacientes.get(i).getdataNascimento());
						
					}
				}
				else {
					System.out.println("\n--------------------------------------------------------------");
					System.out.println("Não há pacientes cadastrados.");
				}
				System.out.println("--------------------------------------------------------------");
				System.out.println("\nPressione enter para continuar");
				ler.nextLine();	
				break;
				case 6:
				break;
				}
			}
		}while(opPrincipal!=5);
		
	}
}
