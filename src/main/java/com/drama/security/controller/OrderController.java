package com.drama.security.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.drama.security.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/list/{id}")
    @ResponseBody
    public Order getOrder(@PathVariable("id")String id){
        Order order = new Order(IdUtil.randomUUID(), "订单一", RandomUtil.randomDay(1, 7));
        return order;
    }
}
