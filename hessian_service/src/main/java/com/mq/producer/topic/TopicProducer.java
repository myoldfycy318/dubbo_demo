package com.mq.producer.topic;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by zhangshanmin on 2016/2/23.
 */

public class TopicProducer {

    private JmsTemplate jmsTemplate;

	private Topic destination;


    public void sendMsg(Object obj){
        jmsTemplate.convertAndSend(destination,obj);
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setDestination(Topic destination) {
        this.destination = destination;
    }
}
