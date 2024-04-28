package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.controller.response.*;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.ItemPedido;
import com.school.sptech.grupo3.gobread.entity.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoMapper {

    public static List<PedidoResponse> toPedidoResponse(List<Pedido> pedidos){
        List<PedidoResponse> pedidoResponse = pedidos.stream().map(PedidoMapper::toPedidoResponse).toList();
        return pedidoResponse;
    }

    public static PedidoResponse toPedidoResponse(Pedido pedido){
        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setId(pedido.getId());
        pedidoResponse.setHorarioEntrega(pedido.getHorarioEntrega());
        pedidoResponse.setItensPedido(ItemPedidoMapper.toListItemPedidoResponse(pedido.getItensPedido()));
        pedidoResponse.setDiaEntrega(pedido.getDiaEntrega());
        return pedidoResponse;
    }

    public static PedidoComercioResponse toPedidoComercioResponse(Pedido pedido){
        PedidoComercioResponse pedidoComercioResponse = new PedidoComercioResponse();
        pedidoComercioResponse.setId(pedido.getId());
        pedidoComercioResponse.setHorarioEntrega(pedido.getHorarioEntrega());
        pedidoComercioResponse.setItensPedido(ItemPedidoMapper.toListItemPedidoClienteResponse(pedido.getItensPedido()));
        pedidoComercioResponse.setDiaEntrega(pedido.getDiaEntrega());
        pedidoComercioResponse.setCliente(ClienteMapper.toClienteComercioResponse(pedido.getCliente()));
        return pedidoComercioResponse;
    }

    public static List<PedidoComercioResponse> toListPedidoComercioResponse(List<Pedido> pedidos){
        return pedidos.stream().map(PedidoMapper::toPedidoComercioResponse).toList();
    }


    public static List<PedidoClienteResponse> toListPedidoClienteResponse(List<Pedido> pedidos){
        List<PedidoClienteResponse> pedidoResponses = pedidos.stream().map(PedidoMapper::toPedidoClienteResponse).toList();
        return pedidoResponses;
    }

    public static PedidoClienteResponse toPedidoClienteResponse(Pedido pedido){
        PedidoClienteResponse pedidoResponse = new PedidoClienteResponse();
        pedidoResponse.setId(pedido.getId());
        pedidoResponse.setStatus(pedido.getStatus());
        pedidoResponse.setCodigoVerificacao(pedido.getCodigoVerificacao());
        pedidoResponse.setHorarioEntrega(pedido.getHorarioEntrega());
        pedidoResponse.setItensPedido(ItemPedidoMapper.toListItemPedidoClienteResponse(pedido.getItensPedido()));
        pedidoResponse.setDiaEntrega(pedido.getDiaEntrega());
        pedidoResponse.setComercio(ComercioMapper.toComercioClienteResponse(pedido.getComercio()));
        return pedidoResponse;
    }

    public static Pedido toPedido(PedidoRequest pedidoRequest){
        Cliente cliente = new Cliente();
        cliente.setId(pedidoRequest.idCliente());
        Comercio comercio = new Comercio();
        comercio.setId(pedidoRequest.idComercio());
        Pedido pedido = new Pedido();
        pedido.setDiaEntrega(pedidoRequest.diaEntrega());
        pedido.setHorarioEntrega(pedidoRequest.horarioEntrega());
        for (int j = 0; j < pedidoRequest.itensPedido().size(); j++){
            pedidoRequest.itensPedido().get(j).setPedido(pedido);
        }
        pedido.setItensPedido(pedidoRequest.itensPedido());
        pedido.setCliente(cliente);
        pedido.setComercio(comercio);
        return pedido;
    }
}
