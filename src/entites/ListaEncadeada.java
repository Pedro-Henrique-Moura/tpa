package entites;

public class ListaEncadeada {
    ListaTel Primeiro;
    ListaTel Ultimo;
    int tamanho_lista = 0;

    public ListaEncadeada() {
        Primeiro = null;
        Ultimo = Primeiro;
    }

    public void addItem(String nome, String telefone, String cidade, String pais) { //adiciona um item na lista encadeada presente em um indice X do vetor instanciada na classe Vetor.java
        
        if (Primeiro == null) {    //tratamento caso na posição que o item será inserido não tenha nenhum item/elemento
            Primeiro = new ListaTel();
            Ultimo = Primeiro;
            ListaTel novo_contato = new ListaTel();
            novo_contato.setNome(nome);
            novo_contato.setTelefone(telefone);
            novo_contato.setCidade(cidade);
            novo_contato.setPais(pais);
            novo_contato.setProximo(null);
            Ultimo.setProximo(novo_contato);
            Ultimo = novo_contato;
            tamanho_lista++;
        } else {       //tratamento caso na posição que o item será inserido já tenha outros itens
            ListaTel novo_contato = new ListaTel(); 
            novo_contato.setNome(nome);
            novo_contato.setTelefone(telefone);
            novo_contato.setCidade(cidade);
            novo_contato.setPais(pais);
            novo_contato.setProximo(null);
            Ultimo.setProximo(novo_contato);
            Ultimo = novo_contato;
            tamanho_lista++;
        }
    }

    public void imprimeLista(){   //realiza a impressão de todos itens presente em um determinado indice do vetor instanciado na classe Vetor.java, ou seja, imprime tudo que está presente na lista encadeada
        ListaTel percorre = Primeiro.getProximo();   

        while (percorre != null) {
            System.out.println("nome: " + percorre.getNome() +"\n");
            System.out.println("telefone: " + percorre.getTelefone() + "\n");
            System.out.println("cidade: " + percorre.getCidade() + "\n");
            System.out.println("pais: " + percorre.getPais() + "\n");
            System.out.println("\n----------------------------\n");
            percorre = percorre.getProximo();            
        }
    }

    public void removeItem(String nome) {  //realiza a remoção de um item da lista encadeada
        ListaTel remove = Primeiro.getProximo();
        ListaTel auxiliar = Primeiro;

        if(!(Primeiro == null)) {

            while(remove != null) {
                if (remove.getNome().equals(nome)) {   //verifica se o nome do item que eu quero remover é igual ao nome do item atual

                    if(remove.getProximo() == null) {
                        Ultimo = auxiliar;
                        Ultimo.setProximo(null);
                        remove = null;
                        tamanho_lista--;
                        break;
                    } else {
                        auxiliar.setProximo(remove.getProximo());
                        remove.setProximo(null);
                        remove = null;
                        tamanho_lista--;
                        break;
                    }
                }
                remove = remove.getProximo(); //seta próximo item para verificar se é esse elemento que desejo remover
                auxiliar = auxiliar.getProximo();  //auxiliar adquire o valor anterior do REMOVE
            }
        }

    }

    public void buscaItem(String nome) {  //realiza a busca de um item especifico
        ListaTel percorre = Primeiro.getProximo();
        boolean find = false;

        while (percorre != null && find != true) {
            if(percorre.getNome().equals(nome)){
                System.out.println("nome: " + percorre.getNome() +"\n");
                System.out.println("telefone: " + percorre.getTelefone() + "\n");
                System.out.println("cidade: " + percorre.getCidade() + "\n");
                System.out.println("pais: " + percorre.getPais() + "\n");
                find = true;
                break;
            }
            percorre = percorre.getProximo();
        }
        if (find == false){
            System.out.println("\nO contato buscado não foi encontrado :( \n");
        }
    }

    public void atualizarItem(String nome, String telefone, String cidade, String pais) {  //atualiza um item especifico, setando um novo telefone, cidade e pais
        ListaTel auxiliar = Primeiro; //como será feita iterações no atributo Primeiro da Lista Encadeada, eu salvo o valor do Primeiro em uma variável auxiliar
        Primeiro = Primeiro.getProximo();  
        boolean find = false;

        while (Primeiro != null) {
            if(Primeiro.getNome().equals(nome)){   //valido se o nome do item a ser atualizado é igual ao nome passado como parametro
                System.out.println("***Dados antes de serem atualizados***\n");
                System.out.println("nome: " + Primeiro.getNome() + "\n");
                System.out.println("telefone: " + Primeiro.getTelefone() + "\n");
                System.out.println("cidade: " + Primeiro.getCidade() + "\n");
                System.out.println("pais: " + Primeiro.getPais() + "\n");

                Primeiro.setTelefone(telefone);
                Primeiro.setCidade(cidade);
                Primeiro.setPais(pais);        
                System.out.println("***Dados após serem atualizados***\n");
                System.out.println("nome: " + Primeiro.getNome() + "\n");        
                System.out.println("telefone novo: " + Primeiro.getTelefone() + "\n");
                System.out.println("cidade nova: " + Primeiro.getCidade() + "\n");
                System.out.println("pais novo: " + Primeiro.getPais() + "\n");
                find = true;
                break;
            }
            Primeiro = Primeiro.getProximo(); //pr´xoimo item a ser verificado
        }
        Primeiro = auxiliar;
        if (find == false){
            System.out.println("\no contato a ser atualizado não foi encontrado :( \n");
        }
    }
    
    
}
