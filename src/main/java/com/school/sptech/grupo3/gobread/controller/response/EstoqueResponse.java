package com.school.sptech.grupo3.gobread.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueResponse {
    private List<ItemComercioResponse> itensComercio;
}
