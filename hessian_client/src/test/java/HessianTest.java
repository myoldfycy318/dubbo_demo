import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.service.AddService;

/**
 * Created with IntelliJ IDEA.
 * IUserService: zhangshanmin
 * Date: 15-11-23
 * Time: 下午5:55
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-spring-beans.xml")
public class HessianTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private AddService addSer;
    @org.junit.Test
    public void  testHessian(){
        System.out.println("result:"+addSer.add(1,2));
    }
}
