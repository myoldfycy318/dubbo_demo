package com.lv.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.annotation.ApiMethod;
import com.comm.util.RSACoder;
import com.github.pagehelper.Page;
import com.lv.model.User;
import com.lv.service.user.IUserService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserAction
 *
 * @author Zhang ShanMin
 * @date 2016/3/28
 * @time 12:06
 */
@Controller
@RequestMapping("/user")
public class UserAction {


    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "topicaAmqpTemplate")
    private AmqpTemplate topicaAmqpTemplate;

    @Resource(name = "directAmqpTemplate")
    private AmqpTemplate directAmqpTemplate;

    @Resource(name = "sdkAmqpTemplate")
    private AmqpTemplate sdkAmqpTemplate;

    @Resource(name = "fanoutAmqpTemplate")
    private AmqpTemplate fanoutAmqpTemplate;

    @Autowired
    private AmqpTemplate delayMsgTemplate;

    private int count = 1;


    @ResponseBody
    @RequestMapping("/userList")
    @ApiMethod(limitCount = 10, time = 60)
    public List<User> getUsers(HttpServletRequest request) {
        String path = request.getContextPath();
        String uri = request.getRequestURI();
        uri = uri.substring(path.length(), uri.length());
        System.out.println("uri=" + uri);
        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        List<User> users = userService.getUsersByPage(Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
//        System.out.println(JSONObject.toJSON(users));
//        System.out.println("1111="+request.getRemoteUser());
//        System.out.println("2222.username=" +AssertionHolder.getAssertion().getPrincipal().getName());
        System.out.println("Tread.name=" + Thread.currentThread());
        System.out.println("count=" + count++);
        return users;
    }

    @ResponseBody
    @RequestMapping("/userListByPage")
    public List<User> getUsersByPage(HttpServletRequest request) {
        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        Page<User> userPage = userService.queryUsersByPage(Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
        System.out.println("total=" + userPage.getTotal());
        System.out.println(JSONObject.toJSON(userPage));
        return userPage;
    }

    @ResponseBody
    @RequestMapping("/exception")
    @ApiMethod(limitCount = 10, time = 60)
    public List<User> getUsersException(HttpServletRequest request) {
        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        List<User> users = userService.getUsersByPage(Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
        int i = 1 / 0;
        return users;
    }

    /**
     * rabbit:topic-exchange 验证
     */
    @ResponseBody
    @RequestMapping("/sendMqTopicMsg")
    public void testRabbitMqSendMsg(HttpServletRequest request) {
        RequestContextUtils.getWebApplicationContext(request);
        String routeKey = "route.key";
        User user = new User();
        user.setUsername("张叁");
        user.setPassword("123");
        user.setEmail("123@126.com");
        topicaAmqpTemplate.convertAndSend(routeKey, user);
    }

    /**
     * rabbit:direct-exchange 验证
     */
    @ResponseBody
    @RequestMapping("/sendMqDirectMsg")
    public String testDirectExchangSendMsg() {
        User user = new User();
        user.setUsername("张叁");
        user.setPassword("123");
        user.setEmail("123@126.com");
        directAmqpTemplate.convertAndSend(user);
        //directAmqpTemplate.convertAndSend("spring.queue.tag.key",user);
        return "ok";
    }

    /**
     * rabbit:fanout-exchange 验证
     */
    @ResponseBody
    @RequestMapping("/sendFanoutMsg")
    public String testFanoutExchangSendMsg() {
        User user2 = new User();
        user2.setUsername("张叁");
        user2.setPassword("123");
        user2.setEmail("123@126.com");
        fanoutAmqpTemplate.convertAndSend(user2);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/sendSdkMsg")
    public String sendSdkMsg() {
        User user = new User();
        user.setUsername("张叁");
        user.setPassword("123");
        user.setEmail("123@126.com");
        sdkAmqpTemplate.convertAndSend("sdk_pay_trans_queue_key", user);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/testRestful/{id}")
    public String testRestful(@PathVariable long id) {
        System.out.println("testRestful.id=" + id);
        return id + "";
    }


    @ResponseBody
    @RequestMapping("/testRestfulStr/{platformCode}")
    public String testRestfulStr(@PathVariable String platformCode) {
        System.out.println("platformCode=" + platformCode);
        return platformCode;
    }

    @RequestMapping(value = "merchantAccept")
    @ResponseBody
    public void testMerchantAccept(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> requestParams = request.getParameterMap();
        System.out.println(JSON.toJSONString(requestParams));
        Map<String, Object> map = new HashMap<String, Object>();

        String responseCode = request.getParameter("responseCode");
        String errorCode = request.getParameter("errorCode");
        JSONObject jsonObject = JSONObject.parseObject(request.getParameter("data"));
        String orderNo = jsonObject.getString("orderNo");
        String sdkflowId = jsonObject.getString("sdkflowId");
        String signCode = request.getParameter("signCode");
        StringBuffer sb = new StringBuffer();
        sb.append("responseCode=").append(responseCode).append(",")
                .append("errorCode=").append(StringUtils.isBlank(errorCode) ? "\"\"" : errorCode).append(",")
                .append("sdkflowId=").append(sdkflowId).append(",")
                .append("orderNo=").append(orderNo);
        String merchantRsaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJZQGiszkhEUWpenPj/yL49m+wuWsf2Ll3HH70bjVYco+vFAQjnJqcO/469NlRcdds5Vm/9+HRCDAudXLlhGUz8I4dYXAih8lvZxNs7AEdWQqvmovE4eLXA1o96i9neGTCVHAN/vRjMoJPk+aOv0mQyPGaLwuVIZnJrTn1/qsiRwIDAQAB";
        boolean result = RSACoder.verify(sb.toString().getBytes(), merchantRsaPublicKey, signCode);
        if (result) {
            System.out.println("ok");
            map.put("isSuccess", "true");
            response.getWriter().print(new JSONObject(map));
            return;
        }
        response.getWriter().print(new JSONObject(map));
    }

    @RequestMapping(value = "appceptReq")
    @ResponseBody
    public String appceptReq(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> requestParams = request.getParameterMap();
        System.out.println(JSON.toJSONString(requestParams));
        return JSON.toJSONString(requestParams);


    }

    @RequestMapping(value = "resp")
    @ResponseBody
    public void resp(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter printWriter = null;
        try {
            String result = "{\"isSuccess\":\"true\"}";
            printWriter = response.getWriter();
            printWriter.print(result);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "bind")
    public ModelAndView testDataBind(User user) {
        ModelAndView modelAndView = new ModelAndView("/user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "delayMsg")
    @ResponseBody
    public String delayMsg() {
        final int xdelay = 10;
        User user = new User();
        user.setId(1);
        user.setEmail("xxx@123.com");
        user.setPassword("12321");
        user.setUsername("张三");
        System.out.println("当前时间：" + com.comm.util.DateUtils.getCurDateFormatStr("yyyy-MM-dd HH:mm:ss"));
        //发送延迟消息
        delayMsgTemplate.convertAndSend("order.delay.notify", user,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message)
                            throws AmqpException {
                        //设置延迟时间（5分钟后执行）
                        message.getMessageProperties().setDelay(xdelay);
                        return message;
                    }
                });
        return "ok";
    }
}
