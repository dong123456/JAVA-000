package com.jike.week.service;

import com.jike.week.dao.mapper.OrderMapper;
import com.jike.week.dao.po.OrderPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public OrderPO findOrderById(int userId, int orderId) {
        return orderMapper.findOrderById(userId, orderId);
    }

    public int insert(OrderPO orderPO) {
        return orderMapper.insert(orderPO);
    }

//    @Override
//    boolean updateById(OrderInfo entity);

 //   @Override updateById(OrderINfo entity);

}
