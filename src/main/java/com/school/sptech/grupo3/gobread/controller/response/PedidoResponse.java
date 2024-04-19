package com.school.sptech.grupo3.gobread.controller.response;

import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.ItemPedido;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoResponse {
    private Integer id;
    private String diaEntrega;
    private String horarioEntrega;
    private List<ItemPedidoResponse> itensPedido;
}
