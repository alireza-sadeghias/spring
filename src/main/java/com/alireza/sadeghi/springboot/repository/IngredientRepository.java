package com.alireza.sadeghi.springboot.repository;

import com.alireza.sadeghi.springboot.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient,String> {

}
