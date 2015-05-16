package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import bean.Ordi;
import ch.qos.logback.classic.LoggerContext;
import environment.BaseTest;

public class EntityParserTest extends BaseTest{
    
  EntityParser ep = EntityParser.getEntityParser(Ordi.class);
  HashMap mapParam = new HashMap<String, Object>();
  Logger logger = LoggerFactory.getLogger(getClass());
  @Test
    public void getDelete() {
        System.out.println((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.debug(ep.getDelete());
    }

  @Test
  public void getInsertBatch(){
	  List list = new ArrayList<Ordi>();
	  for(int i=0;i<4;i++){
		  Ordi ordi = new Ordi();
		  ordi.setOrderId("1111111");
		  ordi.setAbBankFlag("x");
		  ordi.setActiveFlag(1);
		  list.add(ordi);
	  }
	  EntityParser epList = EntityParser.getEntityListParser(Ordi.class, list);
	  logger.debug(epList.getBatchInsert());
  }
  
  @Test
  public void getDynamicUpdate() {
      mapParam.clear();
      mapParam.put("casherId", "xxx");
      logger.debug(ep.getDynamicUpdate(mapParam));
  }

//  @Test
  public void getId() {
      logger.debug(ep.getId());
  }

//  @Test
  public void getInsert() {
      logger.debug(ep.getInsert());
  }

//  @Test
  public void getSelect() {
      logger.debug(ep.getSelect());
  }

//  @Test
  public void getSelectAll() {
      logger.debug(ep.getSelectAll());
  }

//  @Test
  public void getUpdate() {
      logger.debug(ep.getUpdate());
  }

//  @Test
//  public void isTransient() {
//      Field[] fields = Ordi.class.getDeclaredFields();
//      Method method = Ordi.class.getMethods()[0];
//      logger.debug(ep.isTransient(fields, BeanUtils.findPropertyForMethod(method).getName()));
//  }
}
