package com.school.sptech.grupo3.gobread.controller.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EnderecoRequest(
        @NotBlank
        String cep,
        @Min(1)
        Integer numero,
        String complemento
) {}
