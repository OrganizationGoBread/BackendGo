package com.school.sptech.grupo3.gobread.repository;

import com.school.sptech.grupo3.gobread.entity.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComercioRepository extends JpaRepository<Comercio, Integer> {
    Optional<Comercio> findByEmail(String email);

    List<Comercio> findByEnderecoBairro(String bairro);



}
