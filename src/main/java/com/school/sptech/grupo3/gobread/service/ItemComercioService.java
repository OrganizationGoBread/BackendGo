package com.school.sptech.grupo3.gobread.service;


import com.school.sptech.grupo3.gobread.controller.request.EstoqueRequest;
import com.school.sptech.grupo3.gobread.controller.response.*;
import com.school.sptech.grupo3.gobread.entity.ItemComercio;
import com.school.sptech.grupo3.gobread.mapper.ItemComercioMapper;
import com.school.sptech.grupo3.gobread.mapper.ProdutoMapper;
import com.school.sptech.grupo3.gobread.repository.ComercioRepository;
import com.school.sptech.grupo3.gobread.repository.ItemComercioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemComercioService {

    private final ItemComercioRepository itemComercioRepository;
    private final ComercioRepository comercioRepository;


    public EstoqueResponse cadastrar(EstoqueRequest estoqueRequest){
        if(comercioRepository.existsById(estoqueRequest.getItensComercio().get(0).idComercio())){
            List<ItemComercio> listaItens = ItemComercioMapper.toListItemComercio(estoqueRequest.getItensComercio());
            itemComercioRepository.saveAll(listaItens);
            List<ItemComercioResponse> listaResponse = ItemComercioMapper.toListItemComercioResponse(listaItens);
            EstoqueResponse estoqueResponse = new EstoqueResponse(listaResponse);
            return estoqueResponse;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comercio não encontrado");
    }

    public List<ItemComercioPedidoResponse> buscarProdutosPorId(int id) {
        List<ItemComercio> itemComercio = itemComercioRepository.findByComercioId(id);
        List<ItemComercioPedidoResponse> list = ItemComercioMapper.toListItemComercioPedidoResponse(itemComercio);
        return list;


    }
}
