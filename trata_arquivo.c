//Includes
#include <stdio.h>
#include <stdlib.h>

//Cria e/ou limpa o arquivo
void limpa_arquivo(char *nome_arquivo){
    FILE *file;
    file = fopen(nome_arquivo, "w");
    fclose(file);
}

//Função que verifica se o meu arquivo existe

int arquivo_existe(char *nome_arquivo)
{
    FILE *arquivo;
    arquivo = fopen(nome_arquivo, "r");
    if(arquivo)
    {
        fclose(arquivo);
        return 1; //Retorna 1 se existe
    }
    else return 0; //Retorna 0 se n existe
}

//Deleta o arquivo
void deletaArquivo(char *nome_arquivo)
{
    if(arquivo_existe(nome_arquivo)) remove(nome_arquivo); //Verifica se o arquivo existe e remove
}

unsigned long int size_file_in_lines(char *file_name)
{
    if(!arquivo_existe(file_name)) return 0;

    FILE *file = fopen(file_name, "r");

    char *line = NULL;
    size_t len_line = 0;
    unsigned long int qtd_lines = 0;
    while(getline(&line, &len_line, file) != -1) 
    {
        qtd_lines+=1;
    }
    fclose(file);
    if(line) free(line);
    return qtd_lines;
}