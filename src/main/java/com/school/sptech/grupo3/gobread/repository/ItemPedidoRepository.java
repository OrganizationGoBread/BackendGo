package com.school.sptech.grupo3.gobread.repository;

import com.school.sptech.grupo3.gobread.entity.ItemPedido;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

    List<ItemPedido> findByPedido(Pedido pedido);

}
