package com.school.sptech.grupo3.gobread.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoClienteResponse {
    private Integer id;
    private String diaEntrega;
    private String horarioEntrega;
    private String status;
    private Integer codigoVerificacao;
    private List<ItemPedidoClienteResponse> itensPedido;
    private ComercioClienteResponse comercio;
}
