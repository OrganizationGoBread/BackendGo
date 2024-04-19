package com.school.sptech.grupo3.gobread.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CNPJ;

@Builder
public record ComercioRequest(
    @Size(min = 3, max = 255)
    String razaoSocial,
    @Size(min = 3, max = 255)
    String responsavel,
    @CNPJ
    String cnpj,
    @Size(min = 11, max = 11)
    String telefone,
    @Email
    String email,
    @Size(min = 8, max = 16)
    String senha,
    @NotBlank
    String tipo,
    EnderecoRequest endereco
) {}
