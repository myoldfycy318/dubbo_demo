package com.mq.producer.queue;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.Queue;

/**
 * Created by zhangshanmin on 2016/2/23.
 */

public class MessageProducer {

    private JmsTemplate jmsTemplate;

	private Queue destination;


    public void sendMsg(Object obj){
        jmsTemplate.convertAndSend(destination,obj);
    }



    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    public void setDestination(Queue destination) {
        this.destination = destination;
    }
}
