#ifndef ARQUIVO_H
#define ARQUIVO_H
//"Chamada das funções"

int arquivo_existe(char *nome_arquivo);

//Cria e/ou limpa o arquivo
void limpa_arquivo(char *nome_arquivo);
void deletaArquivo(char *nome_arquivo);
unsigned long int size_file_in_lines(char *file_name);
#endif