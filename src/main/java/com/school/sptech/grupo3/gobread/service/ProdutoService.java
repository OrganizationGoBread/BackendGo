package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.controller.response.ProdutoPedidoResponse;
import com.school.sptech.grupo3.gobread.controller.response.ProdutoResponse;
import com.school.sptech.grupo3.gobread.entity.Produto;
import com.school.sptech.grupo3.gobread.mapper.ProdutoMapper;
import com.school.sptech.grupo3.gobread.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<ProdutoResponse> buscarProdutos(){
        List<Produto> produtos = this.produtoRepository.findAll();
        List<ProdutoResponse> lista = ProdutoMapper.toListProdutoResponse(produtos);
        return lista;
    }

}
