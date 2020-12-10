package com.jike.week.dao.mapper;

import com.jike.week.dao.po.OrderPO;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 *
 */
@Mapper
public interface OrderMapper {
    /**
     * 查询单条
     */
    OrderPO findOrderById(@Param("userId") int userId, @Param("orderId") int orderId);

    /**
     * 查询多条
     */
    List<OrderPO> findOrderList(@Param("userId") int userId, @Param("page") int page, @Param("pageSize") int pageSize);

    /**
     * 插入
     */
    int insert(OrderPO orderPO);

    /**
     * 更新
     */
    int update(OrderPO orderPO);

}
