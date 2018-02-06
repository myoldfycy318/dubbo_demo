package com.exception;

import com.alibaba.fastjson.JSONObject;
import com.pojo.student.Student;
import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * springmvc 统一异常处理
 * ExceptionHandler
 *
 * @author Zhang ShanMin
 * @date 2016/4/15
 * @time 11:23
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        OutputStream outputStream = null;
        if (e != null) {
            System.out.println("RequestURI="+httpServletRequest.getRequestURI());
            System.out.println("异常报错,error=" + e);
            try {
                Student student = new Student();
                student.setMajor("爆粗");
                httpServletResponse.setContentType("text/html;charset=UTF-8");
                httpServletResponse.setCharacterEncoding("UTF-8");
                outputStream = httpServletResponse.getOutputStream();
                outputStream.write((JSONObject.toJSONString(student).getBytes("UTF-8")));
                outputStream.flush();
                outputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                IOUtils.closeQuietly(outputStream);
            }
        }

        return null;
    }
}
