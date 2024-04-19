package com.school.sptech.grupo3.gobread.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer valorPorcao;
    private String tipoPorcao;

    public Produto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValorPorcao() {
        return valorPorcao;
    }

    public void setValorPorcao(Integer valorPorcao) {
        this.valorPorcao = valorPorcao;
    }

    public String getTipoPorcao() {
        return tipoPorcao;
    }

    public void setTipoPorcao(String tipoPorcao) {
        this.tipoPorcao = tipoPorcao;
    }
}
