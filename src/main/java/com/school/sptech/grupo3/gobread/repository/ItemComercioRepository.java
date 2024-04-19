package com.school.sptech.grupo3.gobread.repository;

import com.school.sptech.grupo3.gobread.entity.ItemComercio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemComercioRepository extends JpaRepository<ItemComercio, Integer> {

    public List<ItemComercio> findByComercioId(Integer id);


}


