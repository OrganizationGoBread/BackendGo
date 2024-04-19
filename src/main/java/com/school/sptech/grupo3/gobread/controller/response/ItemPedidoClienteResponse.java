package com.school.sptech.grupo3.gobread.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoClienteResponse {

    private Integer id;
    private Integer quantidade;
    private ProdutoResponse produto;

}
