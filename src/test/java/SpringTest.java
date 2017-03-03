import com.wasu.accounting.config.RootConfig;
import com.wasu.accounting.service.impl.TestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by yangzh on 2017/2/27.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@WebAppConfiguration
public class SpringTest {

    @Autowired
    private TestServiceImpl testService;

    @Test
    public void test(){
        System.out.println(System.getProperty("test"));
    }
}
