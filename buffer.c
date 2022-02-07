#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "buffer.h"

//Armazena o conteudo de um arquivo em um buffer


void* carrega_Buffer(Buffer* buffer)
{
    
    if(buffer->arquivo != NULL) //Se o arquivo n estiver vazio
    {
        
        if(fseek(buffer->arquivo, buffer->posicao, SEEK_SET) == 0)
        {
            free(buffer->conteudo);

            //Aloca memoria para a leitura do bloco
            buffer->conteudo = (char*)malloc(buffer->tamanho * (sizeof(char)) + (sizeof(char)));

            //Memória alocada corretamente?
            if(buffer->conteudo != NULL)
            {
                
                //Copia todo o conteudo do bloco de tamanho N do arquivo para o buffer
                size_t novo_tam =fread(buffer->conteudo, sizeof(char), buffer->tamanho, buffer->arquivo);
                
                //Verifica se tem erro ao ler o bloco ao transferir para o buffer
                if(ferror(buffer->arquivo) == 0)
                {
                    
                    //Verificando se o ultimo char eh um '\n'
                    if((strcmp(&buffer->conteudo[novo_tam-1], "\n") != 0))
                    {
                        //Buffer aponta para NULL
                        buffer->conteudo = NULL;

                        //Vai diminuindo o tamanho do Buffer de 1 em 1 até achar um '\n'
                        buffer->tamanho--;
                        carrega_Buffer(buffer);
                    }
                    
                    else
                    {
                        //Se tiver um '\0' no final
                        buffer->conteudo[novo_tam++] = '\0';
                        buffer->posicao += buffer->tamanho;
                        buffer->tamanho = buffer->tamanho_original;
                    }
                }
                else
                {
                    printBuffer(buffer);
                    buffer->tamanho = buffer->tamanho_original;
                    printf("Erro ao Carregar buffer \n");
                }
            }
            else
            {
                printf("Erro ao Carregar buffer, o conteúdo do arquivo é nulo !\n");
                printBuffer(buffer);
                printf("AQUI %s\n", buffer->conteudo);
            }
        }
        else
        {
            printf("Erro ao Carregar buffer \n");
        }
    }
    else
    {
        printf("Erro ao Carregar buffer \n");
    }

    return 0;
}

//Define quando buffer chegou ao fim do arquivo
unsigned long int tam_Arquivo(char *nome_arquivo)
{
    FILE *arquivo;
    arquivo = fopen(nome_arquivo, "r"); //Abre o arquivo
    unsigned long int numbytes = 0;
    if(fseek(arquivo, 0, SEEK_END) == 0)
    {
        numbytes = ftell(arquivo);
        //Se numbytes >= 0. numbytes obtidos com sucesso
        if(numbytes != -1)
        {
            //se houver um erro ao voltar para o incio do arquivo
            if(!fseek(arquivo, 0, SEEK_SET) == 0)
            {
                numbytes = 0;
            }
        }
    }
    fclose(arquivo);
    return numbytes;
}

Buffer* criaBuffer(char *nome_arquivo, unsigned long int tamanho_buffer)
{
    //Alocando memoria pro buffer
    Buffer* buffer = (Buffer*) malloc(sizeof(Buffer));

    //Salvando o nome do arquivo
    strcpy(buffer->nome_arquivo, nome_arquivo);
    
    //Calculando o tamanho do arquivo
    buffer->fim_arquivo = tam_Arquivo(buffer->nome_arquivo);

    //Abrindo o arquivo
    buffer->arquivo = fopen(buffer->nome_arquivo, "r");
    if(buffer->arquivo == NULL)
    {
        printf("Falha ao abrir arquivo: %s\n", buffer->nome_arquivo);
        return NULL;
    }
    
    //Posicao inicial do bloco do buffer
    buffer->posicao = 0;

    //Tamanho atual do buffer
    buffer->tamanho = tamanho_buffer;

    //Tamanho original do buffer, utilizado caso o tamanho atual seja modificado
    buffer->tamanho_original = tamanho_buffer;

    buffer->conteudo = NULL;

    //Para matriz
    buffer->pos_atual_matriz = 0;
    buffer->pos_max_matriz = 0;
    return buffer;
}

void freeBuffer(Buffer* buffer)
{
    free(buffer->conteudo);
    fclose(buffer->arquivo);
    free(buffer);
}

void freeBufferLista(Buffer* buffer)
{
    free(buffer->conteudo);
    fclose(buffer->arquivo);
}

//////////
void merging_buffers_to_file(char nome_arquivo_destino[], Buffer *buffer1, Buffer *buffer2)
{
    /*
    Abrindo o arquivo de destino no modo "append", ou seja, os dados nao serao sobrescritos
    e sim adicionados ao final
    */
    FILE *file_dest = NULL;
    file_dest = fopen(nome_arquivo_destino, "a");

    //ponteiro char para verificar se todo o buffer ja foi percorrido
    char *tem_item_b1 = NULL;
    char *tem_item_b2 = NULL;

    //Ponteiros para salvar a instancia de cada buffer no strtok_r
    char *saveptr_1 = NULL;
    char *saveptr_2 = NULL;
   
    /*
    Capturando o primeiro item de cada buffer.
    buffer a ser lido / separador / ponteiro para salvar onde parou a verificacao
    */
    tem_item_b1 = strtok_r(buffer1->conteudo, "\n", &saveptr_1);
    tem_item_b2 = strtok_r(buffer2->conteudo, "\n", &saveptr_2);


    //Enquanto houver itens em ambos os buffers
    while(tem_item_b1 && tem_item_b2)
    {
        if(atoi(tem_item_b1) == atoi(tem_item_b2)) 
        {
            //Escreve no arquivo
            fprintf(file_dest, "%s\n", tem_item_b1);
            //Pego o proximo item dos buffers
            tem_item_b1 = strtok_r(NULL ,"\n", &saveptr_1);
            tem_item_b2 = strtok_r(NULL ,"\n", &saveptr_2);
        }
        else if(atoi(tem_item_b1) < atoi(tem_item_b2))
        {
            fprintf(file_dest, "%s\n", tem_item_b1);
            tem_item_b1 = strtok_r(NULL ,"\n", &saveptr_1);
        }
        else
        {
            fprintf(file_dest, "%s\n", tem_item_b2);
            tem_item_b2 = strtok_r(NULL ,"\n", &saveptr_2);
        }
    }

    /*
    Se ainda houver elementos nao percorridos nos buffer,
    escreve no novo arquivo os elementos restantes.
    */
    while(tem_item_b1)
    {
        fprintf(file_dest, "%s\n", tem_item_b1);
        tem_item_b1 = strtok_r(NULL ,"\n", &saveptr_1);
    }
    while(tem_item_b2)
    {
        fprintf(file_dest, "%s\n", tem_item_b2);
        tem_item_b2 = strtok_r(NULL ,"\n", &saveptr_2);
    }
    //Fechando arquiov de destino
    fclose(file_dest);
}

//Escreve item por item do buffer no arquivo de destino
void escreve_item(char *nome_arquivo_destino, Buffer* buffer)
{
    FILE *file_dest = NULL;
    file_dest = fopen(nome_arquivo_destino, "a");

    //ponteiro char para verificar se todo o buffer ja foi percorrido
    char *tem_item = NULL;

    //Ponteiros para salvar a instancia de cada buffer no strtok_r
    char *saveptr= NULL;

    tem_item = strtok_r(buffer->conteudo, "\n", &saveptr);
    while(tem_item)
    {
        fprintf(file_dest, "%s\n", tem_item);
        tem_item = strtok_r(NULL ,"\n", &saveptr);
    }

    fclose(file_dest);

}

void merge_arquivos(char *nome_arquivo_final, Buffer* buffer1, Buffer* buffer2)
{
    while(buffer1->posicao < buffer1->fim_arquivo && buffer2->posicao < buffer2->fim_arquivo) //Faz o while de verificação
    {
        carrega_Buffer(buffer1); 
        carrega_Buffer(buffer2);
        merging_buffers_to_file(nome_arquivo_final, buffer1, buffer2); //Chama a função de merging
    }

    //Se o arquivo 1 ainda nao chegou ao fim
    while(buffer1->posicao < buffer1->fim_arquivo)
    {
        carrega_Buffer(buffer1);
        escreve_item(nome_arquivo_final, buffer1); //Escrevendo
    }

    //Se o arquivo 2 ainda nao chegou ao fim
    while(buffer2->posicao < buffer2->fim_arquivo)
    {
        carrega_Buffer(buffer2);
        escreve_item(nome_arquivo_final, buffer2); //Escrevendo
    }
}

/*
Funcao apenas para fins de debugger.
Mostra o conteudo de um buffer.
*/
void printBuffer(Buffer* buffer)
{
    printf("------\n");
    printf("ARQUIVO: %s\n", buffer->nome_arquivo);
    printf("POSICAO: %ld\n", buffer->posicao);
    printf("FIM_ARQUIVO: %ld\n", buffer->fim_arquivo);
    printf("TAMANHO: %ld\n", buffer->tamanho);
    printf("TAMANHO_ORIGINAL: %ld\n", buffer->tamanho_original);
    printf("CONTEUDO:%s\n", buffer->conteudo);
    printf("POSICAO ATUAL MATRIZ: %ld\n", buffer->pos_atual_matriz);
    printf("POSICAO MAX MATRIZ: %ld\n", buffer->pos_max_matriz);
    printf("\n------\n");
}

//Função que limpar o Buffer
void limpaBuffer(Buffer* buffer)
{
    fclose(buffer->arquivo);
    buffer->arquivo = NULL;
    free(buffer->conteudo);
    buffer->posicao = 0;
    buffer->fim_arquivo = 0;
    buffer->tamanho = 0;
    buffer->tamanho_original = 0;
}
///////////

void captura_palavra(char destino[], char frase[], char separador[], unsigned long int posicao)
{
    char *copy_conteudo = strdup(frase);
    char *item = NULL, *saveptr = NULL;
    unsigned long int i = 0;

    item = strtok_r(copy_conteudo, separador, &saveptr);
    //Captura a palavra da respectiva posicao
    while(i < posicao)
    {
        item = strtok_r(NULL, separador, &saveptr);
        i++;
    }

    strcpy(destino, item);
    free(copy_conteudo);
}

unsigned long int calcula_tam_buffer_to_matriz(Buffer* buffer, char separador[])
{
    unsigned long int tam = 0;
    char *token = NULL;
    char *copy_frase = strdup(buffer->conteudo);

    token = strtok(copy_frase, separador);
    while(token != NULL)
    {
        tam++;
        token = strtok(NULL, separador);
    }

    free(copy_frase);
    return tam;
    
}

void buffer_to_matriz(Buffer* buffer, char **matriz, unsigned long int tam_matriz)
{
    for(unsigned long int i = 0; i < tam_matriz; i++)
    {
        captura_palavra(matriz[i], buffer->conteudo, "\n", i);
    }
}

//Imprime a Matriz
void print_matriz(char **matriz, unsigned long int tam_matriz)
{
    printf("-----\n");
    for(unsigned long int i = 0; i < tam_matriz; i++) printf("%s\n", matriz[i]);
    printf("-----\n");
}

//Salva uma matriz de strings num arquivo
//Recebe o nome do arquivos de destino, a matriz de strings e o tamanha da mesma
void matriz_to_file(char nome_arquivo[], char **matriz, unsigned long int tam_matriz)
{
    FILE *file_dest = NULL;
    file_dest = fopen(nome_arquivo, "w");
    if(file_dest)
    {
        for(unsigned long int i = 0; i < tam_matriz; i++)
        {
            fprintf(file_dest, "%s\n", matriz[i]);
        }
    }
    fclose(file_dest);
}