package com.mq.consumer.topic;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Topic消息监听器
 * Created by zhangshanmin on 2016/2/23.
 */
@Component("topicReceiver2")
public class TopicReceiver2 implements MessageListener {


    @Override
    public void onMessage(Message message) {

        try {
          Thread.sleep(1000*10);
          System.out.println("topicReceiver2接收到消息:"+((TextMessage)message).getText());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
