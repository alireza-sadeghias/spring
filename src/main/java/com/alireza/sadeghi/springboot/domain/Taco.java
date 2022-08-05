package com.alireza.sadeghi.springboot.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class Taco {


    private Date createdAt = new Date();

    @NotNull
    @Size(min=5,message = "Name should be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1,message = "You must choose at least 1 ingredient")
    private List<IngredientUDT> ingredients=new ArrayList<>();

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
    }
}
