package com.fh.core.utils;

import redis.clients.jedis.Jedis;

import java.util.Map;


public class RedisUtil {

    public static String hget(String key, String filed){
        Jedis jedis = null;
        String result = "";
        try {
            jedis = RedisPool.getJedis();
            result = jedis.hget(key, filed);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis = null;
            }
        }
        return result;
    }

    public static void hmset(String key, Map hash){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.hmset(key, hash);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis = null;
            }
        }
    }

    public static void set(String key, String val){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.set(key, val);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis = null;
            }
        }

    }

    public static String get(String key){
        Jedis jedis = null;
        String result = "";
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis = null;
            }
        }
        return result;
    }

    public static void del(String key){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis = null;
            }
        }

    }

    public static boolean setnxExpire(String key, String val, int seconds){
        Jedis jedis = null;
        boolean flag = false;
        try {
            jedis = RedisPool.getJedis();
            Long result = jedis.setnx(key, val);
            if (result == 1){
                //返回结果为1 说明redis不存在该key
                jedis.expire(key, seconds);
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis = null;
            }
        }
        return flag;
    }

    public static void setex(String key,String val, int seconds){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.setex(key, seconds,val);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis = null;
            }
        }
    }

    public static Long incr(String key){
        Jedis jedis = null;
        Long incr = null;
        try {
            jedis = RedisPool.getJedis();
            incr = jedis.incr(key);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis = null;
            }
        }
        return incr;
    }

    public static void expire(String key, int seconds) {
        Jedis jedis = null;
        Long incr = null;
        try {
            jedis = RedisPool.getJedis();
            incr = jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis = null;
            }
        }
    }
}
