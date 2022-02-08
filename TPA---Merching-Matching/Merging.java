import java.util.Arrays; 

public class Merging {

    public Merging(){}

    public void merging(int vet[], int vetor[], int list[]){


        int i = 0; //indice do vet[]
        int j = 0; //indice do vetor[]
        int indice = 0; //indice do vetor list[]

        while (i < vet.length){
            
            if(j < vet.length){ //enquanto o tamanho do vetor vetor[] for válido...
                if (vet[i] < vetor[j]){ //verifica se um elemento do vetor vet[] é menor do que o de vetor[]
                    if ((existe(vet[i], list))){  //valida se o elemento já existe na lista, caso não exista, adiciona na lista
                        list[indice] = vet[i]; //add elemento
                        i+=1; //incremente indice do vet[]
                        indice+=1; //incremente indice do list[]
                    } else {
                        i+=1; //caso já exista, incremente indice do vet[] apenas
                    }
                } else {
                    if ((existe(vetor[j], list))){ //segue a mesma lógica das linhas acima, porém tendo base o vetor[]
                        list[indice] = vetor[j];
                        j+=1;
                        indice+=1;
                    } else {
                        j+=1;
                    }
                }
            } else { //caso o vetor vet chegue ao final, acrescento o último elemento que sobrou no vetor vet[]
                if ((existe(vet[i], list))){
                    list[indice] = vet[i];
                    i+=1;
                    indice+=1;
                } else {
                    i+=1;
                }

            }
                
            

        }

        if (j < vetor.length){   //caso ainda tenha sobrado elementos no vetor[], adiciona ao final da lista de saída (list)
            for (int y = j; y < vetor.length;y++){
                list[indice] = vetor[y];
                indice+=1;
            }
        }

    }

    public void mergingThree(int vet[], int vetor[], int v[], int list[]){ //merging para 3 vetores como entrada


        int i = 0; //indice do vet[]
        int j = 0; //indice do vetor[]
        int k = 0; //indice do v[]
        int indice = 0; //indice do list[]

        while (i < vet.length && j < vetor.length && k < v.length){ //enquanto nenhum dos 3 vetores chegar ao final, executa...
            
            if (vet[i] < vetor[j]){ //verifico se o elem. do vet[] é menor do que o do vetor[]
                if (vet[i] < v[k]){  // se for menor, verifica se é menor do que o do vetor v[]
                    if ((existe(vet[i], list))){ //valida se o elemento já exista na lista de saida (list)
                        list[indice] = vet[i];  //add na lista final
                        i+=1;  //incrementa indice i do vet[]
                        indice+=1; //incrementa indice do list[]
                    } else {
                        i+=1; //incrementa indice i do vet[]
                    }
                } else {   //caso o elem. v[k] for menor que o vet[i], verifico se o mesmo existe na lista final e incremente indice k do v[], e indice do list[] 
                    if ((existe(v[k], list))){
                        list[indice] = v[k];
                        k+=1;
                        indice+=1;
                    } else {
                        k+=1;
                    }
                }              
            } else {
                if(vetor[j] < v[k]){ //caso o elem. vetor[j] for menor que o v[k], verifico se o mesmo existe na lista final e incremente indice j do vetor[], e indice do list[] 
                    if ((existe(vetor[j], list))){
                        list[indice] = vetor[j];
                        j+=1;
                        indice+=1;
                    } else {
                        j+=1;
                    }
                } else {  //caso o elem. v[k] for menor que o vetor[j], verifico se o mesmo existe na lista final e incremente indice k do v[], e indice do list[] 
                    if ((existe(v[k], list))){
                        list[indice] = v[k];
                        k+=1;
                        indice+=1;
                    } else {
                        k+=1;
                    }
                }
                
            }

        }
        //verifico se sobrou algum elemento em alguns dos 3 vetores de entrada
        //chama mergingAux que vai passar o último valor dos indices de cada vetor como parametro, além dos próprios vetores
        //merginAux possui a mesma lógica da função mergin usada para dois vetores, logo ela recebe dois vetores.
        if (i == vet.length){
            mergingAux(vetor, v, list, j, k, indice);
        }

        if (j == vetor.length){
            mergingAux(vet, v, list, i, k, indice);
        }

        if (k == v.length){
            mergingAux(vet, vetor, list, i , j, indice);
        }

    }

    //usada como auxiliar da função merginThree
    public void mergingAux(int vet[], int vetor[], int list[], int indice_v1, int indice_v2, int indice_v3){

        int i = indice_v1;
        int j = indice_v2;
        int indice = indice_v3;

        while (i < vet.length){
            
            if(j < vet.length){
                if (vet[i] < vetor[j]){
                    if ((existe(vet[i], list))){
                        list[indice] = vet[i];
                        i+=1;
                        indice+=1;
                    } else {
                        i+=1;
                    }
                } else {
                    if ((existe(vetor[j], list))){
                        list[indice] = vetor[j];
                        j+=1;
                        indice+=1;
                    } else {
                        j+=1;
                    }
                }
            } else {
                if ((existe(vet[i], list))){
                    list[indice] = vet[i];
                    i+=1;
                    indice+=1;
                } else {
                    i+=1;
                }
            }
        }

        if (j < vetor.length){
            for (int y = j; y < vetor.length;y++){
                list[indice] = vetor[y];
                indice+=1;
            }
        }

    }

    //verifica se um elemento a ser inserido já existe na lista final (list[])
    public boolean existe(int elem, int list[]){
        if (list != null){
            for (int i = 0; i < list.length;i++){
                if (elem == list[i]){
                    return false;
                }
            }
        } else {
            return true;
        }
        return true;         
    }

    //imprime um vetor inteiro
    public void imprimeVetor(int list[]){
        System.out.println(Arrays.toString(list));
    }

    
    //na main é criado um vetor auxiliar que recebera o valor final da lista de saída e na sua impressão não sairá com zeros ao final (os zeros no final saem pois ao declarar o tamanho do vetor, 
    //o mesmo é preenchido com 0 automaticamente em todas posições)
    public int verificaZeros(int list[]){
        int count = 0;      //verifico quantos zeros existem no final de um vetor e retorno através do contado
        for (int i = 0; i < list.length; i++){
            if (list[i] == 0 && i != 0){
                count+=1;
            }
        }
        return count;
    }

}
