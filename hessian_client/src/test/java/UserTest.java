import com.alibaba.fastjson.JSONObject;
import com.lv.model.User;
import com.lv.service.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangshanmin on 2016/2/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-spring-beans.xml")
public class UserTest {

    @Resource(name = "userService")
    private IUserService userService;

    @Test
    public void addUser(){
        User user = new User();
        user.setUsername("张叁");
        user.setPassword("123");
        user.setEmail("123@126.com");
        userService.addUser(user);
    }

    @Test
    public void getUsers(){
        List<User> users = userService.getUsers();
        System.out.println(JSONObject.toJSON(users));
    }
}
