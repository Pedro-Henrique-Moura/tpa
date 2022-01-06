package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.Program;
import entites.ListaTel;

public class Program { 

	public static void main(String[] args) {

		String path = "C:\\Users\\PC\\Desktop\\Trab1\\agenda1.csv"; //Caminho de leitura do arquivo
		
		List<ListaTel> list = new ArrayList<ListaTel>(); //Cria uma lista para receber os produtos do arquivo
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) { //Pede para abrir o arquivo
			
			String line = br.readLine();  //Lê as linhas do arquivo
			line = br.readLine(); //Tenta ler a segunda linha, Todo o conteudo da linha está na variavel Line
			while (line != null) { //Enquanto as linhas não forem vazias, continua  a leitura
				
				String[] vect = line.split(","); // Faz o split toda vez que encontrar uma linha e transporta para um vetor
				String nome = vect[0]; //Recebe o nome da pessoa
				String telefone = vect[1]; //Converte para Inteiro
				String cidade = vect[2]; //Recebe cidade
				String pais = vect[3];// Recebe pais
				
				ListaTel lista = new ListaTel(nome,telefone,cidade,pais); //Lista Telefonica recebe uma nova lista
				list.add(lista); //Insere as informações na lista
				
				line = br.readLine(); // Le a proxima linha
			}	//Comentário
			
			System.out.println("Lista Telelefonica:"); // Print da lista
			for (ListaTel l : list) { //For para percorrer a lista
				System.out.println(l);
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}