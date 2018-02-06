import com.alibaba.fastjson.JSONObject;
import com.lv.model.User;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * TestDataBinder
 *
 * @author Zhang ShanMin
 * @date 2016/12/11
 * @time 18:26
 */
public class TestDataBinder {

    @Test
    public void testDataBinder(){

        User user = new User();
        BeanWrapper bw = new BeanWrapperImpl(user);
        bw.setPropertyValue("good", "on");
//bw.setPropertyValue("good", "1");
//bw.setPropertyValue("good", "true");
//bw.setPropertyValue("good", "yes");
        System.out.println(JSONObject.toJSONString(user));
    }
}
