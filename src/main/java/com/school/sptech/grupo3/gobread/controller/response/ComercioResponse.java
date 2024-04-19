package com.school.sptech.grupo3.gobread.controller.response;

import com.school.sptech.grupo3.gobread.entity.Endereco;
import lombok.Builder;

import java.util.List;

@Builder
public record ComercioResponse(
        Integer id,
        String razaoSocial,
        String responsavel,
        String email,
        String tipo,
        String cnpj,
        String telefone,
        Endereco endereco,
        List<PedidoComercioResponse> pedidos
) {}
