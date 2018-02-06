package com.lv.event;

import com.comm.constant.MethodExecutionStatus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * MethodExecutionEventPublisher
 *
 * @author Zhang ShanMin
 * @date 2017/1/13
 * @time 15:37
 */
public class MethodExecutionEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    public void methodToMonitor() {
        MethodExecutionEvent beginEvent = new MethodExecutionEvent(this, "methodToMonitor", MethodExecutionStatus.BEGIN);
        this.eventPublisher.publishEvent(beginEvent);
        System.out.println("--------------------------------------------------");
        MethodExecutionEvent endEvent = new MethodExecutionEvent(this, "methodToMonitor", MethodExecutionStatus.END);
        this.eventPublisher.publishEvent(endEvent);
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}