import com.mq.producer.queue.QueueSender;
import com.mq.producer.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zhangshanmin on 2016/2/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-spring-beans.xml" })
public class TestMQ {

    @Resource(name = "queueSender")
    private QueueSender queueSender;

    @Resource(name = "topicSender")
    private TopicSender topicSender;


    @Test
    public void testMQQueue(){
     queueSender.sendMsg("test.spring.queue","testSpringMQ");
    }

    @Test
    public void testMQTopic(){
        String destinationName ="test.spring.topic";
        topicSender.setTopicMsg(destinationName,"topic");
    }

}
