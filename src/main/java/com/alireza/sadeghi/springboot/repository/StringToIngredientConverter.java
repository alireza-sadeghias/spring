package com.alireza.sadeghi.springboot.repository;

import java.util.Optional;

import com.alireza.sadeghi.springboot.domain.Ingredient;
import com.alireza.sadeghi.springboot.domain.IngredientUDT;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

  private IngredientRepository ingredientRepository;

  public StringToIngredientConverter(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }
  
  @Override
  public IngredientUDT convert(String id) {
    Optional<Ingredient> ingredient = ingredientRepository.findById(id);
    if (ingredient.isEmpty()) {
      return null;
    }
    
    return ingredient.map(i -> {
      return new IngredientUDT(i.getName(), i.getType());
    }).get();
  }
  
}
