package com.service;

import org.springframework.stereotype.Service;

/**
 * Created by zhangshanmin on 2015/12/24.
 */
@Service("helloDubbo")
public class HelloDubboImpl implements HelloDubbo {

    @Override
    public String sayDubbo(String content) {
        return "helloDubbo-->"+content;
    }
}
