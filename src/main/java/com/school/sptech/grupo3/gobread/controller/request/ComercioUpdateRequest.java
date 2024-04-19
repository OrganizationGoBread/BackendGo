package com.school.sptech.grupo3.gobread.controller.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ComercioUpdateRequest {

    @Size(min = 3, max = 255)
    private String razaoSocial;
    @Size(min = 3, max = 255)
    private String responsavel;
    @CNPJ
    private String cnpj;
    @Size(min = 11, max = 11)
    private String telefone;
    @Email
    private String email;
    @NotBlank
    private String tipo;
    @NotNull
    private EnderecoRequest endereco;

}
