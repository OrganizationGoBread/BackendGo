package com.school.sptech.grupo3.gobread.repository;

import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {


    List<Pedido> findByComercio(Comercio comercio);

    List<Pedido> findByCliente(Cliente cliente);



}
