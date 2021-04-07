package aplicacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

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
				log("Opção 1 - Recepcionar Clientes");
			}
			if (op == 2) {
				log("Opção 2 - Consultar clientes");
			}
			if (op == 3) {
				log("Opção 3 - Atender clientes");
			}
			if (op == 4) {
				log("Opção 4 - Liberar todos os clientes");
			}
			if (op == 5) {
				log("Opção 5 - Quantidade de clientes");
			}
			if (op == 6) {
				log("Opção 6 - Localizar cliente por número");
			}
			if (op == 7) {
				log("Opção 7 - Localizar cliente por nome");
			}
			if (op == 8) {
				log("Opção 8 - Emitir relatório de clientes");
			}
			if (op == 9) {
				log("Opção 9 - Ver relatório de clientes");
			}
			if (op == 10) {
				log("Opção 10 - Filtrar clientes por valor");
			}
			if (op == 11) {
				log("Opção 11 - Ver endereços hash");
			}
			if (op == 12) {
				log("Opção 12 - Sobre");
			}
		} while (op != 13);
		System.out.println("Programa finalizado em: "+getDateTime());
		JOptionPane.showMessageDialog(null, "PROGRAMA FINALIZADO");
	}
	
	public static void log(String texto) {
		System.out.println("Acessou: "+texto+" - "+getDateTime());
		
	}
	
	public static String getDateTime() {
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
