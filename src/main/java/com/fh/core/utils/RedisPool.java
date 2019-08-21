package com.fh.core.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private static  JedisPool jedisPool;

    private RedisPool(){};

    private static void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig, "10.10.10.105", 6379);
    }

    static {
        initPool();
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
