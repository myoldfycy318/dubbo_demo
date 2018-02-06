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

@Component("fanoutListenter2")
public class FanoutListenter2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("fanoutListenter2............");
            System.out.print(message.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
