package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import environment.BaseTest;

public class XmlResourceTest extends BaseTest{
  @Autowired
  XmlResource xr;
  @Test
  public void getSQL() {
    String sql = xr.getSQL("order.query_posOrderId_by_omsOrderItemId");
    System.out.println(sql);
  }

  @Test
  public void parseResource() {
//    throw new RuntimeException("Test not implemented");
  }
}
