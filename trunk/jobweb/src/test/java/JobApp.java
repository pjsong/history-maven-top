import org.springframework.context.support.ClassPathXmlApplicationContext;

import base.BaseTest;


public class JobApp extends BaseTest
{
    public static void main( String[] args ) throws Exception
    {
        new ClassPathXmlApplicationContext("conf/spring/application-quartz.xml");
    }
}
