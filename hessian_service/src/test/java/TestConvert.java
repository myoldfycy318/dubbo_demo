import com.action.TestAction;
import com.alibaba.fastjson.JSON;
import com.dao.convert.dao.ConvertOrderDAO;
import com.dao.teacher.dao.TeacherDAO;
import com.pojo.convert.ConvertOrder;
import com.pojo.teacher.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.jar.Attributes;

/**
 * Created by zhangshanmin on 2015/12/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-spring-beans.xml",
        "classpath*:spring/service-servlet.xml" })
public class TestConvert {

    @Resource(name = "convertOrderDao")
    private ConvertOrderDAO convertOrderDao;

    @Resource(name = "testAction")
    private TestAction testAction;

    @Resource(name = "teacherDAO")
    private TeacherDAO teacherDAO;

    @Test
    public void testSave(){

        ConvertOrder co = new ConvertOrder();
        co.setUserId(111);
        co.setOrderNum("abc1");
        co.setStatus(0);
        co.setWaitAudit("true");
        co.setCredits("20");
        co.setType("appliy");
        co.setFacePrice("100");
        co.setActualPrice("50");
        co.setIp("192.0.0.1");
        co.setCrateTime(new Date());
		System.out.println("test:"+convertOrderDao.insertSelective(co));
        System.out.println("id="+co.getId());
    }

    @Test
    public void testSelect(){
        ConvertOrder convertOrder = new ConvertOrder();
        convertOrder.setOrderNum("abc123");
        convertOrder = convertOrderDao.getOrderByOrderNum(convertOrder);
        System.out.println(JSON.toJSON(convertOrder));

    }

    @Test
    public void testMybatisQueryMap(){
       List<Map> list = convertOrderDao.queryMap();
        System.out.println(list.size());
        for (Map idsMap : list) {
				Iterator iter = idsMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
                    System.out.println("key="+entry.getKey()+",val="+entry.getValue());
                }

			}
    }

    @Test
    public void testGetJson(){
        System.out.println(testAction.testList());
    }

    @Test
    public void testTeacher(){
        Teacher teacher = new Teacher();
        teacher.setId(1);
       List<Teacher> teachers = teacherDAO.selectTeacherById(teacher);
        System.out.println("teachers:\n"+JSON.toJSON(teachers));
    }
}
