package entites;
//import java.io.Serializable;

//implements Serializable- Caso necessário

public class ListaTel { //Classe que contém as variáveis da lista telefonica - Serializable permite que eu transforme os dados do arquivo em bytes caso precise salvar em disco

    private String nome;
    private String telefone;
    private String cidade;
    private String pais;

    public ListaTel(){ //Constructor
    }

    public ListaTel(String nome, String telefone, String cidade, String pais){ //Constructor
        super();
        this.nome= nome;
        this.telefone = telefone;
        this.cidade = cidade;
        this.pais = pais;
    }
    //Gets e sets
    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getCidade(){
        return cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    public String getPais(){
        return pais;
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    @Override 
    public String toString(){
        return "Lista Tel: [nome = " + nome + ",telefone = " + telefone + ",cidade = " + cidade + ",pais = " + pais;
    }
}





