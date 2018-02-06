package com.lv.rabbitmq.comsumer;

import org.springframework.stereotype.Component;

/**
 * QueueLitenerOne
 *
 * @author Zhang ShanMin
 * @date 2016/4/29
 * @time 12:06
 */
@Component("queueLitenerOne")
public class QueueLitenerOne {

    public void listen(String msg) {
        System.out.println("enter listen method");
        System.out.println(msg);
    }
}
