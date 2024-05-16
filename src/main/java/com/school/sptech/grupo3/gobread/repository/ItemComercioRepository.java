package com.school.sptech.grupo3.gobread.repository;

import com.school.sptech.grupo3.gobread.entity.ItemComercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemComercioRepository extends JpaRepository<ItemComercio, Integer> {

    public List<ItemComercio> findByComercioId(Integer id);


}


