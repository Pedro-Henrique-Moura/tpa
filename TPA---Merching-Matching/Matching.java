import java.util.Arrays; 

public class Matching {

    public Matching(){}
    
    public void matching(int vet[], int vetor[], int list[]){


        int i = 0; //indice do vet[]
        int indice = 0; //indice do list

        while (i < vet.length){ 
            
            for (int j = 0; j < vetor.length; j++){ //um elemento do vet[] é pego como base a cada iteração do while
                // a cada iteração verifico dentro do FOR se o elemento é igual a algum que exista no vetor[] secundário
                if (vet[i] == vetor[j]){ //sendo igual, adiciona na lista e incrementa indice do list[] (vetor saída)
                    list[indice] = vet[i];
                    indice+=1;
                }
            }
            i+=1; //pula para o próximo elemento
        }

    }

    public int[] matchingThree(int vet[], int vetor[], int v[], int list[]){


        int i = 0; //indice do vet[]
        int elem = 0; //guarda elemento do list[] que sofrerá um merging duplicado
        int l[]; //lista de saída
        l = new int[list.length];
        int indice = 0; //indice do vetor l[]

        MergingDuplicado mergeDuplicado = new MergingDuplicado();
        mergeDuplicado.mergingDuplicadoThree(vet, vetor, v, list); //vetor list[] sofre um merging duplicado, através disso será selecionado na lista final (vetor l[]) todos elementos que se repetirem

        while (i < list.length-1){
            elem = list[i]; //atribuo um elemeto do list
            int count = 0;

            for (int y = i; y < list.length; y++){
                if (list[y] == elem) { //verifico se o elemento se repetiu 
                    count+=1; //incrementa contador
                    if (count >= 2){ // se o contador for maior que 2, significa que o elemento está na lista de forma repetida, logo insiro novo elemento no vetor final (l[])
                        l[indice] = elem;
                        indice +=1;
                        i+=count;
                        y = list.length;
                        
                    }
                } else {
                     y = list.length;
                     i+=count;
                }
            } 
        }

        return l; // retorna vetor final

    }


    //imprimir vetor
    public void imprimeVetor(int list[]){
        System.out.println(Arrays.toString(list));
    }
}
