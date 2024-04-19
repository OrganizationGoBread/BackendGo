package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.response.ProdutoResponse;
import com.school.sptech.grupo3.gobread.entity.Produto;
import com.school.sptech.grupo3.gobread.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> buscarProdutos(){
        List<ProdutoResponse> produtoResponses = this.produtoService.buscarProdutos();
        if(produtoResponses.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(produtoResponses);
    }



}
