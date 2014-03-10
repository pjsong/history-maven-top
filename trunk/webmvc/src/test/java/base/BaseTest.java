/**
 *
 */
package base;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;

/**
 * 单元测试基类，spring配置文件为oms-integration-test.xml，该文件包含现有�?��的bean配置文件�?可根据自己的�?��去修改bean的配置文件�?
 * 
 */
@ContextConfiguration(locations = { "classpath:conf/spring/base.xml" })
@TransactionConfiguration(defaultRollback = false)
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Test
    public void test() {

    }

}
