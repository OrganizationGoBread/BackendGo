package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.response.ItemPedidoClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.ItemPedidoResponse;
import com.school.sptech.grupo3.gobread.entity.ItemPedido;

import java.util.List;

public class ItemPedidoMapper {

    public static ItemPedidoResponse toItemPedidoResponse(ItemPedido itemPedido){
        ItemPedidoResponse itemPedidoResponse = new ItemPedidoResponse();
        itemPedidoResponse.setProduto(ProdutoMapper.toProdutoPedidoResponse(itemPedido.getProduto()));
        itemPedidoResponse.setId(itemPedido.getId());
        return itemPedidoResponse;
    }

    public static ItemPedidoClienteResponse toItemPedidoClienteResponse(ItemPedido itemPedido){
        ItemPedidoClienteResponse itemPedidoResponse = new ItemPedidoClienteResponse();
        itemPedidoResponse.setProduto(ProdutoMapper.toProdutoResponse(itemPedido.getProduto()));
        itemPedidoResponse.setId(itemPedido.getId());
        itemPedidoResponse.setQuantidade(itemPedido.getQuantidade());
        return itemPedidoResponse;
    }

    public static List<ItemPedidoClienteResponse> toListItemPedidoClienteResponse(List<ItemPedido> itensPedido){
        List<ItemPedidoClienteResponse> itemPedidoClienteResponses = itensPedido.stream().map(ItemPedidoMapper::toItemPedidoClienteResponse).toList();
        return itemPedidoClienteResponses;
    }

    public static List<ItemPedidoResponse> toListItemPedidoResponse(List<ItemPedido> pedidos){
        List<ItemPedidoResponse> itemPedidoResponses = pedidos.stream().map(ItemPedidoMapper::toItemPedidoResponse).toList();
        return itemPedidoResponses;
    }
}
