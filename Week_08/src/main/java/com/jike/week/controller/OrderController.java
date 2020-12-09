package com.jike.week.controller;

import com.google.common.collect.Maps;
import com.jike.week.dao.po.OrderPO;
import com.jike.week.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 分库分表测试
 * order库分为两个库 order0和order1
 * order表分为32个子表
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
        result.put("data", orderPO.getOrderNo());

        return result;
    }

}
