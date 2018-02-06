package com.lv.event;

import com.comm.constant.MethodExecutionStatus;
import org.springframework.context.ApplicationEvent;

/**
 * MethodExecutionEvent
 *
 * @author Zhang ShanMin
 * @date 2017/1/13
 * @time 15:33
 */
public class MethodExecutionEvent extends ApplicationEvent {

    private static final long serialVersionUID = 2565706247851725694L;
    private String methodName;
    private MethodExecutionStatus methodExecutionStatus;

    public MethodExecutionEvent(Object source) {
        super(source);
    }

    public MethodExecutionEvent(Object source, String methodName, MethodExecutionStatus methodExecutionStatus) {
        super(source);
        this.methodName = methodName;
        this.methodExecutionStatus = methodExecutionStatus;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public MethodExecutionStatus getMethodExecutionStatus() {
        return methodExecutionStatus;
    }

    public void setMethodExecutionStatus(MethodExecutionStatus methodExecutionStatus) {
        this.methodExecutionStatus = methodExecutionStatus;
    }
}