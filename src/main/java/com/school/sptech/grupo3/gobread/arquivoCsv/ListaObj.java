package com.school.sptech.grupo3.gobread.arquivoCsv;

import java.lang.reflect.Array;

public class ListaObj <T> {
    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho, Class<T> tipo) {
        this.vetor = (T[]) Array.newInstance(tipo, tamanho);
        this.nroElem = 0;
    }

    // 04) Método adiciona:
    // Recebe o elemento a ser adicionado na lista
    // Se a lista estiver cheia usar IllegalStateException();
    public void adiciona(T elemento) {
        if(nroElem != vetor.length){
            vetor[nroElem] = elemento;
            nroElem++;
        } else{
            throw new IllegalStateException("Lista cheia!");
        }
    }

    // 05) Método busca:
    // Recebe o elemento a ser procurado na lista
    // Retorna o índice do elemento, se for encontrado
    // Retorna -1 se não encontrou
    public int busca(T elementoBuscado) {
        for(int i = 0; i < nroElem; i++){
            if(vetor[i].equals(elementoBuscado)){
                return i;
            }
        }
        return -1;
    }

    // 06) Método removePeloIndice:
    // Recebe o índice do elemento a ser removido
    // Se o índice for inválido, retorna false
    // Se removeu, retorna true
    public boolean removePeloIndice(int indice) {
        if(indice < nroElem && indice >= 0){
            for(int i = indice; i < nroElem -1; i++){
                vetor[i] = vetor[i+1];
            }
            nroElem--;
            return true;
        }
        return false;
    }

    public void adicionaNoIndice(int indice, T cliente) {
        if (indice < 0 || indice > nroElem) {
            System.out.println("Índice inválido");
        } else if (nroElem >= vetor.length) {
            System.out.println("Lista cheia");
        } else {
            for (int i = nroElem - 1; i >= indice; i--) {
                vetor[i + 1] = vetor[i];
            }
            vetor[indice] = cliente;
            nroElem++;
        }
    }

    // 07) Método removeElemento
    // Recebe um elemento a ser removido
    // Utiliza os métodos busca e removePeloIndice
    // Retorna false, se não encontrou o elemento
    // Retorna true, se encontrou e removeu o elemento
    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    // 08) Método getTamanho
    // Retorna o tamanho da lista
    public int getTamanho() {
        return nroElem;
    }

    // 09) Método getElemento
    // Recebe um índice e retorna o elemento desse índice
    // Se o índice for inválido, retorna null
    public T getElemento(int indice) {
        if(indice < nroElem && indice >= 0){
            return vetor[indice];
        }
        return null;
    }

    // 10) Método limpa
    // Limpa a lista
    public void limpa() {
        nroElem = 0;
    }

    // 11) Método exibe:
    // Exibe os elementos da lista
    public void exibe() {
        for (int i = 0; i < nroElem; i++){
            System.out.println(vetor[i]);
        }
    }

    // Get do vetor
    // Não retirar, é usado nos testes
    public T[] getVetor() {
        return vetor;
    }
}