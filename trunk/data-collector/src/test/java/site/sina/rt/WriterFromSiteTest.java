package site.sina.rt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import site.yahoo.YahooHistoryRead;
import BaseDAO.BaseTest;
@ContextConfiguration(locations = { "/spring/bean.xml" })
public class WriterFromSiteTest extends BaseTest {
	@Autowired
	WriterFromSite writer;
	@Test
	public void doCollect() throws IOException {
	    writer.doCollect("sh600", "00");
	}
}
