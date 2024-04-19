package com.school.sptech.grupo3.gobread.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest(

    @Size(min = 3, max = 255)
    String nome,
    @CPF
    String cpf,
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
