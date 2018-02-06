package com.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangshanmin on 2016/2/2.
 */


/**
 * 在业务处理器处理请求之前被调用
 * 如果返回false
 *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
 * 如果返回true
 *    执行下一个拦截器,直到所有的拦截器都执行完毕
 *    再执行被拦截的Controller
 *    然后进入拦截器链,
 *    从最后一个拦截器往回执行所有的postHandle()
 *    接着再从最后一个拦截器往回执行所有的afterCompletion()
 */

public class TestInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("局部拦截器....");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
