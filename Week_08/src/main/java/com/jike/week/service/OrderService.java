package com.jike.week.service;

import com.jike.week.dao.mapper.OrderMapper;
import com.jike.week.dao.po.OrderPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


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

    public List<OrderPO> findOrderList(int userId, int page, int pageSize) {
        return orderMapper.findOrderList(userId, page, pageSize);
    }

    public int insert(OrderPO orderPO) {
        return orderMapper.insert(orderPO);
    }

    public int update(OrderPO orderPO) {
        return orderMapper.update(orderPO);
    }

    public int delete(int userId, int orderId) {
        return orderMapper.delete(userId, orderId);
    }


}
