package site.yahoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import BaseDAO.BaseTest;
@ContextConfiguration(locations = { "/spring/bean.xml" })
public class YahooHistoryReadTest extends BaseTest{
    @Autowired
    YahooHistoryRead reader;

    //
    @Test
    public void getParameter() {
        reader.writeCodes("sz00", "00");
    }

//    @Test
    public void writeTextTable() {
        throw new RuntimeException("Test not implemented");
    }
}
