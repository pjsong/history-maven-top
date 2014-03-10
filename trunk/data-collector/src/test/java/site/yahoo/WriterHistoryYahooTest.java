package site.yahoo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import BaseDAO.BaseTest;

public class WriterHistoryYahooTest extends BaseTest{
    @Autowired
    WriterHistoryYahoo writerHistoryYahoo;
  @Test
  public void doHistory() throws IOException {
      writerHistoryYahoo.doHistoryCode("sz002121", "00");
  }
}
