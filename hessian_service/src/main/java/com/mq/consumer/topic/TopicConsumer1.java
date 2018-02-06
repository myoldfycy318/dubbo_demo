package com.mq.consumer.topic;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

/**
 * Created by zhangshanmin on 2016/2/24.
 */

@Component("topicConsumer1")
public class TopicConsumer1 {


    public void receive(Object m) {

        System.out.println("topicConsumer1 接收到信息：\n"+new Gson().toJson(m));
    }
}
