package com.redisTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.healthmarketscience.jackcess.impl.expr.StringValue;

import redis.clients.jedis.Jedis;

public class Goods {
    private Jedis jedis = new Jedis();

    public boolean tryLock(String key, String requset, int timeout) {
        Long result = jedis.setnx(key, requset);
        // 拿到锁
        if (result == 1L) {
            System.out.println(jedis.get(key));
            return jedis.expire(key, timeout) == 1L;
        } else { // 没有拿到锁
            return false;
        }
    }

    // timeout 单位是s
    public boolean tryLockWithLua(String key, String requset, int timeout) {
        String lua_scripts = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then " +
                "redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        keys.add(key);
        values.add(requset);
        values.add(String.valueOf(timeout));
        Object result = jedis.eval(lua_scripts, keys, values);
        System.out.println("first reuslt=" + result.equals(1L));

    /*    List<String> values2 = new ArrayList<>();
        values2.add(requset + "---" + requset);
        values2.add(String.valueOf(timeout));
        Object result2 = jedis.eval(lua_scripts, keys, values2);
        System.out.println("second reuslt=" + result2.equals(1L));*/

        //判断是否成功
        System.out.println(jedis.get(key));
        return result.equals(1L);
    }

    public Boolean tryLockSet(String key, String requset, int timeout) {
        String result = jedis.set(key, requset, "NX", "EX", timeout);
        System.out.println("first reuslt=" + "OK".equals(result));

        String result2 = jedis.set(key, requset, "NX", "EX", timeout);
        System.out.println("second reuslt=" + "OK".equals(result2));
        return "OK".equals(result);
    }

    public Boolean releaseLockWityLua(String key, String value) {
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";
        return jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
    }

    public boolean tryLockReent(String key, String requset, int timeout) {
        Long result = jedis.setnx(key, requset);
        // 拿到锁
        if (result == 1L) {
            System.out.println("first:" + jedis.get(key));
            Long result2 = jedis.setnx(key, requset + "----" + requset);
            if (result2 == 1L) {
                System.out.println("second" + jedis.get(key));
            }
            return jedis.expire(key, timeout) == 1L;
        } else { // 没有拿到锁
            return false;
        }
    }

    public static void main(String[] args) {
        Goods goods = new Goods();
        Boolean result = goods.tryLockSet("qin", "wenjing", 10);
       /* Thread thread = new Thread();

        Boolean result2 = goods.releaseLockWityLua("qin", "wenjing");*/
        System.out.println(result);
        // System.out.println(result2);
    }
}
