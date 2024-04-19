package com.school.sptech.grupo3.gobread.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteUpdateRequest {

    @Size(min = 3, max = 255)
    private String nome;
    @CPF
    private String cpf;
    @Size(min = 11, max = 11)
    private String telefone;
    @Email
    private String email;
    @NotBlank
    private String tipo;
    @NotNull
    private EnderecoRequest endereco;
}
