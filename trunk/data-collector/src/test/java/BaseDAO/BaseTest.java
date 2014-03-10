package BaseDAO;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
@ContextConfiguration(locations = { "/spring/bean.xml" })
public class BaseTest extends AbstractTestNGSpringContextTests{
//    @Autowired
//    WriterFromSite writerFromSite;
//    private ApplicationContext context;
//
//    @Test
//    public void f() throws IOException {
//        context = new ClassPathXmlApplicationContext(new String[] { "spring/bean.xml" });
//        WriterFromSite cust = (WriterFromSite) context.getBean("writerFromSite");
//        cust.doCollect("xx");
//        System.out.println("xxx");
//    }
}
