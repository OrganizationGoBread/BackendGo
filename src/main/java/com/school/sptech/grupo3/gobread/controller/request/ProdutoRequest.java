package com.school.sptech.grupo3.gobread.controller.request;

import lombok.Builder;

@Builder
public record ProdutoRequest(
    String nome,
    Integer valorPorcao
) {}
