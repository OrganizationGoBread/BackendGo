package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.request.EnderecoRequest;
import com.school.sptech.grupo3.gobread.entity.Endereco;

public class EnderecoMapper {

    public static Endereco toEndereco(EnderecoRequest enderecoRequest){
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoRequest.cep());
        endereco.setComplemento(enderecoRequest.complemento());
        endereco.setNumero(enderecoRequest.numero());
        return endereco;
    }



}
