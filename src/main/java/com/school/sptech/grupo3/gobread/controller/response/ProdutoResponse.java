package com.school.sptech.grupo3.gobread.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse{
    private Integer id;
    private String nome;
    private Integer valorPorcao;
    private String tipoPorcao;

}
