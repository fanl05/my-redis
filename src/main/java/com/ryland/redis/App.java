package com.ryland.redis;

import com.ryland.redis.config.BaseConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(BaseConfig.class);


}
