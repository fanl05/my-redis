package com.ryland.redis.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Objects;

public class PoolTest {

    private JedisPool jedisPool;

    @Before
    public void setUp() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(8);
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMinIdle(0);
        jedisPool = new JedisPool(jedisPoolConfig, "110.42.187.113", 6379);
    }

    @After
    public void tearDown() {
        if (Objects.nonNull(jedisPool)) {
            jedisPool.close();
        }
    }

    @Test
    public void test01() {
        Jedis jedis = getJedis();
        jedis.set("company", "vip");
        System.out.println(jedis.get("company"));
    }

    private Jedis getJedis() {
        return jedisPool.getResource();
    }

}
