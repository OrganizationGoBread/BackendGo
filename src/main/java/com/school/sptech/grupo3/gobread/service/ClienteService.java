package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.request.ClienteUpdateRequest;
import com.school.sptech.grupo3.gobread.controller.request.LoginRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.LoginClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.PedidoClienteResponse;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.ItemPedido;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import com.school.sptech.grupo3.gobread.exceptions.UsuarioNaoEncontradoException;
import com.school.sptech.grupo3.gobread.integrations.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.mapper.ClienteMapper;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import com.school.sptech.grupo3.gobread.repository.ItemPedidoRepository;
import com.school.sptech.grupo3.gobread.repository.PedidoRepository;
import com.school.sptech.grupo3.gobread.security.GerenciadorTokenJwt;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final EnderecoService enderecoService;
    private final ClienteRepository rep;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public ClienteResponse buscarClientePorId(int id) throws UsuarioNaoEncontradoException {
        Cliente cliente = this.rep.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException());
        List<Pedido> pedidosAtivos = new ArrayList<>();
        for (int i = 0; i < cliente.getPedidos().size(); i++) {
            if (cliente.getPedidos().get(i).getStatus().equalsIgnoreCase("confirmado") || cliente.getPedidos().get(i).getStatus().equalsIgnoreCase("entrega pendente")) {
                pedidosAtivos.add(cliente.getPedidos().get(i));
            }
        }
        cliente.setPedidos(pedidosAtivos);
        ClienteResponse clienteResponse = ClienteMapper.clienteToClienteResponse(cliente);

        return clienteResponse;
    }

    public LoginClienteResponse autenticar(LoginRequest usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.email(), usuarioLoginDto.senha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Cliente usuarioAutenticado =
                rep.findByEmail(usuarioLoginDto.email())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);
        LoginClienteResponse response = new LoginClienteResponse();
        response.setToken(token);
        response.setCliente(ClienteMapper.clienteToClienteResponse(usuarioAutenticado));
        return response;

    }

    public ClienteResponse criarCliente(ClienteRequest clienteRequest) throws ResponseStatusException {
        Optional<Cliente> clienteOptional = this.rep.findByEmail(clienteRequest.email());
        if(clienteOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existente");
        }
        final Cliente cliente = ClienteMapper.toCliente(clienteRequest);
        final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(cliente.getEndereco().getCep());
        final Cliente clienteEnderecoAtualizado = cliente.atualizarEndereco(enderecoViaCep);
        String senhaCriptografada = passwordEncoder.encode(clienteEnderecoAtualizado.getSenha());
        clienteEnderecoAtualizado.setSenha(senhaCriptografada);
        rep.save(clienteEnderecoAtualizado);
        final ClienteResponse clienteResponse = ClienteMapper.clienteToClienteResponseSemPedidos(clienteEnderecoAtualizado);
        return clienteResponse;
    }

    public ClienteResponse atualizarCliente(int id, ClienteUpdateRequest clienteRequest) throws UsuarioNaoEncontradoException {
        if(rep.existsById(id)){
            final Cliente cliente = ClienteMapper.toCliente(clienteRequest);
            final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(cliente.getEndereco().getCep());
            final Cliente clienteEnderecoAtualizado = cliente.atualizarEndereco(enderecoViaCep);
            clienteEnderecoAtualizado.setId(id);
            clienteEnderecoAtualizado.getEndereco().setId(id);
            Optional<Cliente> cliente1 = rep.findById(id);
            clienteEnderecoAtualizado.setSenha(cliente1.get().getSenha());
            rep.save(clienteEnderecoAtualizado);
            final ClienteResponse clienteResponse = ClienteMapper.clienteToClienteResponseSemPedidos(clienteEnderecoAtualizado);
            return clienteResponse;
        }
        throw new UsuarioNaoEncontradoException();
    }

    public boolean deletarCliente(int id) throws UsuarioNaoEncontradoException {
        Optional<Cliente> cliente = rep.findById(id);
        if(cliente.isPresent()){
            List<Pedido> pedidos = pedidoRepository.findByCliente(cliente.get());
            for(int i = 0; i < pedidos.size(); i++){
                List<ItemPedido> itens = itemPedidoRepository.findByPedido(pedidos.get(i));
                itemPedidoRepository.deleteAll(itens);
            }
            pedidoRepository.deleteAll(pedidos);
            rep.deleteById(id);
            return true;
        }
        throw new UsuarioNaoEncontradoException();
    }


    public ClienteResponse atualizarAssinatura(String assinatura, int id) throws UsuarioNaoEncontradoException {
        Cliente cliente = rep.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário com esse id não encontrado")
        );
        cliente.setAssinatura(assinatura);
        rep.save(cliente);
        return ClienteMapper.clienteToClienteResponse(cliente);
    }
}
