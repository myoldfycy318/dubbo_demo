package com.lv.rabbitmq.comsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component("sdkPayTransListener")
public class SdkPayTransListener implements MessageListener {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public void onMessage(Message arg0) {
        try {
            String body = new String(arg0.getBody(), "utf-8");
            log.info("队列的值:" + body);
            //发送奖励
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }


}
