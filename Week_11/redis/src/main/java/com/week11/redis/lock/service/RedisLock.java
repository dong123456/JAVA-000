package com.week11.redis.lock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * redis设置分布式锁
 * redis池优化  todo
 */

@Component
@Slf4j
public class RedisLock {
	private static final String REDIS_PREFIX = "lock:";
	private static final String LOCK_SUCCESS = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_WITH_EXPIRE_TIME = "EX";
	private static final Long RELEASE_SUCCESS = 1L;
	
//	@Resource
//	private JedisConnectionFactory jedisConnectionFactory;

	public boolean tryGetLock(String cacheKey, String cacheValue, int timeout) {
		Jedis jedis = new Jedis("localhost", 6379);
		try {


		//	RedisConnection conn = RedisConnectionUtils.getConnection(jedisConnectionFactory);
		//	jedis = (Jedis) conn.getNativeConnection();
			String result = jedis.set(cacheKey, cacheValue, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, timeout);
			if (LOCK_SUCCESS.equals(result)) {
				return true;
			}
		} catch (Exception e) {
			log.error("try to get lock error, lockKey:{}, requestId:{}", cacheKey, cacheValue, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	public boolean releaseLock(String cacheKey, String cacheValue) {
		Jedis jedis = new Jedis("localhost", 6379);
		try {
	//		RedisConnection conn = RedisConnectionUtils.getConnection(jedisConnectionFactory);
	//		jedis = (Jedis) conn.getNativeConnection();
			String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
			Object result = jedis.eval(script, Collections.singletonList(cacheKey), Collections.singletonList(cacheValue));
			if (RELEASE_SUCCESS.equals(result)) {
				return true;
			}
		} catch (Exception e) {
			log.error("release lock error,lockKey:{}, requestId:{}", cacheKey, cacheValue, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}
}