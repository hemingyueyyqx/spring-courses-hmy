package org.example.redisexamples.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.redisexamples.component.ULID;
import org.example.redisexamples.dto.Item;
import org.example.redisexamples.dto.Order;
import org.redisson.api.*;
import org.redisson.api.stream.StreamAddArgs;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final RedissonClient client;
    private final ULID ulid;
    public void addItems(List<Item> items) {
        RBatch batch = client.createBatch();
        for (Item item : items) {
            RMapAsync<String, Integer> map = batch.getMap(Item.PRE_KEY + item.getId(), StringCodec.INSTANCE);
            map.putAsync("total", item.getTotal());
        }
        batch.execute();
    }

    public long buy(String itemId, String userId) {
        long q = client.getFunction()
                .call(FunctionMode.WRITE,
                        "buy_62",
                        FunctionResult.LONG,
                        List.of(Item.PRE_KEY + itemId));
        if(q == -1) {
            log.debug("抢光了");
            return -1;
        }
        var id = ulid.nextULID();
        Order o = Order.builder()
                .id(id)
                .userId(userId)
                .itemId(itemId)
                .build();
        client.getBucket(Order.PRE_KEY + id)
                .set(o);
        sendMessage(o);
        return q;
    }

    public void sendMessage(Order order) {
        client.getStream(Order.STREAM_KEY, StringCodec.INSTANCE)
                .add(StreamAddArgs.entry("orderid", order.getId()));
    }

}
