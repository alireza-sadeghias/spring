package com.alireza.sadeghi.springboot.controller;

import com.alireza.sadeghi.springboot.domain.Ingredient;
import com.alireza.sadeghi.springboot.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestController
@RequestMapping(value = "/api/ingredients",produces = "application/json")
@CrossOrigin(origins = {"http:/ /localhost:8080"})
public class IngredientController {

    private IngredientRepository repo;

    @Autowired
    public IngredientController(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients(){
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#hasRole('admin')")
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){
        return repo.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("#hasRole('admin')")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("id") String id){
        log.info("{} received for removal",id);
        try {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (ConstraintViolationException e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
