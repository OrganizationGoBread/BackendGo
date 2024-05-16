package com.school.sptech.grupo3.gobread.repository;

import com.school.sptech.grupo3.gobread.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByNome(String nome);


}
