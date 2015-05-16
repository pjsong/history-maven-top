package dao.dal.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import dao.XmlResource;
import dao.dal.RouteConfigDefault;
import dao.transaction.TransactionTemplateDAL;


/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class TransactionOperation {
    RouteConfigDefault routeConfigDefault;
    
    private DataSource defaultDataSource;
    @Autowired
    private XmlResource xmlResource;
    
    protected void parseResource() {
//        xmlResource.parseResource();
    }

    protected String getSQL(String sqlId) {
        return xmlResource.getSQL(sqlId);
    }
    
    protected DataSource routeDataSource(RouteConfigDefault routeConfig, Object parameter) {
        if (routeConfig != null) {
            DataSource routeDataSource = routeConfig.route(parameter);
            if (routeDataSource != null) {
                return routeDataSource;
            }
        }
        return defaultDataSource;
    }
    
    protected DataSource routeDataSource_1(RouteConfigDefault routeConfig, Object parameter) {
        if (routeConfig != null) {
            DataSource routeDataSource = routeConfig.route_1(parameter);
            if (routeDataSource != null) {
                return routeDataSource;
            }
        }
        return defaultDataSource;
    }
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param param
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public TransactionTemplateDAL getTransactionTemplate(Object param) {
        DataSource dataSource = routeDataSource(routeConfigDefault, param);
        return new TransactionTemplateDAL(dataSource);
    }

    public XmlResource getXmlResource() {
        return xmlResource;
    }

    public void setXmlResource(XmlResource xmlResource) {
        this.xmlResource = xmlResource;
    }

    public RouteConfigDefault getRouteConfigDefault() {
        return routeConfigDefault;
    }

    public void setRouteConfigDefault(RouteConfigDefault routeConfigDefault) {
        this.routeConfigDefault = routeConfigDefault;
    }
    
}
