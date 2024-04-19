package com.school.sptech.grupo3.gobread.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginComercioResponse {
    private String token;
    private ComercioResponse comercio;

}
