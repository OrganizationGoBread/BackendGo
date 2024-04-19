package com.school.sptech.grupo3.gobread.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PedidoComercioResponse {
    private Integer id;
    private String diaEntrega;
    private String horarioEntrega;
    private List<ItemPedidoClienteResponse> itensPedido;
    private ClienteComercioResponse cliente;
}
