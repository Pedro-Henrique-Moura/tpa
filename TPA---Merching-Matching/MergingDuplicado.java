
import java.util.Arrays; 

public class MergingDuplicado {

    public MergingDuplicado(){}

    public void mergingDuplicado(int vet[], int vetor[], int list[]){


        int i = 0; //indice do vet[]
        int j = 0; //indice do vetor[]
        int indice = 0; //indice do list[]

        while (i < vet.length){ //enquanto tamanho do vet[] for válido

            if(j < vet.length){ //caso o vetor[] chegue ao final primeiro, na clausula else é adicionado o elemento final do vet[]
            
                if (vet[i] < vetor[j]){ //se um elmento do vet[] for menor, o mesmo é adicionado e os indices são incrementados
                    list[indice] = vet[i];
                    i+=1;
                    indice+=1;
                } else {  //caso o elmento menor seja do vetor[], o mesmo é realizado conforme dito acima
                    list[indice] = vetor[j];
                    j+=1;
                    indice+=1;
                }
            } else {
                list[indice] = vet[i];
                i+=1;
                indice+=1;
            }

        }
        // verifica se sobrou algum elemento a ser inserido, e insere no final
        if (j < vetor.length){
            for (int y = j; y < vetor.length;y++){
                list[indice] = vetor[y];
                indice+=1;
            }
        }

    }

    public void mergingDuplicadoThree(int vet[], int vetor[], int v[], int list[]){


        int i = 0; //indice do vet[]
        int j = 0; // indice do vetor[]
        int k = 0; // indice do v[]
        int indice = 0; //indice do list[]

        while (i < vet.length && j < vetor.length && k < v.length){ //enquanto qualquer um dos vetores tiver tamanho válido

            //segue a mesma lógica do merging duplicado para 2 vetores, porém é considerado agora um vetor adicional
            if (vet[i] < vetor[j]){   //valido primeiro se o vet[i] é menor do que o vetor[j], caso seja, verifico se o vet[i] é menor que o v[k]
                if (vet[i] < v[k]){  //faço incremento de indice igual é feito no método de merging anterior e no da classe Merging.java
                    list[indice] = vet[i];
                    i+=1;
                    indice+=1;
                } else {
                    list[indice] = v[k];
                    k+=1;
                    indice+=1;
                }              
            } else {
                if(vetor[j] < v[k]){
                    list[indice] = vetor[j];
                    j+=1;
                    indice+=1;
                } else {
                    list[indice] = v[k];
                    k+=1;
                    indice+=1;
                }
                
            }

        }
        //verifico no final se há algum vetor que tem elementos que ainda não foram adicionados
        if (i == vet.length){
            mergingAux(vetor, v, list, j, k, indice); //a função merginAux recebe dois vetores como parametro, além dos indices atualizados e tem a mesma estrutura da merginDuplicada
        }

        if (j == vetor.length){
            mergingAux(vet, v, list, i, k, indice);
        }

        if (k == v.length){
            mergingAux(vet, vetor, list, i , j, indice);
        }

    }


    public void mergingAux(int vet[], int vetor[], int list[], int indice_v1, int indice_v2, int indice_v3){

        //função semelhante a merginDuplicada, segue a mesma lógica, porém recebemos como parametro os indices de cada vetor, pois nela serão acrescentados os elementos que ainda não foram adicionados no merginDuplicadoThree (p/ 3 vetores)
        int i = indice_v1;
        int j = indice_v2;
        int indice = indice_v3;

        while (i < vet.length){
            
            if(j < vet.length){
                if (vet[i] < vetor[j]){
                    list[indice] = vet[i];
                    i+=1;
                    indice+=1;
                } else {
                    list[indice] = vetor[j];
                    j+=1;
                    indice+=1;
                }
            } else {
                    list[indice] = vet[i];
                    i+=1;
                    indice+=1;
            }
        }

        if (j < vetor.length){
            for (int y = j; y < vetor.length;y++){
                list[indice] = vetor[y];
                indice+=1;
            }
        }

    }

    //imprime vetor completo
    public void imprimeVetor(int list[]){
        System.out.println(Arrays.toString(list));
    }

    
}
