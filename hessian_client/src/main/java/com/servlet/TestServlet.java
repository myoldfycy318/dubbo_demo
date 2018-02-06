package com.servlet;

import com.comm.SpringBeanProxy;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhangshanmin on 2016/2/4.
 */
public class TestServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//       HelloDubbo helloDubbo = (HelloDubbo)SpringBeanProxy.getBean("helloDubbo");
//       System.out.println(helloDubbo.sayDubbo("啦啦啦"));
    }
}
