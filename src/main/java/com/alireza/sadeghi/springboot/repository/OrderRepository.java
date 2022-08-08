package com.alireza.sadeghi.springboot.repository;

import com.alireza.sadeghi.springboot.domain.TacoOrder;
import com.alireza.sadeghi.springboot.domain.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {
    List<TacoOrder> findByUsersOrderByPlacedAtDesc(Users user, Pageable pageable);
}
