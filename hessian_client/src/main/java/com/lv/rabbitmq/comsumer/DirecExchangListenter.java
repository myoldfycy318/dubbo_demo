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

@Component("direcExchangListenter")
public class DirecExchangListenter implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("direcExchangListenter............");
            String body = new String(message.getBody(), "utf-8");
            System.out.print("22:\n"+body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
