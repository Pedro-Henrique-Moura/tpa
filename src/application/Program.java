package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import application.Program;
import entites.ListaTel;
import entites.MergeSort;

public class Program { 

	public static void main(String[] args) {

		String path = "C:\\Users\\PC\\Desktop\\Trab1\\teste.txt"; //Caminho de leitura do arquivo agenda1.csv
		
		List<ListaTel> list = new ArrayList<ListaTel>(); //Cria uma lista para receber os endereços do arquivo
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) { //Pede para abrir o arquivo
			
			String line = br.readLine();  //Lê as linhas do arquivo
			line = br.readLine(); //Tenta ler a segunda linha, Todo o conteudo da linha está na variavel Line
			while (line != null) { //Enquanto as linhas não forem vazias, continua  a leitura
				
				String[] vect = line.split(","); // Faz o split toda vez que encontrar uma linha e transporta para um vetor
				String nome = vect[0]; //Recebe o nome da pessoa
				String telefone = vect[1]; //Recebe o telefone
				String cidade = vect[2]; //Recebe cidade
				String pais = vect[3];// Recebe pais
				
				ListaTel lista = new ListaTel(nome,telefone,cidade,pais); //Instancia uma lista a partir da classe ListaTel 
				list.add(lista); //Insere as informações na lista
				
				line = br.readLine(); // Le a proxima linha
			}	
			
			System.out.println("Lista Telefonica:"); // Print da lista
			for (ListaTel l : list) { //For para percorrer a lista
				System.out.println(l);
			}

			//Lista ordenada
			System.out.println("Lista Ordenada");

			//Compara a lista baseado no comparatorNomeCrescente
			Collections.sort(list, new MergeSort());
			for(ListaTel cliente : list){
				System.out.println(cliente.toString());
			}
			
		}

		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		//Abertura do segundo arquivo

		String path2 = "C:\\Users\\PC\\Desktop\\Trab1\\teste2.txt"; //Caminho de leitura do arquivo agenda1.csv
		List<ListaTel> list2 = new ArrayList<ListaTel>(); //Cria uma lista para receber os endereços do arquivo2

		try (BufferedReader br = new BufferedReader(new FileReader(path2))) { //Pede para abrir o arquivo
			
			String line = br.readLine();  //Lê as linhas do arquivo
			line = br.readLine(); //Tenta ler a segunda linha, Todo o conteudo da linha está na variavel Line
			while (line != null) { //Enquanto as linhas não forem vazias, continua  a leitura
				
				String[] vect2 = line.split(","); // Faz o split toda vez que encontrar uma linha e transporta para um vetor
				String nome = vect2[0]; //Recebe o nome da pessoa
				String telefone = vect2[1]; //Recebe o telefone
				String cidade = vect2[2]; //Recebe cidade
				String pais = vect2[3];// Recebe pais
				
				ListaTel lista2 = new ListaTel(nome,telefone,cidade,pais); //Instancia uma lista a partir da classe ListaTel 
				list2.add(lista2); //Insere as informações na lista
				
				line = br.readLine(); // Le a proxima linha
			}	

			System.out.println("Lista Telefonica2:"); // Print da lista
			for (ListaTel l2 : list2) { //For para percorrer a lista
				System.out.println(l2);
			}
			
			//Lista ordenada
			System.out.println("Lista Ordenada2");

			//Compara a lista baseado no comparatorNomeCrescente
			Collections.sort(list2, new MergeSort());
			for(ListaTel cliente : list2){
				System.out.println(cliente.toString());
			}
			
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}


		


		
			
		

	}
}