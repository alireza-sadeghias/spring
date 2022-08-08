package com.alireza.sadeghi.springboot.repository;

import com.alireza.sadeghi.springboot.domain.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends JpaRepository<Taco,Long> {
//    Iterable<Taco> findAll(PageRequest pageable);
}
