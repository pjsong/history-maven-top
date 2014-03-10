package site.netease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import BaseDAO.BaseTest;
@ContextConfiguration(locations = { "/spring/bean.xml" })
public class NetEaseHistoryReadTest extends BaseTest{
    @Autowired
    NetEaseHistoryRead netEaseHistoryRead;

    //
    @Test
    public void getParameter() {
        netEaseHistoryRead.writeCodes("sz00", "00");
    }

//    @Test
    public void writeTextTable() {
        throw new RuntimeException("Test not implemented");
    }
}
