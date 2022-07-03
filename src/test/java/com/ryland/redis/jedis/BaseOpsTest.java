package com.ryland.redis.jedis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BaseOpsTest {

    private Jedis jedis;

    @Before
    public void setUp() {
        jedis = new Jedis("110.42.187.113", 6379);
        jedis.select(0);
    }

    public void tearDown() {
        if (Objects.nonNull(jedis)) {
            jedis.close();
        }
    }

    @Test
    public void test01() {
        jedis.set("name", "ryland");
        System.out.println(jedis.get("name"));

        jedis.mset("a", "1", "b", "2", "c", "3");
        List<String> values = jedis.mget("a", "b", "c");
        System.out.println(values);
    }

    @Test
    public void test02() {
        Map<String, String> attr = new HashMap<>();
        attr.put("age", "24");
        attr.put("sex", "male");
        jedis.hset("ryland", attr);
        System.out.println(jedis.hget("ryland", "sex"));
        System.out.println(jedis.hgetAll("ryland"));
    }

    @Test
    public void test03() {
        jedis.lpush("array", "3");
        jedis.lpush("array", "2");
        jedis.lpush("array", "1");
        jedis.lpush("array", "0");
        System.out.println(jedis.lpop("array"));
        System.out.println(jedis.lpop("array"));
        System.out.println(jedis.lpop("array"));
        System.out.println(jedis.lpop("array"));
    }

    @Test
    public void test04() {
        Map<String, Double> scores = new HashMap<>();
        scores.put("jessica", 70D);
        scores.put("ryland", 100D);
        scores.put("sherry", 80D);
        jedis.zadd("scores", scores);
        System.out.println(jedis.zrank("scores", "ryland"));
        System.out.println(jedis.zrevrank("scores", "ryland"));
        System.out.println(jedis.zcard("scores"));
    }


}
