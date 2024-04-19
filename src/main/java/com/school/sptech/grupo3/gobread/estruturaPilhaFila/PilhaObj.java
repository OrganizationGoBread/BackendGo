package com.school.sptech.grupo3.gobread.estruturaPilhaFila;

public class PilhaObj<T> {

    // 01) Atributos
    private T[] pilha;
    private int topo;

    // 02) Construtor
    public PilhaObj(int capacidade) {
        this.pilha = (T[]) new Object[capacidade];
        this.topo = -1;

    }

    // 03) MÃ©todo isEmpty
    public Boolean isEmpty() {
        if(topo == -1){
            return true;
        }
        return false;
    }

    // 04) MÃ©todo isFull
    public Boolean isFull() {
        if(topo == pilha.length-1){
            return true;
        }
        return false;
    }

    // 05) MÃ©todo push
    public void push(T info) {
        if(isFull()){
            throw new IllegalStateException("pilha cheia");
        }
        pilha[topo+1] = info;
        topo++;
    }

    // 06) MÃ©todo pop
    public T pop() {
        return pilha[topo--];
    }

    // 07) MÃ©todo peek
    public T peek() {
        return pilha[topo];
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        for(int i = 0; i < topo; i++){
            System.out.println(pilha[i]);
        }
    }


    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}
