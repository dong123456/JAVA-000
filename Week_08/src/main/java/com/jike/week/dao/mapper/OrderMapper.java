package com.jike.week.dao.mapper;

import com.jike.week.dao.po.OrderPO;
import org.apache.ibatis.annotations.*;

/**
 *
 */
@Mapper
public interface OrderMapper {
    /**
     * 查询单调
     */
    OrderPO findOrderById(@Param("userId") int userId, @Param("orderId") int orderId);

    /**
     * 插入
     */
    int insert(OrderPO orderPO);

}
