import java.util.ArrayList;
import java.util.Arrays;

public class main {

    public static void main(String[] args) {
		// TODO Auto-generated method stub
      

        int vet[] = {1,3,5,6,7,9,13,15,19,22};

        int vetor[] = {0,2,5,6,8,11,14,15,18,20};

        int v[] = {0,1,4,5,6,10,11,13,15,24};

        int list[]; //lista final com resultado do merging
        int list_md[]; //lista final com resultado do merging duplicado
        int list_mat[]; //lista final com resultado do matching
        list = new int[vet.length + vetor.length];
        list_md = new int[vet.length + vetor.length];
        list_mat = new int[vet.length + vetor.length];


        Merging merge = new Merging();
        merge.merging(vet, vetor, list);  //execução do merging

        int list_aux[]; //lista final com resultado do merging, porém excluindo os zeros que podem ficar no final da lista
        list_aux = new int[list.length - merge.verificaZeros(list)];
        for (int i = 0; i < list_aux.length; i++){
          list_aux[i] = list[i];
        }

        System.out.println("\nMerging:");   
        merge.imprimeVetor(list_aux);

        MergingDuplicado mergeDuplicado = new MergingDuplicado(); //execução do merging duplicado
        mergeDuplicado.mergingDuplicado(vet, vetor, list_md);
        System.out.println("\nMerging duplicado:");
        mergeDuplicado.imprimeVetor(list_md);

        Matching matching = new Matching();
        matching.matching(vet, vetor, list_mat); //execução do matching

        int list_aux_m[]; //lista final com resultado do matching, porém excluindo os zeros que podem ficar no final da lista
        list_aux_m = new int[list_mat.length - merge.verificaZeros(list_mat)];
        for (int i = 0; i < list_aux_m.length; i++){
          list_aux_m[i] = list_mat[i];
        }

        System.out.println("\nMatching:");
        matching.imprimeVetor(list_aux_m);

        // 3 vetores utilizados para guardar o resultados dos testes baseados em 3 vetores como entrada, porém dos testes mais básicos (com 10 elementos)
        int l1[]; //lista final com resultado do merging
        int l2[]; //lista final com resultado do merging duplicado
        int l3[]; //lista final com resultado do matching
        l1 = new int[vet.length + vetor.length + v.length];
        l2 = new int[vet.length + vetor.length + v.length];
        l3 = new int[vet.length + vetor.length + v.length];

        merge.mergingThree(vet, vetor, v, l1);
        int l1_aux[]; //lista final com resultado do merging com 3 vetores de entrada, porém excluindo os zeros que podem ficar no final da lista
        l1_aux = new int[l1.length - merge.verificaZeros(l1)];  //remove os número 0 que podem existir no final do vetor final, ou seja, é criado um vetor com tamanho ideal para a saída
        for (int i = 0; i < l1_aux.length; i++){
          l1_aux[i] = l1[i];
        }

        System.out.println("\nMerging 3 vetores como entrada:");
        merge.imprimeVetor(l1_aux);

        mergeDuplicado.mergingDuplicadoThree(vet, vetor, v, l2);
        System.out.println("\nMerging duplicado com 3 vetores de entrada:");
        mergeDuplicado.imprimeVetor(l2);

        l3 = matching.matchingThree(vet, vetor, v, l3);
        int l3_aux[]; //lista final com resultado do matching com 3 vetores de entrada, porém excluindo os zeros que podem ficar no final da lista
        l3_aux = new int[l3.length - merge.verificaZeros(l3)]; //remove os número 0 que podem existir no final do vetor final, ou seja, é criado um vetor com tamanho ideal para a saída
        for (int i = 0; i < l3_aux.length; i++){
          l3_aux[i] = l3[i];
        }

        System.out.println("\nMatching com 3 vetores de entrada:");
        matching.imprimeVetor(l3_aux);



        int quantidade = 10000; //declaro tamanho dos vetores de entrada
        int[] v_teste = new int[quantidade];

        /*inicializo os 3 vetores declarados abaixo, que serão utilizados nos testes de performance*/
        for (int i = 0; i < v_teste.length; i++) {
          v_teste[i] = (int) (Math.random()*quantidade);
        }

        int[] v1_teste = new int[quantidade];

        for (int i = 0; i < v1_teste.length; i++) {
          v1_teste[i] = (int) (Math.random()*quantidade);
        }

        int[] v2_teste = new int[quantidade];

        for (int i = 0; i < v2_teste.length; i++) {
          v2_teste[i] = (int) (Math.random()*quantidade);
        }

        long tempoInicial = System.currentTimeMillis();

        InsertionSort insertion = new InsertionSort();

        // Execução do insertion sort para ordenar os 3 vetores
        insertion.insertionSort(v_teste);
        insertion.insertionSort(v1_teste);
        insertion.insertionSort(v2_teste);

        int[] l4 = new int[quantidade*3];
        merge.mergingThree(v_teste, v1_teste, v2_teste, l4);

        int l4_aux[]; //lista final com resultado do merging com 3 vetores de entrada, porém excluindo os zeros que podem ficar no final da lista
        l4_aux = new int[l4.length - merge.verificaZeros(l4)];
        for (int i = 0; i < l4_aux.length; i++){
          l4_aux[i] = l4[i];
        }
        System.out.println("\nMerging com 3 vetores de entrada - 10000 elementos:");
        //merge.imprimeVetor(l4_aux);

        int[] l5 = new int[quantidade*3];
        mergeDuplicado.mergingDuplicadoThree(v_teste, v1_teste, v2_teste, l5);
        System.out.println("\nMerging duplicado com 3 vetores de entrada - 10000 elementos:");
        //mergeDuplicado.imprimeVetor(l5);

        int[] l6 = new int[quantidade*3];
        matching.matchingThree(v_teste, v1_teste, v2_teste, l6);
        int l6_aux[]; //lista final com resultado do matching com 3 vetores de entrada, porém excluindo os zeros que podem ficar no final da lista
        l6_aux = new int[l6.length - merge.verificaZeros(l6)];
        for (int i = 0; i < l6_aux.length; i++){
          l6_aux[i] = l6[i];
        }

        System.out.println("\nMatching com 3 vetores de entrada - 10000 elementos:");
        //matching.imprimeVetor(l6_aux);

        long tempoFinal = System.currentTimeMillis();

        System.out.println("\nExecutado em = " + (tempoFinal - tempoInicial) + " ms");

        //imprimeVetor(v_teste);


    }


    public static void imprimeVetor(int list[]){
      System.out.println(Arrays.toString(list));
  }
    
}
