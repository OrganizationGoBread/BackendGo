package com.school.sptech.grupo3.gobread.controller.request;

import com.school.sptech.grupo3.gobread.entity.ItemPedido;
import lombok.Builder;

import java.util.List;

@Builder
public record PedidoRequest(
        String diaEntrega,
        String horarioEntrega,
        List<ItemPedido> itensPedido,
        Integer idCliente,
        Integer idComercio
) {}
