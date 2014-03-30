package site.netease;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import BaseDAO.BaseTest;

public class WriterHistoryNetEaseTest extends BaseTest{
    @Autowired
    WriterHistoryNetease writerHistoryNetease;
  @Test
  public void doHistory() throws IOException {
      writerHistoryNetease.doHistoryCode("sz002121", "00");
  }
}
