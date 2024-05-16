package com.school.sptech.grupo3.gobread.repository;

import com.school.sptech.grupo3.gobread.entity.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Integer> {
    Optional<Comercio> findByEmail(String email);

    List<Comercio> findByEnderecoBairro(String bairro);



}
