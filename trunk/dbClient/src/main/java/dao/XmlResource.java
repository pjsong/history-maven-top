package dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import dao.util.XmlParser;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class XmlResource implements InitializingBean{

    private Map<String, String> sqlContainer = new HashMap<String, String>();
    private Resource[] resources;


    public void parseResource() {
        XmlParser.getInstance().parse(resources, sqlContainer);
    }

    public String getSQL(String sqlId) {
        String sql = sqlContainer.get(sqlId);
        if(!StringUtils.hasLength(sql)){
            throw new RuntimeException("SQL must be not null.");
        }
        return sql;
    }


    public void setResources(Resource[] resources) {
        this.resources = resources;
    }

    public void afterPropertiesSet() throws Exception {
        parseResource();
    }
}