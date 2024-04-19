package com.school.sptech.grupo3.gobread.controller.response;

import com.school.sptech.grupo3.gobread.entity.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Normalized;

@Getter
@Setter
@NoArgsConstructor
public class ClienteComercioResponse {

    private String nome;
    private Integer id;
    private String email;
    private String telefone;
    private String cpf;
    private Endereco endereco;

}
