package com.mq.consumer.topic;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Topic消息监听器
 * Created by zhangshanmin on 2016/2/23.
 */
@Component("topicReceiver1")
public class TopicReceiver1 implements MessageListener {


    @Override
    public void onMessage(Message message) {

        try {
             Thread.sleep(1000*5);
          System.out.println("topicReceiver1接收到消息:"+((TextMessage)message).getText());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
