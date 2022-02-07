package entites;

public class Vetor {
    ListaEncadeada vetor[];

    public Vetor(int tamanho) {           //instanciação de um vetor do tipo ListaEncadeada, como parametro é repassado o tamanho do vetor                
        vetor = new ListaEncadeada[tamanho];
    }

    public void inicializaListas(int tamanho) {    //inicializa cada posição do vetor, instanciando cada indice de acordo com o tamanho da tabela hash (tamanho do vetor)
        for (int i = 0; i<tamanho; i++) {
            vetor[i] = new ListaEncadeada();
            System.out.println("***Iinicializando lista encadeada na posição: "+ i +"\n");
        }
    }

  
    public int funcaohash(String palavra, int tamanho) {   //função responsável por retornar uma posição na tabela hash, dado que eu passo como parametro uma chave
        palavra.toLowerCase();
        int hash = 7;
        for (int i = 0; i < palavra.length(); i++) {
            hash = hash*31 + palavra.charAt(i);
        }
        if (hash < 0){
            hash*=-1;
        }
        System.out.println("\n***Posicao na Tabela Hash: " + hash%tamanho +"\n");
        return hash%tamanho;
    }
 

    public void addTabela(String nome, String telefone, String cidade, String pais, int tamanho) { //função que é chamada para adicionar um novo item na Lista Encadeada que estará presente em uma das posições do Vetor 
        int posicao = funcaohash(nome, tamanho);
        vetor[posicao].addItem(nome, telefone, cidade, pais);
    }

    public void excluirItem(String nome, int tamanho) {  //removo um item que está inserido na tabela hash, chamando a função removeItem da Lista Encadeada
        int posicao = funcaohash(nome, tamanho);
        int num_elementos_antigo = vetor[posicao].tamanho_lista;
        System.out.println("\n***Itens nesse indice:***\n");
        vetor[posicao].imprimeLista();   //função que imprime por completo todos itens (contatos) que estão em uma determinada posição no Vetor de Listas Encadeadas
        vetor[posicao].removeItem(nome);
        
        System.out.println("***Itens nesse indice após excluir:***\n");
        vetor[posicao].imprimeLista();
        System.out.println("\n***N° elementos no indice antes da exclusão:" + num_elementos_antigo +"\n");
        System.out.println("\n***N° elementos no indice após exclusão:" + vetor[posicao].tamanho_lista +"\n");
    }

    public void buscaPalavra(String nome, int tamanho) { //função responsável por encontrar na tabela hash (vetor de listas encadeadas) um item, dado que eu passo sua chave como parametro (nome)
        int posicao = funcaohash(nome, tamanho);
        vetor[posicao].buscaItem(nome);
    }

    public void atualizarPalavra(String nome, String telefone, String cidade, String pais, int tamanho) { //função responsável por atualizar um item presente na tabela hash, recebo como parametro as informações novas
        int posicao = funcaohash(nome, tamanho);
        vetor[posicao].atualizarItem(nome, telefone, cidade, pais);
    }

    public void encontrarColisoes(int tamanho){ //função criada para verificar quantas colisões ocorreram na tabela hash, ao final retorno também o maior número de colisões encontrados em um único indice no Vetor de Listas Encadeadas 
        int colisao = 0;
        int maior = 0;

        for (int i = 0; i<tamanho; i++) {
            if (vetor[i].tamanho_lista > 1) {
                System.out.println("\n***Itens no indice "+ i +": \n"+vetor[i].tamanho_lista);
                colisao+=vetor[i].tamanho_lista;
                if (vetor[i].tamanho_lista >= maior){
                    maior = vetor[i].tamanho_lista;
                }
            }
        }

        System.out.println("\nNúmero de colisões: " + colisao + "\n"); //número de colisões no geral
        System.out.println("\nMaior número de colisões encontrado em um único indice: " + maior + "\n"); //informa o maior número de colisões encontrados em um único indice

    }
    
}
