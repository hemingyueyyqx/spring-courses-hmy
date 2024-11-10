package org.example.redisexamples.service;

import lombok.extern.slf4j.Slf4j;
import org.example.redisexamples.dto.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    void addItems() {
        List<Item> items = List.of(
                Item.builder().id("01JCAWP1QC6ETT8BRWPK220XAT").total(30).build(),
                Item.builder().id("01JCAWP1QCG1C8NQ39N39MNH46").total(30).build()
        );
        orderService.addItems(items);
    }

    @Test
    void buy() throws InterruptedException {
        var count = 200;
        CountDownLatch latch = new CountDownLatch(count);
        var random = new Random();
        for (int i = 0; i < 200; i++) {
            Thread.startVirtualThread(() -> {
                long buy = orderService.buy("01JCAWP1QC6ETT8BRWPK220XAT", String.valueOf(random.nextInt(count)));
                log.debug("{}", buy);
                latch.countDown();
            });

        }
        latch.await();
        Thread.sleep(6000);
    }
}