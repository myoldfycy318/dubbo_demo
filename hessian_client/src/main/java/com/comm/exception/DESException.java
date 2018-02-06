package com.comm.exception;

/**
 * Created by chenwei on 2016/5/13
 */
public class DESException extends Exception{

    public DESException() {
    }

    public DESException(String message) {
        super(message);
    }

    public DESException(String message, Exception e) {
        super(message,e);
    }

}
