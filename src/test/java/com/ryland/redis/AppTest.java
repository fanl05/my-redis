package com.ryland.redis;

import com.ryland.redis.config.BaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;

@Slf4j
public class AppTest {

    private StringRedisTemplate stringRedisTemplate;

    @Before
    public void setUp() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BaseConfig.class);
        stringRedisTemplate = ctx.getBean(StringRedisTemplate.class);
    }

    @Test
    public void testSetName() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("name", "ryland");
        log.debug("name: [{}]", valueOperations.get("name"));
    }

    @Test
    public void testDelNameByLua() {
        DefaultRedisScript<Long> luaScript = new DefaultRedisScript<>();
        luaScript.setLocation(new ClassPathResource("lua/checkAndDel.lua"));
        luaScript.setResultType(Long.class);
        stringRedisTemplate.execute(luaScript, Collections.singletonList("name"), "ryland");

    }

}
