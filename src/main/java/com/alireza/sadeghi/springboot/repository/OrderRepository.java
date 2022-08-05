package com.alireza.sadeghi.springboot.repository;

import com.alireza.sadeghi.springboot.domain.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

}
