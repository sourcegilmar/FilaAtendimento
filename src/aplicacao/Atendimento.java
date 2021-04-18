/*
 * TODO
 * 1 - Alterar para gravar sempre em maiúsculas na inclusão
 * 2 - Criar um método de agende de erro para log das exceções
 */
package aplicacao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Atendimento {
	static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private static class Atende {
		public int cartao;
		public String nome;
		public String sobreNome;
		double valor;
		public Atende prox;
	}

	public static void main(String[] args) {

		Atende inicio = null;
		Atende fim = null;
		Atende aux;
		int op = 0;

		do {

			try {

				String texto = "\nMENU DE OPÇÕES\n" + "\n1  - Recepcionar cliente"
						+ "\n2  - Consultar clientes a serem atendidos" + "\n3  - Atender cliente"
						+ "\n4  - Liberar todos os clientes" + "\n5  - Verificar quantidade de clientes a atender"
						+ "\n6  - Localizar cliente por número" + "\n7  - Localizar cliente por nome"
						+ "\n8  - Emitir relatório de clientes" + "\n9  - Ver relatório de clientes"
						+ "\n10 - Filtrar clientes por valor" + "\n11 - Ver endereços hash" + "\n12 - Sobre"
						+ "\n13 - Sair\n";
				op = Integer.parseInt(JOptionPane.showInputDialog(texto, "1"));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"A tecla <<Cancelar>> foi acionada!\nEscolha a opção 13 para encerrar.", "Mensagem",
						JOptionPane.CLOSED_OPTION);
				continue;
			}
			if (op < 1 || op > 13) {
				System.out.println("Opção Inválida!!");
			}
			if (op == 1) {
				int numero = 0;
				try {
					numero = Integer.parseInt(JOptionPane.showInputDialog("NÚMERO DO CARTÃO: ", "0"));
				} catch (NumberFormatException e) {

					continue;
				}
				aux = inicio;
				boolean achou = false;
				while (aux != null) {
					if (aux.cartao == numero) {
						achou = true;
						JOptionPane.showMessageDialog(null, "Esse número do cartão já foi usado.\nFavor verificar!");
						break;
					}
					aux = aux.prox;
				}
				if (achou == false) {
					Atende novo = new Atende();
					novo.cartao = numero;

					try {
						novo.nome = JOptionPane.showInputDialog("NOME: ", "");
						novo.sobreNome = JOptionPane.showInputDialog("SOBRENOME: ", "");
						novo.valor = Double.parseDouble(JOptionPane.showInputDialog("VALOR: ", "0"));

						novo.nome = novo.nome.toUpperCase();
						novo.sobreNome = novo.sobreNome.toUpperCase();

					} catch (NumberFormatException e) {
						continue;
					}

					if (inicio == null) {
						inicio = novo;
						fim = novo;
					} else {
						fim.prox = novo;
						fim = novo;
					}
					JOptionPane.showMessageDialog(null,
							"O cartão número " + novo.cartao + ", foi inserido para atendimento: ",
							"MENSAGEM DO PROGRAMA", JOptionPane.CLOSED_OPTION);
					log("Opção 1 - Recepcionar Clientes");
				}

			}
			if (op == 2) {
				if (inicio == null) {
					JOptionPane.showMessageDialog(null, "O atendimento está vazio!", "Mensagem do Programa",
							JOptionPane.CLOSED_OPTION);
					;
				} else {
					JTextArea saida = new JTextArea(6, 45); // HEIGHT X WIDTH
					JScrollPane scroll = new JScrollPane(saida);
					saida.append("CARTÃO\t" + "NOME\t" + "SOBRENOME\t" + "VALOR\n");
					saida.append("-------------------------------------------------------------------------\n");
					aux = inicio;
					while (aux != null) {
						saida.append(aux.cartao + "\t" + aux.nome + "\t" + aux.sobreNome + "\t" + aux.valor + "\n");
						aux = aux.prox;
					}
					saida.append("\n");
					JOptionPane.showMessageDialog(null, scroll, "CONSULTAR DADOS DO ATENDIMENTO",
							JOptionPane.CLOSED_OPTION);
					log("Opção 2 - Consultar clientes");
				}
			}
			if (op == 3) {
				if (inicio == null) {
					JOptionPane.showMessageDialog(null, "O atendimento está vazio!", "Mensagem do Programa",
							JOptionPane.CLOSED_OPTION);
					;
				} else {
					JOptionPane.showMessageDialog(null,
							"CARTÃO:  " + inicio.cartao + ", NOME: " + inicio.nome + " foi atendido(a)!",
							"MENSAGEM DO PROGRAMA", JOptionPane.CLOSED_OPTION);
					inicio = inicio.prox;
					log("Opção 3 - Atender clientes");
				}
			}
			if (op == 4) {
				if (inicio == null) {
					JOptionPane.showMessageDialog(null, "O atendimento está vazio!", "Mensagem do Programa",
							JOptionPane.CLOSED_OPTION);
					;
				} else {
					inicio = null;
					JOptionPane.showMessageDialog(null, " * * O ATENDIMENTO FOI LIBERADO * *", "MENSAGEM DO PROGRAMA",
							JOptionPane.CLOSED_OPTION);
					log("Opção 4 - Liberar todos os clientes");
				}
			}
			if (op == 5) {
				aux = inicio;
				int n = 0;
				double soma = 0;
				while (aux != null) {
					soma = soma + aux.valor;
					aux = aux.prox;
					n++;
				}
				JOptionPane.showMessageDialog(null,
						"O ATENDIMENTO CONTÉM: " + n + " ELEMENTOS.\nVALOR TOTAL: " + soma + "\n",
						"MENSAGEM DO PROGRAMA", JOptionPane.CLOSED_OPTION);
				log("Opção 5 - Quantidade de clientes");
			}
			if (op == 6) {
				int cartao = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do cartão", "0"));
				aux = inicio;
				int posicao = 1;
				while (aux != null) {
					if (cartao == aux.cartao) {
						String texto = "CARTÃO: " + aux.cartao + "\n" + "NOME: " + aux.nome + "\n" + "SOBRENOME: "
								+ aux.sobreNome + "\n" + "VALOR: " + aux.valor + "\n" + "POSIÇÃO: " + posicao
								+ "a. POSIÇÃO";
						JOptionPane.showMessageDialog(null, "DADOS DO CLIENTE: \n\n" + texto, "MENSAGEM DO PROGRAMA",
								JOptionPane.CLOSED_OPTION);
					}
					posicao++;
					aux = aux.prox;
				}
				log("Opção 6 - Localizar cliente por número");
			}
			if (op == 7) {
				String nome = JOptionPane.showInputDialog("Nome do cliente", "");
				aux = inicio;
				int posicao = 1;
				while (aux != null) {
					if (aux.nome.equals(nome)) {
						String texto = "CARTÃO: " + aux.cartao + "\n" + "NOME: " + aux.nome + "\n" + "SOBRENOME: "
								+ aux.sobreNome + "\n" + "VALOR: " + aux.valor + "\n" + "POSIÇÃO: " + posicao
								+ "a. POSIÇÃO";
						JOptionPane.showMessageDialog(null, "DADOS DO CLIENTE: \n\n" + texto, "MENSAGEM DO PROGRAMA",
								JOptionPane.CLOSED_OPTION);
					}
					posicao++;
					aux = aux.prox;
				}
				log("Opção 7 - Localizar cliente por nome");
			}
			if (op == 8) {
				if (inicio == null) {
					JOptionPane.showMessageDialog(null, "O atendimento está vazio!", 
							"Mensagem do Programa",	JOptionPane.CLOSED_OPTION);
				} else {
					aux = inicio;
									
					// Faz a verificação da existência da pasta
					// se a pasta não existir => cria!
					File file = new File("C:/DADOS");  
					if (!file.exists()) {
					    file.mkdirs();
					}					
					try {
						FileWriter arq = new FileWriter("c:\\Dados\\Atendimento.txt");
						PrintWriter gravar = new PrintWriter(arq);
						while (aux != null) {
							gravar.printf("%d, %s, %s, %.2f %n", aux.cartao, aux.nome, aux.sobreNome, +aux.valor);
							aux = aux.prox;
						}
						gravar.printf("%s %n", "--------------------------");
						gravar.printf("%s %n", "copyright (c) by: Professor Gilmar Borba");
						arq.close();
					} catch (IOException e) {
						System.out.println("MENSAGEM / CLASS ArquivoTexto:\nErro ao tentar gravar no arquivo");
					}

					JOptionPane.showMessageDialog(null, "ARQUIVO GRAVADO COM SUCESSO", "MENSAGEM DO SISTEMA",
							JOptionPane.CLOSED_OPTION);
				}
				log("Opção 8 - Emitir relatório de clientes (Arquivos)");
			}
			if (op == 9) {
				int resposta = JOptionPane.showConfirmDialog(null, 
						"DESEJA VER ARQUIVO?", "MENSAGEM",JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					try {
						Process pro = Runtime.getRuntime().exec("cmd.exe /c  c://Dados//Atendimento.txt");
						pro.waitFor();
					} catch (Exception e) {
						System.out.println("Erro . . . ");
					}
				}
				log("Opção 9 - Ver relatório de clientes (Arquivos)");
			}
			if (op == 10) {
				double filtro = Double.parseDouble(
						JOptionPane.showInputDialog("FILTRAR ATENDIMENTOS PARA VALORES SUPERIORES A: ", ""));
				JTextArea saida = new JTextArea(6, 45); // HEIGHT X WIDTH
				JScrollPane scroll = new JScrollPane(saida);
				saida.append("CARTÃO\t" + "NOME\t" + "SOBRENOME\t" + "VALOR\n");
				saida.append("----------------------------------------------------------------------------\n");
				aux = inicio;
				int posicao = 1;
				while (aux != null) {
					if (aux.valor > filtro) {
						saida.append(aux.cartao + "\t" + aux.nome + "\t" + aux.sobreNome + "\t" + aux.valor + "\n");
					}
					posicao++;
					aux = aux.prox;
				}
				saida.append("\n");
				JOptionPane.showMessageDialog(null, scroll, "ATENDIMENTOS COM VALORES SUPERIORES A: " + filtro,
						JOptionPane.CLOSED_OPTION);
				log("Opção 10 - Filtrar clientes por valor");
			}
			if (op == 11) {
				if (inicio == null) {
					JOptionPane.showMessageDialog(null, "O atendimento está vazio!", 
							"Mensagem do Programa",	JOptionPane.CLOSED_OPTION);
				} else {
					JTextArea saida = new JTextArea(7, 45); // HEIGHT X WIDTH
					JScrollPane scroll = new JScrollPane(saida);
					saida.append("NOME\t" + "ENDEREÇO\tPROX\t\n");
					saida.append("-------------------------------------------------------------\n");
					aux = inicio;
					while (aux != null) {
						if (aux.prox != null)
							saida.append(aux.nome + "\t" + aux.hashCode() + "\t" + aux.prox.hashCode() + "\n");
						else
							saida.append(aux.nome + "\t" + aux.hashCode() + "\tfim\n");

						aux = aux.prox;
					}
					saida.append("\n");
					JOptionPane.showMessageDialog(null, scroll, "CONSULTAR DADOS DO ATENDIMENTO",
							JOptionPane.CLOSED_OPTION);
				}
				log("Opção 11 - Ver endereços hash");
			}
			if (op == 12) {
				log("Opção 12 - Sobre");
			}
		} while (op != 13);
		System.out.println("Programa finalizado em: " + getDateTime());
		JOptionPane.showMessageDialog(null, "PROGRAMA FINALIZADO");
	}

	public static void log(String texto) {
		System.out.println("Acessou: " + texto + " - " + getDateTime());

	}

	public static String getDateTime() {
		Date date = new Date();
		return dateFormat.format(date);
	}

	/*
	 * Eclipse IDE 2019-12 Version: 2019-12 (4.14.0) JavaSE-1.8 *
	 */

}
