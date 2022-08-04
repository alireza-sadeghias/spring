package com.alireza.sadeghi.springboot.controller;

import com.alireza.sadeghi.springboot.repository.IngredientRepository;
import com.alireza.sadeghi.springboot.domain.Ingredient;
import com.alireza.sadeghi.springboot.domain.Taco;
import com.alireza.sadeghi.springboot.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.alireza.sadeghi.springboot.domain.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoControllers {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoControllers(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Streamable.of(ingredientRepository.findAll()).toList();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        TacoOrder order = new TacoOrder();
        log.info(order.toString());
        return order;
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        Taco taco = new Taco();
        log.info(taco.toString());
        return taco;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("tacoOrder:{}", tacoOrder);

        tacoOrder.setPlacedAt(new Date());
        tacoOrder.addTaco(taco);
        log.info("Processing Taco:{}", taco);

        log.info("tacoOrder:{}", tacoOrder);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
