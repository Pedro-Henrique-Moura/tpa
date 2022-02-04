package entites;

public class Vetor {
    ListaEncadeada vetor[];

    public Vetor() {
        vetor = new ListaEncadeada[3946];
    }

    public void inicializaListas() {
        for (int i = 0; i<3946; i++) {
            vetor[i] = new ListaEncadeada();
            System.out.println("***Iinicializando lista encadeada na posição: "+ i +"\n");
        }
    }


    public int funcaoHash(String palavra, int tamanho) {
        palavra = palavra.toLowerCase();
        int posicao = palavra.charAt(0);

        int hash = 7;
        for (int i = 0; i < palavra.length(); i++) {
            hash = hash + palavra.charAt(i);
        }
        posicao = posicao + hash;

        if(palavra.length() >= 6){            
            posicao = (posicao - 97) * (palavra.length() + palavra.length());
            if (posicao >= tamanho){
                posicao = posicao - tamanho;
            }
        } else {
            posicao = ((posicao - 97) + palavra.length()) * palavra.length();
            if (posicao < 10000) {
                posicao = posicao * palavra.length();
                if (posicao >= tamanho) {
                    posicao = posicao - 201;
                }
            }
        }
        //funcaohash(palavra);
        return posicao;
    }

    public int funcaoHashSecond(String palavra, int tamanho) {
        palavra = palavra.toLowerCase();
        int posicao = palavra.charAt(0);   

        if(palavra.length() >= 6){            
            posicao = (posicao - 97) * (palavra.length() + palavra.length());
            if (posicao >= tamanho){
                posicao = posicao - tamanho;
            }
        } else {
            posicao = ((posicao - 97) + palavra.length()) * palavra.length();
            if (posicao < 127) {
                posicao = posicao * palavra.length();
                if (posicao >= tamanho) {
                    posicao = posicao - 109;
                }
            }
        }
        return posicao;
    }
  
    public int funcaohash(String palavra) {
        int hash = 7;
        for (int i = 0; i < palavra.length(); i++) {
            hash = hash*31 + palavra.charAt(i);
        }
        System.out.println("***Hash secundario***:\n" + hash);
        return hash;
    }

    public int funcaohash_main(String palavra) {
        int hash = 1;
        for (int i = 0; i < palavra.length(); i++) {
            hash = hash + 3;
        }
        hash= palavra.length();
        System.out.println("***Hash secundario***:\n" + hash);
        return hash;
    }
    

    public void addTabela(String nome, String telefone, String cidade, String pais) {
        //int posicao = funcaoHash(nome,87999);
        int posicao = funcaoHashSecond(nome,3946);
        vetor[posicao].add_contato(nome, telefone, cidade, pais);
    }

    public void excluirItem(String nome) {
        //int posicao = funcaoHash(nome,87999);
        int posicao = funcaoHashSecond(nome,3946);
        int num_elementos_antigo = vetor[posicao].tamanho_lista;
        System.out.println("\n***Itens nesse indice:***\n");
        vetor[posicao].imprimeLista();
        vetor[posicao].removeItem(nome);
        
        System.out.println("***Itens nesse indice após excluir:***\n");
        vetor[posicao].imprimeLista();
        System.out.println("\n***N° elementos no vetor antes da exclusão:" + num_elementos_antigo +"\n");
        System.out.println("\n***N° elementos no vetor após exclusão:" + vetor[posicao].tamanho_lista +"\n");
    }

    public void buscaPalavra(String nome) {
        //int posicao = funcaoHash(nome,87999);
        int posicao = funcaoHashSecond(nome,3946);
        vetor[posicao].buscaItem(nome);
    }

    public void atualizarPalavra(String nome, String telefone, String cidade, String pais) {
        //int posicao = funcaoHash(nome,87999);
        int posicao = funcaoHashSecond(nome,3946);
        vetor[posicao].atualizarItem(nome, telefone, cidade, pais);
    }

    public void encontrarColisoes(){
        int colisao = 0;
        int maior = 0;
        for (int i = 0; i<3946; i++) {
            if (vetor[i].tamanho_lista > 1) {
                System.out.println("\n***Itens no indice "+ i +": \n"+vetor[i].tamanho_lista);
                colisao+=1;
                if (vetor[i].tamanho_lista >= maior){
                    maior = vetor[i].tamanho_lista;
                }
            }
        }

        System.out.println("\nNúmero de colisões: " + colisao + "\n");
        System.out.println("\nMaior número de colisões encontrado: " + maior + "\n");

    }
    
}
