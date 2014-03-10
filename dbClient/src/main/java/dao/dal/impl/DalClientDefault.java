package dao.dal.impl;

import org.springframework.beans.factory.InitializingBean;

import dao.dal.DalClient;

public class DalClientDefault extends DataBaseOperation implements DalClient, InitializingBean {
    public void afterPropertiesSet() throws Exception {
        parseResource();
    }
}
