package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.controller.response.PedidoResponse;
import com.school.sptech.grupo3.gobread.entity.ItemPedido;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import com.school.sptech.grupo3.gobread.estruturaPilhaFila.FilaObj;
import com.school.sptech.grupo3.gobread.estruturaPilhaFila.PilhaObj;
import com.school.sptech.grupo3.gobread.mapper.PedidoMapper;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import com.school.sptech.grupo3.gobread.repository.ComercioRepository;
import com.school.sptech.grupo3.gobread.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ComercioRepository comercioRepository;
    private PilhaObj pilhaPedidos = new PilhaObj(7);
    private FilaObj<Pedido> filaPedidos = new FilaObj<>(10, Pedido.class);
    List<String> diasDosPedidosFeitos = new ArrayList<>();

    public List<String> cadastrar(PedidoRequest pedidoRequest){
        Pedido pedido = PedidoMapper.toPedido(pedidoRequest);
        if(clienteRepository.existsById(pedido.getCliente().getId())){
            if(comercioRepository.existsById(pedido.getComercio().getId())){
                filaPedidos.insert(pedido);
                diasDosPedidosFeitos.add(pedido.getDiaEntrega());
                return diasDosPedidosFeitos;
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comercio não existe!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não existe!");
    }

    public PedidoResponse salvarPedidos() {
        diasDosPedidosFeitos = new ArrayList<>();
        Pedido pedido = new Pedido();
        int tamanho = filaPedidos.getTamanho();
        for (int i = 0; i < tamanho; i++) {
            pedido = (Pedido) filaPedidos.poll();
            this.pedidoRepository.save(pedido);
        }
        return PedidoMapper.toPedidoResponse(pedido);
    }


    public void deletar(int id) {
        Pedido pedido = this.pedidoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado")
        );
        pilhaPedidos.push(pedido);
        this.pedidoRepository.deleteById(id);
    }

    public PedidoResponse reverterDelete() {
        Pedido pedido = (Pedido) pilhaPedidos.pop();
        this.pedidoRepository.save(pedido);
        return PedidoMapper.toPedidoResponse(pedido);
    }

    public void atualizarStatusEntregaPendente(int id) {
        Pedido pedido = this.pedidoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado")
        );
        if(pedido.getStatus().equals("entrega pendente")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido já está pendente");
        }
        pedido.setStatus("entrega pendente");
        Random random = new Random();
        pedido.setCodigoVerificacao(random.nextInt(9000)+ 1000);
        pedidoRepository.save(pedido);
    }

    public void finalizarPedido(int id, Integer codigoVerificacao) {
        Pedido pedido = this.pedidoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado")
        );
        if(pedido.getStatus().equals("finalizado")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido já está finalizado");
        }
        if(pedido.getCodigoVerificacao().intValue() != codigoVerificacao.intValue()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de verificação incorreto!");
        }
        pedido.setStatus("finalizado");
        pedidoRepository.save(pedido);
        gerarNovoPedido(pedido);
    }

    public void gerarNovoPedido(Pedido novoPedido) {
        Pedido pedido = new Pedido();
        List<ItemPedido> itensPedido = novoPedido.getItensPedido();
        List<ItemPedido> itensPedidoNovo = new ArrayList<>();
        for (int i = 0; i < itensPedido.size(); i++) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setQuantidade(itensPedido.get(i).getQuantidade());
            itemPedido.setProduto(itensPedido.get(i).getProduto());
            itensPedidoNovo.add(itemPedido);
        }

        pedido.setItensPedido(itensPedidoNovo);
        pedido.setCliente(novoPedido.getCliente());
        pedido.setComercio(novoPedido.getComercio());
        pedido.setDiaEntrega(novoPedido.getDiaEntrega());
        pedido.setHorarioEntrega(novoPedido.getHorarioEntrega());
        pedidoRepository.save(pedido);
    }
}
