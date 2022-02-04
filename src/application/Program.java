package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import application.Program;
import entites.Vetor;


public class Program { 

	public static void main(String[] args) {


		System.out.println("------ Opções do menu ------ \n 1 - Abrir/carregar arquivo \n 2 - Pesquisar contato(informe o nome completo) \n 3 - Atualizar contato (informe o nome completo) \n 4 - Excluir contato (informe nome completo) \n 5 - Pesquisar contato excluido \n 0 - Sair");
		int opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção válida: "));
		Vetor lista_contato = new Vetor();
		lista_contato.inicializaListas();
		
		while (opcao != 0){
			if(opcao == 1){			
				
				//Vetor lista_contatoAux = new Vetor();
				//lista_contatoAux.inicializaListas();
				
				String path = "D:\\Faculdade  - trabalho\\TPA\\entrada1.csv"; //Caminho de leitura do arquivo agenda1.csv  D:\\Faculdade  - trabalho\\TPA\\				
				System.out.println("\nCarga do arquivo...\n");
				try (BufferedReader br = new BufferedReader(new FileReader(path))) { //Pede para abrir o arquivo
					
					String line = br.readLine();  //Lê as linhas do arquivo
					while (line != null) { //Enquanto as linhas não forem vazias, continua  a leitura
						System.out.println("\nLendo arquivo...\n");
						String[] vect = line.split(","); // Faz o split toda vez que encontrar uma linha e transporta para um vetor
						String nome = vect[0]; //Recebe o nome da pessoa
						String telefone = vect[1]; //Recebe o telefone
						String cidade = vect[2]; //Recebe cidade
						String pais = vect[3];// Recebe pais
						//lista_contato.funcaohash(nome);
						lista_contato.addTabela(nome, telefone, cidade, pais); //chama método responsável por adicionar contato na lista encadeada		
						//lista_contatoAux.addTabela(nome, telefone, cidade, pais); //chama método responsável por adicionar contato na lista encadeada										
						line = br.readLine(); // Le a proxima linha
					}
					//lista_contato = lista_contatoAux;		
				} catch (IOException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
			if (opcao == 2){
				String nome_contato = JOptionPane.showInputDialog("Digite o nome completo de um dos contatos a ser buscado: ");
				lista_contato.buscaPalavra(nome_contato);
			}

			if (opcao == 3) {
				String nome_contato = JOptionPane.showInputDialog("Digite o nome completo de um dos contatos a ser atualizado: ");
				String telefone = JOptionPane.showInputDialog("Digite o telefone a ser atualizado: ");
				String cidade = JOptionPane.showInputDialog("Digite a cidade a ser atualizado: ");
				String pais = JOptionPane.showInputDialog("Digite o pais a ser atualizado: ");
				lista_contato.atualizarPalavra(nome_contato, telefone, cidade, pais);
			}

			if (opcao == 4) {
				String nome_contato = JOptionPane.showInputDialog("Digite o nome completo de um dos contatos a ser excluido: ");
				lista_contato.excluirItem(nome_contato);
			}

			if (opcao == 5) {
				String nome_contato = JOptionPane.showInputDialog("Digite o nome completo do contato excluido: ");
				lista_contato.buscaPalavra(nome_contato);
			}

			if (opcao == 6){
				lista_contato.encontrarColisoes();
			}

			System.out.println("------ Opções do menu ------ \n 1 - Abrir/carregar arquivo \n 2 - Pesquisar contato(informe o nome completo) \n 3 - Atualizar contato (informe o nome completo) \n 4 - Excluir contato (informe nome completo) \n 5 - Pesquisar contato excluido \n 6 - Verificar número de colisões \n 0 - Sair\n");
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção válida: "));

		}

	}
}