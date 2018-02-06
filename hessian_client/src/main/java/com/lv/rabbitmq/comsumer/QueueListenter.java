package com.lv.rabbitmq.comsumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * QueueListenter
 *
 * @author Zhang ShanMin
 * @date 2016/4/29
 * @time 13:21
 */

@Component
public class QueueListenter implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("2222............");
            System.out.print(message.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
