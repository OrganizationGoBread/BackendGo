package com.school.sptech.grupo3.gobread.mapper;


import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.request.ComercioUpdateRequest;
import com.school.sptech.grupo3.gobread.controller.response.ComercioClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.controller.response.ComercioSemPedidoResponse;
import com.school.sptech.grupo3.gobread.entity.Comercio;

import java.util.List;

public class ComercioMapper {

    public static ComercioClienteResponse toComercioClienteResponse(Comercio comercio){
        ComercioClienteResponse comercioClienteResponse = new ComercioClienteResponse();
        comercioClienteResponse.setId(comercio.getId());
        comercioClienteResponse.setRazaoSocial(comercio.getRazaoSocial());
        return comercioClienteResponse;
    }

    public static ComercioResponse toComercioResponse(Comercio comercio){
        return ComercioResponse.builder()
                .id(comercio.getId())
                .tipo(comercio.getTipo())
                .cnpj(comercio.getCnpj())
                .email(comercio.getEmail())
                .razaoSocial(comercio.getRazaoSocial())
                .responsavel(comercio.getResponsavel())
                .telefone(comercio.getTelefone())
                .endereco(comercio.getEndereco())
                .pedidos(PedidoMapper.toListPedidoComercioResponse(comercio.getPedidos()))
                .build();
    }

    public static ComercioResponse toComercioSemPedidoResponse(Comercio comercio){
        return ComercioResponse.builder()
                .id(comercio.getId())
                .tipo(comercio.getTipo())
                .cnpj(comercio.getCnpj())
                .email(comercio.getEmail())
                .razaoSocial(comercio.getRazaoSocial())
                .responsavel(comercio.getResponsavel())
                .telefone(comercio.getTelefone())
                .endereco(comercio.getEndereco())
                .build();
    }

    public static Comercio toComercio(ComercioRequest comercioRequest){
        Comercio comercio = new Comercio();
        comercio.setResponsavel(comercioRequest.responsavel());
        comercio.setEmail(comercioRequest.email());
        comercio.setCnpj(comercioRequest.cnpj());
        comercio.setSenha(comercioRequest.senha());
        comercio.setRazaoSocial(comercioRequest.razaoSocial());
        comercio.setTelefone(comercioRequest.telefone());
        comercio.setTipo(comercioRequest.tipo());
        comercio.setEndereco(EnderecoMapper.toEndereco(comercioRequest.endereco()));
        return comercio;
    }

    public static Comercio toComercio(ComercioUpdateRequest comercioRequest){
        Comercio comercio = new Comercio();
        comercio.setResponsavel(comercioRequest.getResponsavel());
        comercio.setEmail(comercioRequest.getEmail());
        comercio.setCnpj(comercioRequest.getCnpj());
        comercio.setRazaoSocial(comercioRequest.getRazaoSocial());
        comercio.setTelefone(comercioRequest.getTelefone());
        comercio.setTipo(comercioRequest.getTipo());
        comercio.setEndereco(EnderecoMapper.toEndereco(comercioRequest.getEndereco()));
        return comercio;
    }

    public static ComercioSemPedidoResponse toComercioSemPedidosResponse(Comercio comercio){
        ComercioSemPedidoResponse comercioResponse = new ComercioSemPedidoResponse();
        comercioResponse.setId(comercio.getId());
        comercioResponse.setResponsavel(comercio.getResponsavel());
        comercioResponse.setTipo(comercio.getTipo());
        comercioResponse.setEmail(comercio.getEmail());
        comercioResponse.setRazaoSocial(comercio.getRazaoSocial());
        comercioResponse.setTelefone(comercio.getTelefone());
        comercioResponse.setEndereco(comercio.getEndereco());
        return comercioResponse;
    }

    public static List<ComercioSemPedidoResponse> toListComercioSemPedidosResponse(List<Comercio> comercios){
        List<ComercioSemPedidoResponse> comerciosResponse = comercios.stream().map(ComercioMapper::toComercioSemPedidosResponse).toList();
        return comerciosResponse;
    }
}
