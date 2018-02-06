package com.mq.consumer.queue;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 队列消息监听器
 * Created by zhangshanmin on 2016/2/23.
 */
@Component("queueReceiver1")
public class QueueReceiver1 implements MessageListener {


    @Override
    public void onMessage(Message message) {

        try {
          System.out.println("QueueReceiver1接收到消息:"+((TextMessage)message).getText());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
