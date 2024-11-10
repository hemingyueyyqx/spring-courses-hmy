package org.example.redisexamples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RedissonTest {
    @Autowired
    private RedissonClient client;

    @Test
    public void testRedisson() {
        var name = "HE";
        var key = "users:14";
        RBucket<String> bucket = client.getBucket(key, StringCodec.INSTANCE);
        log.info("bucket = {}", bucket.isExists());
        bucket.set(name);
        RBucket<String> bucket1 = client.getBucket(key, StringCodec.INSTANCE);
        log.info("bucket = {}", bucket1.get());
    }
}
