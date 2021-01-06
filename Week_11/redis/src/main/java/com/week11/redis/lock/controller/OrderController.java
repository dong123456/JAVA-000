package com.week11.redis.lock.controller;

import com.week11.redis.lock.service.RedisLock;
import com.week11.redis.lock.service.RedisStock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 减库存和分布式锁
 * @author zdd
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    RedisLock redisLock;

    @Autowired
    RedisStock redisStock;

    /**
     * 插入单条订单
     *
     * @param
     * @return 结果
     */
    @RequestMapping(value = "/order/insert/{userId}", method = RequestMethod.POST)
    public Map<String, Object> insertOrderInfo( @PathVariable int userId, @RequestParam Map<String, String> params){
        //入参校验 todo
        String orderNo = params.get("orderNo");

//        String goodsSku = params.get("goodsSku");

//        int goodsNum = Integer.parseInt(params.get("goodsNum"));

        orderNo = "cache22222";
        String goodsSku = "12345678";
        int goodsNum = 2;

        Map<String, Object> result = new HashMap<>();

        //获取锁，同一个orderNo只操作一次
        try {
            if (redisLock.tryGetLock(orderNo, "stock_" + goodsSku, 3)) {

                //模拟操作...   sleep 2s
                Thread.sleep(2000);

                //减库存
                if (redisStock.Stock(goodsSku, goodsNum)) {
                    result.put("message", "ok");
                } else {
                    result.put("message", "扣减库存失败");
                }

                redisLock.releaseLock(orderNo, "111");
            } else {
                //直接返回订单已提交
                //后续可以优化为50ms获取一次锁和结果，有结果了在返回，直到3s超时
                result.put("message", "订单处理中，请勿重复提交");

            }
        } catch (Exception e) {
            log.error("get lock or stock error", e);
        }

        result.put("status", 1);
        return result;

    }

}
