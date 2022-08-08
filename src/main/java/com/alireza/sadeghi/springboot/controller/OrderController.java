package com.alireza.sadeghi.springboot.controller;


import com.alireza.sadeghi.springboot.configuration.OrderProps;
import com.alireza.sadeghi.springboot.domain.Taco;
import com.alireza.sadeghi.springboot.domain.Users;
import com.alireza.sadeghi.springboot.repository.OrderRepository;
import com.alireza.sadeghi.springboot.domain.TacoOrder;
import com.alireza.sadeghi.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private UserRepository userRepository;
    private OrderProps orderProps;
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository,OrderProps orderProps) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderProps = orderProps;
    }


    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

//    @PostMapping
//    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
//        if(errors.hasErrors()){
//            return "orderForm";
//        }
//        order.setPlacedAt(new Date());
//        orderRepository.save(order);
//        log.info("Order submitted: {}", order);
//        sessionStatus.setComplete();
//        return "redirect:/";
//    }

    //TODO this litters code with  unrelated security code.
//    @PostMapping
//    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, Principal principal) {
//        if(errors.hasErrors()){
//            return "orderForm";
//        }
//        Users user = userRepository.findByUsername(principal.getName());
//        order.setUsers(user);
//        order.setPlacedAt(new Date());
//        orderRepository.save(order);
//        log.info("Order submitted: {}", order);
//        sessionStatus.setComplete();
//        return "redirect:/";
//    }

    //TODO there is more clean code than this
//    @PostMapping
//    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, Authentication authentication) {
//        if(errors.hasErrors()){
//            return "orderForm";
//        }
//        Users user = (Users) authentication.getPrincipal();
//        order.setUsers(user);
//        order.setPlacedAt(new Date());
//        orderRepository.save(order);
//        log.info("Order submitted: {}", order);
//        sessionStatus.setComplete();
//        return "redirect:/";
//    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal Users user){
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info(user.toString());
        order.setUsers(user);
        order.setPlacedAt(new Date());
        orderRepository.save(order);
        log.info(order.toString());

        //TODO for use in lower level of code
        //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //        Users users = (Users) authentication.getPrincipal();

        sessionStatus.setComplete();
        return "redirect:/";

    }

//TODO DO NOT REMOVE!
//    @PostAuthorize("hasRole('Admin') || returnObject.user.username==authentication.name")
//    public TacoOrder getOrder(long id){
//        return orderRepository.findById(id).filter(x->x!=null).orElse(null);
//    }

    @GetMapping
    public String orderHistory(@AuthenticationPrincipal Users user, Model model){
        Pageable pageable  = PageRequest.of(0,orderProps.getPageSize());
        model.addAttribute("orders",orderRepository.findByUsersOrderByPlacedAtDesc(user,pageable));
        return "orderList";
    }

}
