package org.example;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest //在单元测试方法执行之前，会先初始化Spring容器
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSet(){
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        ops.set("account","zhangsan");
    }
    @Test
    public void testGet(){
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();

        System.out.println(ops.get("account"));
    }
}
