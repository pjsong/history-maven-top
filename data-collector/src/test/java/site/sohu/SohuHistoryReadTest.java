package site.sohu;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import BaseDAO.BaseTest;


public class SohuHistoryReadTest extends BaseTest{
    @Autowired
    SohuHistoryRead reader;
    @Test
    public void getParameter() {
        reader.writeCodes("sz00", "00");
    }
}
