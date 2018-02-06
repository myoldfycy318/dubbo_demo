import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.HelloDubbo;

import javax.annotation.Resource;

/**
 * Created by zhangshanmin on 2015/12/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-spring-beans.xml")
public class DubboTest {

   @Resource
    private HelloDubbo helloDubbo;

    @Test
    public void testHelloDubbo(){
        System.out.println(helloDubbo.sayDubbo("啦啦啦"));
    }
}
