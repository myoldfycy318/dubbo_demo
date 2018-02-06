import com.lv.event.MethodExecutionEventPublisher;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * SpringEventTest
 *
 * @author Zhang ShanMin
 * @date 2017/1/13
 * @time 15:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-spring-beans.xml")
public class SpringEventTest extends AbstractJUnit4SpringContextTests {


    @Resource(name = "evtPublisher")
    private MethodExecutionEventPublisher evtPublisher;

    @org.junit.Test
    public void testSpringEvent() {
        evtPublisher.methodToMonitor();
    }
}
