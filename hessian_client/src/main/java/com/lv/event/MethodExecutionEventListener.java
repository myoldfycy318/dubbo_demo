package com.lv.event;

import com.comm.constant.MethodExecutionStatus;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * MethodExecutionEventListener
 *
 * @author Zhang ShanMin
 * @date 2017/1/13
 * @time 15:35
 */
public class MethodExecutionEventListener implements ApplicationListener {

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MethodExecutionEvent) {
            if (MethodExecutionStatus.BEGIN.equals(((MethodExecutionEvent) event).getMethodExecutionStatus())) {
                System.out.println("It's beginning.....................");
            }
            if (MethodExecutionStatus.END.equals(((MethodExecutionEvent) event).getMethodExecutionStatus())) {
                System.out.println("It's ending..................");
            }
        }
    }
}