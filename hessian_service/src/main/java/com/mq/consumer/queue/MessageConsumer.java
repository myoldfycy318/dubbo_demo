package com.mq.consumer.queue;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

/**
 * Created by zhangshanmin on 2016/2/24.
 */

@Component("messageConsumer")
public class MessageConsumer {


    public void receive(Object m) {

        System.out.println("MessageConsumer 接收到信息："+new Gson().toJson(m));
    }
}
