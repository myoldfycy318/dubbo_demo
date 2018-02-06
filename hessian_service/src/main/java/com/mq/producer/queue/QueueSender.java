package com.mq.producer.queue;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 队列消息生产者，发送消息到队列
 * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
 * Created by zhangshanmin on 2016/2/23.
 */
//@Component("queueSender")
public class QueueSender {

    @Resource(name = "jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    /**
     * 发送一条消息到指定的队列（目标）
     * @param destinationName 队列名称
     * @param msg 消息内容
     */
    public void sendMsg(String destinationName ,final String msg){
       jmsTemplate.send(destinationName, new MessageCreator() {
           @Override
           public Message createMessage(Session session) throws JMSException {
               return session.createTextMessage(msg);
           }
       });
    }
}
