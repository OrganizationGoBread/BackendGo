package com.school.sptech.grupo3.gobread.integrations.apiviacep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCep", url = "https://viacep.com.br/ws")
public interface ViaCepApi {
    @GetMapping("/{cep}/json/")
    AddressViaCep getAddressByCep(@PathVariable(value = "cep") String cep);
}
