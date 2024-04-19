package com.school.sptech.grupo3.gobread.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemComercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "comercio_id")
    private Comercio comercio;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

}
