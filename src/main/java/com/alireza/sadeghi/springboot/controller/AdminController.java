package com.alireza.sadeghi.springboot.controller;

import com.alireza.sadeghi.springboot.service.OrderAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private OrderAdminService adminService ;

    public AdminController(OrderAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders(){
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }


}
