import org.junit.Test;

/**
 * Created by zhangshanmin on 2016/3/16.
 */
public class TestLoadFile {

    @Test
    public void testloadFile(){
     String path = TestLoadFile.class.getResource("/").getFile();

    }
}
