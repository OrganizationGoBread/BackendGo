package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.response.ProdutoPedidoResponse;
import com.school.sptech.grupo3.gobread.controller.response.ProdutoResponse;
import com.school.sptech.grupo3.gobread.entity.Produto;

import java.util.List;

public class ProdutoMapper {
    public static ProdutoPedidoResponse toProdutoPedidoResponse(Produto produto){
        ProdutoPedidoResponse produtoResponse = new ProdutoPedidoResponse();
        produtoResponse.setId(produto.getId());
        return produtoResponse;
    }

    public static ProdutoResponse toProdutoResponse(Produto produto){
        ProdutoResponse prodResponse = new ProdutoResponse();
        prodResponse.setId(produto.getId());
        prodResponse.setNome(produto.getNome());
        prodResponse.setValorPorcao(produto.getValorPorcao());
        prodResponse.setTipoPorcao(produto.getTipoPorcao());
        return prodResponse;
    }

    public static List<ProdutoResponse> toListProdutoResponse(List<Produto> produtos){
        List<ProdutoResponse> produtosResponse = produtos.stream().map(ProdutoMapper::toProdutoResponse).toList();
        return produtosResponse;
    }


}
