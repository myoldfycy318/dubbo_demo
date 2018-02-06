package com.lv.controller;

import com.service.HelloDubbo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * DoubboController
 *
 * @author Zhang ShanMin
 * @date 2018/1/10
 * @time 11:20
 */

@Controller
@RequestMapping("/doubbo")
public class DoubboController {

    @Resource
    private HelloDubbo helloDubbo;
    
    @ResponseBody
    @RequestMapping("/test")
    public String testDirectExchangSendMsg() {
        System.out.println(helloDubbo.sayDubbo("啦啦啦"));
        return "ok";
    }




}
