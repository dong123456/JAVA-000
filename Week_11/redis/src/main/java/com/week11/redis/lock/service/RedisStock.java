package com.week11.redis.lock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * redis设置库存
 * redis池优化 todo
 */

@Component
@Slf4j
public class RedisStock {
	private static final Long RELEASE_SUCCESS = 1L;

	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_WITH_EXPIRE_TIME = "EX";

	private static final int STOCK_EXPIRE = 86400;


	/**
	 * 模拟加库存
	 *
	 * @param key    库存key
	 * @param num    库存数量
	 * 防止热点key，可以把同一个产品的库存按一定规则打散 todo
	 * @return
	 */
	public boolean Stock(String key, int num) {
		Jedis jedis = new Jedis("localhost", 6379);
		boolean isSuccess = false;
		boolean hasKey = jedis.exists(key);
		// 判断key是否存在，存在就直接更新
		if (!hasKey) {
			//初始化库存  从数据库里取值...   测试默认有1w库存
			int stockNum = 10000;
			jedis.setex(key, STOCK_EXPIRE, String.valueOf(stockNum));
		}

		int residueStock = Integer.parseInt(jedis.get(key));

		if(residueStock > num) {
			jedis.decrBy(key, num);

			//塞入MQ去异步减mysql库存...  todo
			isSuccess = true;
		}
		jedis.close();
		return isSuccess;
	}

}