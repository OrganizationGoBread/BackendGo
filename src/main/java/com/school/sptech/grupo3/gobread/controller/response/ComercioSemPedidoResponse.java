package com.school.sptech.grupo3.gobread.controller.response;

import com.school.sptech.grupo3.gobread.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ComercioSemPedidoResponse {

    private Integer id;
    private String razaoSocial;
    private String responsavel;
    private String email;
    private String tipo;
    private String telefone;
    private Endereco endereco;

}
