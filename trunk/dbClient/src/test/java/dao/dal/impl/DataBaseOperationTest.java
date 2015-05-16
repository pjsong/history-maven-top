package dao.dal.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import bean.CodeList;
import dao.dal.DalClient;
import environment.BaseTest;

public class DataBaseOperationTest extends BaseTest {

	@Autowired
	DalClient dalClient;

	@Test
	public void batchInsert() {
		List list = new ArrayList<CodeList>();
		for (int i = 0; i < 4; i++) {
			CodeList cl = new CodeList();
			cl.setCategory(i+"");
			cl.setCode("222"+i);
			cl.setDbInstanceId("00");
			cl.setName("name"+i);
			list.add(cl);
		}
		// EntityParser epList =
		// EntityParser.getEntityListParser(CodeList.class,
		// list);
//		dalClient.persist(list.get(0));
		dalClient.persistList(list.get(0), list);
	}

	@Test
	public void batchUpdate() {
	}

	@Test
	public void dynamicMerge() {
	}

	@Test
	public void executeStringMapString() {
	}

	@Test
	public void executeStringObject() {
	}

	@Test
	public void find() {
	}

	@Test
	public void getJdbcTemplate() {
	}

	@Test
	public void getValidateJdbcTemplate() {
	}

	@Test
	public void logMessage() {
	}

	@Test
	public void mapIfNull() {
	}

	@Test
	public void merge() {

	}

	@Test
	public void persistObject() {
//		List list = new ArrayList<CodeList>();
//		for (int i = 0; i < 4; i++) {
//			CodeList cl = new CodeList();
//			cl.setCategory("cat");
//			cl.setCode("222");
//			cl.setDbInstanceId("00");
//			cl.setName("name");
//			list.add(cl);
//		}
//		// EntityParser epList =
//		// EntityParser.getEntityListParser(CodeList.class,
//		// list);
//		dalClient.persistList(CodeList.class, list);
	}

	@Test
	public void persistObjectClassT() {

	}

	@Test
	public void queryForListStringMapStringClassT() {

	}

	@Test
	public void queryForListStringObjectClassT() {

	}

	@Test
	public void queryForListStringMapString() {

	}

	@Test
	public void queryForListStringObject() {

	}

	@Test
	public void queryForMapStringMapString() {

	}

	@Test
	public void queryForMapStringObject() {

	}

	@Test
	public void queryForObjectStringMapStringClassT() {

	}

	@Test
	public void queryForObjectStringObjectClassT() {

	}

	@Test
	public void remove() {

	}

	@Test
	public void singleResult() {

	}
}
