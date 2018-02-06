package com.lv.rabbitmq.comsumer;

import com.alibaba.fastjson.JSON;

/**
 * OrderConsumer
 *
 * @author Zhang ShanMin
 * @date 2017/3/28
 * @time 15:34
 */

//@Component("delayHandleConsumer")
public class DelayHandleConsumer  {

    public void delayHandle(Object obj){
        System.out.println("队列处理时间："+ com.comm.util.DateUtils.getCurDateFormatStr("yyyy-MM-dd HH:mm:ss"));
        System.out.println("队列内容："+ JSON.toJSONString(obj));
    }

}
