package com.alireza.sadeghi.springboot.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;

@Data
@ToString
@Table
public class Taco {
    @Id
    private Long id;
    @NotNull
    @Size(min=5,message = "Name should be at least 5 characters long")
    private String name;
    @NotNull
    @Size(min = 1,message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
    private Date createdAt = new Date();
}
