package entites;

public class ListaEncadeada {
    ListaTel Primeiro;
    ListaTel Ultimo;
    int tamanho_lista = 0;

    public ListaEncadeada() {
        Primeiro = null;
        Ultimo = Primeiro;
    }

    public void add_contato(String nome, String telefone, String cidade, String pais) {
        if (Primeiro == null) {
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
        } else {
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

    public void imprimeLista(){
        ListaTel percorre = Primeiro.getProximo();
        while (percorre != null) {
            System.out.println("nome: " + percorre.getNome() +"\n");
            System.out.println("telefone: " + percorre.getTelefone() + "\n");
            System.out.println("cidade: " + percorre.getCidade() + "\n");
            System.out.println("pais: " + percorre.getPais() + "\n");
            System.out.println("----------------------------\n");
            percorre = percorre.getProximo();
            
        }
    }

    public void removeItem(String nome) {
        ListaTel remove = Primeiro.getProximo();
        ListaTel auxiliar = Primeiro;

        if(!(Primeiro == null)) {

            while(remove != null) {
                if (remove.getNome().equals(nome)) {

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
                remove = remove.getProximo();
                auxiliar = auxiliar.getProximo();   
            }
        }

    }

    public void buscaItem(String nome) {
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

    public void atualizarItem(String nome, String telefone, String cidade, String pais) {
        ListaTel auxiliar = Primeiro;
        Primeiro = Primeiro.getProximo();
        boolean find = false;
        while (Primeiro != null) {
            if(Primeiro.getNome().equals(nome)){
                System.out.println("dados antes de serem atualizados \n");
                System.out.println("nome: " + Primeiro.getNome());
                System.out.println("telefone: " + Primeiro.getTelefone() + "\n");
                System.out.println("cidade: " + Primeiro.getCidade() + "\n");
                System.out.println("pais: " + Primeiro.getPais() + "\n");

                Primeiro.setTelefone(telefone);
                Primeiro.setCidade(cidade);
                Primeiro.setPais(pais);        
                System.out.println("dados após serem atualizados \n");
                System.out.println("nome: " + Primeiro.getNome());        
                System.out.println("telefone novo: " + Primeiro.getTelefone() + "\n");
                System.out.println("cidade nova: " + Primeiro.getCidade() + "\n");
                System.out.println("pais novo: " + Primeiro.getPais() + "\n");
                find = true;
                break;
            }
            Primeiro = Primeiro.getProximo();
        }
        Primeiro = auxiliar;
        if (find == false){
            System.out.println("\no contato a ser atualizado não foi encontrado :( \n");
        }
    }
    
    
}
