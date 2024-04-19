package com.school.sptech.grupo3.gobread.controller;


import com.school.sptech.grupo3.gobread.controller.request.EstoqueRequest;
import com.school.sptech.grupo3.gobread.controller.response.EstoqueResponse;
import com.school.sptech.grupo3.gobread.controller.response.ItemComercioPedidoResponse;
import com.school.sptech.grupo3.gobread.service.ItemComercioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-comercio")
@RequiredArgsConstructor
public class ItemComercioController {

    private final ItemComercioService itemComercioService;

    @PostMapping
    public ResponseEntity<EstoqueResponse> cadastrar(@RequestBody EstoqueRequest estoqueRequest){
        EstoqueResponse estoqueResponse = this.itemComercioService.cadastrar(estoqueRequest);
        return ResponseEntity.status(200).body(estoqueResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ItemComercioPedidoResponse>> buscarProdutosPorId(@PathVariable int id){
        List<ItemComercioPedidoResponse> estoque = this.itemComercioService.buscarProdutosPorId(id);
        if (estoque.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(estoque);
    }

}
