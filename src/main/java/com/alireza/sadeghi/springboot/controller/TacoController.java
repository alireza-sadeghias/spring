package com.alireza.sadeghi.springboot.controller;

import com.alireza.sadeghi.springboot.configuration.TacoProps;
import com.alireza.sadeghi.springboot.domain.Taco;
import com.alireza.sadeghi.springboot.domain.TacoOrder;
import com.alireza.sadeghi.springboot.repository.OrderRepository;
import com.alireza.sadeghi.springboot.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(value = "/api/tacos",produces = "application/json")
@CrossOrigin(origins = "http://tococloud:8080")
public class TacoController {
    private TacoRepository tacoRepository;
    private OrderRepository orderRepository;
    private TacoProps tacoProps;

    public TacoController(TacoRepository tacoRepository, OrderRepository orderRepository, TacoProps tacoProps) {
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
        this.tacoProps = tacoProps;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        log.trace("");
//        Pageable pageable = PageRequest.of(0, tacoProps.getPageSize(), Sort.by("createdAt").descending());
        PageRequest pageable = PageRequest.of(0, tacoProps.getPageSize(), Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id")Long id){
        log.info("id is {}",id);
        Optional<Taco> tacoOptional = tacoRepository.findById(id);

        if(tacoOptional.isPresent()){
            log.info(tacoOptional.get().toString());
            return new ResponseEntity<>(tacoOptional.get(), HttpStatus.OK);
        }
        log.info("no data found");
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND) ;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        log.info("received taco:{}",taco.toString());
        return tacoRepository.save(taco);
    }

    @PutMapping(path = "/{orderId}",consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public TacoOrder putTaco(@PathVariable Long orderId, @RequestBody TacoOrder tacoOrder){
        tacoOrder.setId(orderId);
        return orderRepository.save(tacoOrder);
    }

    @PatchMapping(path = "/{orderId}",consumes = "application/json")
    public TacoOrder patchTaco(@PathVariable Long orderId, @RequestBody TacoOrder patch){
        TacoOrder order = orderRepository.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId){
        try{
            orderRepository.deleteById(orderId);
        }catch (EmptyResultDataAccessException e){

        }
    }
}
