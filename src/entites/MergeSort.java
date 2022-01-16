package entites;
import java.util.Comparator;
//import java.text.Collator;
//import java.util.Collections;
//import java.util.*;

public class MergeSort implements Comparator<ListaTel> {
    

    @Override
    public int compare(ListaTel lista1, ListaTel lista2) {
        if(lista1.getNome().compareTo(lista2.getNome())>0){
            return 1;
        }
        return -1;
    }



   


    /*int inicio; // Inicio da lista
    int fim;  // Fim da lista

    private static void mergeSort(List<ListaTel> list, int inicio, int fim ) { // Recebe as duas agendas telefonicas
        
        if(inicio<fim){
            int meio = (inicio + fim)/2;
            mergeSort(list, inicio, meio);
            mergeSort(list, meio+1, fim);
            intercalar(list,inicio,meio,fim);
        }

        }

    private static void intercalar(List<ListaTel> list, List<ListaTel> list2, int inicio, int meio, int fim) {
        for(int k = inicio; k<fim; k++){
            
        }
    }*/
        
        
    
       
    }
