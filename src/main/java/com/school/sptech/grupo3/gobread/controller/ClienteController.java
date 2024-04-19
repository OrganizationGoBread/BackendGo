package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.request.ClienteUpdateRequest;
import com.school.sptech.grupo3.gobread.controller.request.LoginRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.LoginClienteResponse;
import com.school.sptech.grupo3.gobread.exceptions.UsuarioNaoEncontradoException;
import com.school.sptech.grupo3.gobread.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;


    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponse> cadastrarCliente(@Valid @RequestBody ClienteRequest clienteRequest) throws ResponseStatusException {
         ClienteResponse clienteResponse =  clienteService.criarCliente(clienteRequest);
         return ResponseEntity.status(201).body(clienteResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarClientePorId(@PathVariable int id) throws UsuarioNaoEncontradoException {
        ClienteResponse clienteResponse = clienteService.buscarClientePorId(id);
        return ResponseEntity.status(200).body(clienteResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginClienteResponse> loginCliente(@RequestBody LoginRequest loginRequest) throws UsuarioNaoEncontradoException {
        LoginClienteResponse response = clienteService.autenticar(loginRequest);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable int id,@Valid @RequestBody ClienteUpdateRequest clienteUpdateRequest) throws UsuarioNaoEncontradoException {
        ClienteResponse clienteResponse = clienteService.atualizarCliente(id, clienteUpdateRequest);
        return ResponseEntity.status(200).body(clienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable int id) throws UsuarioNaoEncontradoException {
        boolean isDeleted =  clienteService.deletarCliente(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/assinatura/{id}")
    public ResponseEntity<ClienteResponse> atualizarAssinatura(@RequestParam String assinatura, @PathVariable int id) throws UsuarioNaoEncontradoException {
        ClienteResponse clienteResponse = clienteService.atualizarAssinatura(assinatura, id);
        return ResponseEntity.status(200).body(clienteResponse);
    }


}
