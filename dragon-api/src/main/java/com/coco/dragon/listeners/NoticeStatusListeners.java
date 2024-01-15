package com.coco.dragon.listeners;

import com.coco.dragon.req.follow.DgFollowSaveReq;
import com.coco.dragon.service.DgFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author liaoshen
 */
@Component
@RequiredArgsConstructor
public class NoticeStatusListeners {

    private final DgFollowService dgFollowService;

    @RabbitListener(bindings=@QueueBinding(value = @Queue(name = "dragon.user.follow.queue",durable = "true"),exchange = @Exchange(name = "follow.topic",type = ExchangeTypes.TOPIC),key = "follow.success"))
    public void listenFollow(){

    }
}
