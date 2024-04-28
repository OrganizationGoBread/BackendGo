package com.school.sptech.grupo3.gobread.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String diaEntrega;
    private String horarioEntrega;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido;
    @ManyToOne
    @JoinColumn(name = "fkCliente", referencedColumnName = "id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "fkComercio", referencedColumnName = "id")
    private Comercio comercio;
    private String status = "confirmado";
    private Integer codigoVerificacao = 0000;

}
