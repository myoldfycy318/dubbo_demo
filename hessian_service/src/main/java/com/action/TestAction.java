package com.action;


import com.comm.utils.RedisUtil;
import com.dao.student.dao.StudentDAO;
import com.dao.teacher.dao.TeacherDAO;
import com.mq.producer.queue.MessageProducer;
import com.mq.producer.topic.TopicProducer;
import com.mq.producer.topic.TopicSender;
import com.pojo.convert.ConvertOrder;
import com.pojo.student.Student;
import com.pojo.teacher.Teacher;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshanmin on 2016/2/4.
 */
@Controller("testAction")
@RequestMapping("/test")
public class TestAction {

    //@Resource(name = "topicSender")
    private TopicSender topicSender;

    //@Resource(name = "messageProducer")
    private MessageProducer messageProducer;

    //@Resource(name = "topicProducer")
    private TopicProducer topicProducer;

    @Resource(name = "teacherDAO")
    private TeacherDAO teacherDAO;


    @Resource(name = "studentDAO")
    private StudentDAO studentDAO;

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    private int count = 1;

    @ResponseBody
    @RequestMapping("/list")
    public List<String> testList() {

        List<String> list = new ArrayList<String>() {
            {
                add("aa");
                add("bb");
                add("cc");
            }
        };

        return list;
    }

    /**
     * 发送消息到主题
     * Topic主题 ：放入一个消息，所有订阅者都会收到
     * 这个是主题目的地是一对多的
     *
     * @param message
     * @return String
     */
    @ResponseBody
    @RequestMapping("/topicSender")
    public String topicSender(@RequestParam("message") String message) {
        String opt = "";
        String destinationName = "test.spring.topic";
        try {
            topicSender.setTopicMsg(destinationName, message);
            opt = "suc";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }
        return opt;
    }

    @ResponseBody
    @RequestMapping("/senderQueue")
    public String senderQueue() {
        try {
            ConvertOrder co = new ConvertOrder();
            co.setUserId(11111);
            co.setOrderNum("abc321");
            co.setStatus(0);
            co.setWaitAudit("true");
            co.setCredits("20");
            co.setType("appliy");
            co.setFacePrice("100");
            co.setActualPrice("50");
            co.setIp("192.0.0.1");
            co.setCrateTime(new Date());
            messageProducer.sendMsg(co);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/senderTopic")
    public String senderTopic() {
        try {
            ConvertOrder co = new ConvertOrder();
            co.setUserId(11111);
            co.setOrderNum("abc321");
            co.setStatus(0);
            co.setWaitAudit("true");
            co.setCredits("20");
            co.setType("appliy");
            co.setFacePrice("100");
            co.setActualPrice("50");
            co.setIp("192.0.0.1");
            co.setCrateTime(new Date());
            topicProducer.sendMsg(co);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/teacherList")
    public List<Teacher> getTeacherByid() {

        Teacher teacher = new Teacher();
        teacher.setId(1);
        List<Teacher> teachers = teacherDAO.selectTeacherById(teacher);
        return teachers;
    }

    @ResponseBody
    @RequestMapping("/getStudnet")
    public Student getStudnet() {

//       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Assert.notNull(authentication, "authentication is null");
//        System.out.println("Principal="+authentication.getPrincipal()+",name="+authentication.getName());
//        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(authentication));
//        System.out.println("authorities ="+ JSONObject.toJSONString(authentication.getAuthorities()));
//        System.out.println("Thread.nmae="+Thread.currentThread());
//        System.out.println("count="+count++);

        redisUtil.setex("hessian_service.getStudent",60,"test");
        Student student = new Student();
        student.setId(1);
        return studentDAO.selectStuById(student);
    }



     @ResponseBody
    @RequestMapping("/selectStuAndProfile")
    public Student getStuAndProfile() {

        Student student = new Student();
        student.setId(1);
        return studentDAO.selectStuAndProfile(student);
    }

    @ResponseBody
    @RequestMapping("/addStudnet")
    public void addStudnet(HttpServletRequest reques) {
        Map map = reques.getParameterMap();
        System.out.println("requestParameterMap="+ com.alibaba.fastjson.JSONObject.toJSONString(map));
        Student student = new Student();
        try {
            BeanUtils.populate(student, map);

            System.out.println("student="+student);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/loginDetail")
    public ModelAndView index(){
//        ModelAndView model = new ModelAndView("/exception/exception");
        ModelAndView model = new ModelAndView("/loginDetail");
        return model;
    }

    @RequestMapping("/exception")
    public ModelAndView exception(){
        ModelAndView model = new ModelAndView("/exception/exception");
        return model;
    }
}
