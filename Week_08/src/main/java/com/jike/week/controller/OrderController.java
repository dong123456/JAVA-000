package com.jike.week.controller;

import com.google.common.collect.Maps;
import com.jike.week.dao.po.OrderPO;
import com.jike.week.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 分库分表测试
 * order库分为两个库 order0和order1
 * order表分为32个子表  0...32  使用user_id分表
 * 测试代码没有做参数校验和异常处理
 * @author zdd
 */
@Slf4j
@RestController
public class OrderController {
    /**
     * 处理逻辑
     *
     * @param
     * @return 结果
     */

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/findByid/{userId}/{orderId}", method = RequestMethod.GET)
    public Map<String, Object> getOrderInfo( @PathVariable int userId, @PathVariable int orderId ){
        Map<String, Object> result = Maps.newHashMap();

        OrderPO orderPO = orderService.findOrderById(userId, orderId);

        result.put("status", 0);
        result.put("data", orderPO);

        return result;
    }


    @RequestMapping(value = "/order/findAll/{userId}/{page}", method = RequestMethod.GET)
    public Map<String, Object> getOrderList( @PathVariable int userId, @PathVariable int page ){
        Map<String, Object> result = Maps.newHashMap();

        int pageSize = page*10;

        page = page-1;

        List<OrderPO> orderPO = orderService.findOrderList(userId, page, pageSize);

        result.put("status", 0);
        result.put("data", orderPO);

        return result;
    }


    @RequestMapping(value = "/order/insert/{userId}", method = RequestMethod.POST)
    public Map<String, Object> insertOrderInfo( @PathVariable int userId, @RequestBody OrderPO orderPO ){
        //入参校验 todo


        orderPO.setUserId(userId);

        Map<String, Object> result = Maps.newHashMap();
        int insertId = orderService.insert(orderPO);

        result.put("status", 0);
        return result;

    }

    @RequestMapping(value = "/order/update/{userId}/{orderId}", method = RequestMethod.POST)
    public Map<String, Object> updateOrderInfo( @PathVariable int userId, @PathVariable int orderId, @RequestBody OrderPO orderPO ){
        //入参校验 todo

        orderPO.setUserId(userId);
        orderPO.setId(orderId);

        Map<String, Object> result = Maps.newHashMap();
        int insertId = orderService.update(orderPO);

        result.put("status", 0);
        return result;

    }




}
