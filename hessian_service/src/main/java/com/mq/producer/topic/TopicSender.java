package com.mq.producer.topic;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by zhangshanmin on 2016/2/23.
 */
//@Component("topicSender")
public class TopicSender {

    @Resource(name = "jmsTopicTemplate")
    private JmsTemplate jmsTemplate;

    public void setTopicMsg(String destination, final String context){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(context);
            }
        });
    }
}
