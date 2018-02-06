package com.aop;

import com.alibaba.fastjson.JSONObject;
import com.annotation.ApiMethod;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.MessageFormat;


@Component
@Aspect
@Order(3)//执行权重 最小最先执行
public class ApiMethodIntercept implements ApplicationContextAware, InitializingBean {
    private final Logger logger = Logger.getLogger(this.getClass());

    private ApplicationContext applicationContext;

//    @Pointcut("@annotation(com.annotation.ApiMethod)")
//    public void serviceAccess() {}

    @Before(value = "@annotation(com.annotation.ApiMethod)")
    public void doBefore(JoinPoint joinPoint) {
//        applicationContext.getResource("conf/jdbc.properties")
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        System.out.println("method.name=" + method.getName());
        ApiMethod apimethod = method.getAnnotation(ApiMethod.class);
        System.out.println(".....doBefore@annotation="+apimethod);
        System.out.println("method.annotations:" + JSONObject.toJSONString(method.getAnnotations()));
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            if (args[0] instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
            }
        }
        //获取方法注解 Annotation
        ApiMethod apiMethod = method.getAnnotation(ApiMethod.class);
        System.out.println("apiMethod.annotation=" + JSONObject.toJSONString(apiMethod.limitCount()));
    }

    /**
     * 环绕处理
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("within(@org.springframework.stereotype.Controller *) &&@annotation(com.annotation.ApiMethod)")
    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("-----aop arround 22222--------");
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();
        ApiMethod apimethod = method.getAnnotation(ApiMethod.class);
        System.out.println("@annotation="+apimethod);
        Object[] args = pjp.getArgs();
        int limitCount = apimethod.limitCount();
        long time = apimethod.time();
        if (args != null && args.length > 0) {
//			if(args[0] instanceof RopRequestBody){
//			}
        }
        //目标对象返回值
        Object o = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.info(MessageFormat.format("Method=[{0}],Cost=[{1}]", pjp.getSignature().getName(), endTime - startTime));
        System.out.println("after invoke Object:" + pjp.getTarget() + ",Method:" + pjp.getSignature().getName());
        return o;
    }

    @AfterThrowing(value = "@annotation(com.annotation.ApiMethod)", throwing = "ex")
    public void doThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("method " + jp.getTarget().getClass().getName()
                + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.applicationContext,
                "applicationContext can not null");
    }
}
