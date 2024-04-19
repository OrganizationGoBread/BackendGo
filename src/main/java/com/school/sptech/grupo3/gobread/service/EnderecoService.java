package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.integrations.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.integrations.apiviacep.ViaCepApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final ViaCepApi viaCepApi;

    public AddressViaCep buscarEnderecoViaCep(String cep) {
        return viaCepApi.getAddressByCep(cep);
    }
}
