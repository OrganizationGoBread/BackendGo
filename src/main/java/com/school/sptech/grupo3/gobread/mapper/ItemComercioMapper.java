package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.request.ItemComercioRequest;
import com.school.sptech.grupo3.gobread.controller.response.ItemComercioPedidoResponse;
import com.school.sptech.grupo3.gobread.controller.response.ItemComercioResponse;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.ItemComercio;
import com.school.sptech.grupo3.gobread.entity.Produto;
import com.school.sptech.grupo3.gobread.repository.ItemComercioRepository;

import java.util.ArrayList;
import java.util.List;

public class ItemComercioMapper {

    public static ItemComercio toItemComercio(ItemComercioRequest itemComercioRequest){
        ItemComercio itemComercio = new ItemComercio();
        itemComercio.setComercio(new Comercio(itemComercioRequest.idComercio()));
        itemComercio.setProduto(new Produto(itemComercioRequest.idProduto()));
        return itemComercio;
    }

    public static List<ItemComercio> toListItemComercio(List<ItemComercioRequest> listaItemComercio){
        List<ItemComercio> listaItens = new ArrayList<>();
        for (int i = 0; i < listaItemComercio.size(); i++){
            ItemComercio itemComercio = toItemComercio(listaItemComercio.get(i));
            listaItens.add(itemComercio);
        }
        return listaItens;
    }

    public static ItemComercioResponse toItemComercioResponse(ItemComercio itemComercio){
        ItemComercioResponse itemComercioResponse = new ItemComercioResponse();
        itemComercioResponse.setIdComercio(itemComercio.getComercio().getId());
        itemComercioResponse.setIdProduto(itemComercio.getProduto().getId());
        return itemComercioResponse;
    }

    public static List<ItemComercioResponse> toListItemComercioResponse(List<ItemComercio> listaItens){
        List<ItemComercioResponse> listaItensResponse = new ArrayList<>();
        for(int i = 0; i < listaItens.size(); i++){
            ItemComercioResponse itemComercioResponse = toItemComercioResponse(listaItens.get(i));
            listaItensResponse.add(itemComercioResponse);
        }
        return listaItensResponse;
    }

    public static ItemComercioPedidoResponse toItemComercioPedidoResponse (ItemComercio itemComercio) {
        ItemComercioPedidoResponse itemComercioPedidoResponse = new ItemComercioPedidoResponse();
        itemComercioPedidoResponse.setProduto(ProdutoMapper.toProdutoResponse(itemComercio.getProduto()));
        return itemComercioPedidoResponse;
    }

    public static List<ItemComercioPedidoResponse> toListItemComercioPedidoResponse (List<ItemComercio> itensComercio) {
        List<ItemComercioPedidoResponse> itemComercioPedidoResponse = itensComercio.stream().map(ItemComercioMapper::toItemComercioPedidoResponse).toList();
        return  itemComercioPedidoResponse;
    }
}
