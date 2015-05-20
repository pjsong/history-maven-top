package dao.dal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import com.googlecode.aviator.AviatorEvaluator;

import constants.DbInstanceId;
import dao.FreeMakerParser;

/**
 * 〈一句话功能简述〉<br>
 * <bean id="route" class="com.suning.framework.dal.route.support.DefaultRouteConfig"> 
 * <property name="rules"> <map>
 * <entry key="'${route.dbInstantceId}'=='publicDB'" value-ref="dataSourcePublic" /> 
 * <entry key="'${route.dbInstantceId}'=='00'" value-ref="dataSource1" /> 
 * <entry key="'${route.dbInstantceId}'=='01'" value-ref="dataSource2"/> 
 * </map> </property> 
 * </bean>
 * <entry key="publicDB" value-ref="dataSourcePublic" /> 
 * <entry key="00" value-ref="dataSource1" /> 
 * <entry key="01" value-ref="dataSource2"/> 
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RouteConfigDefault {

    private static final String ROUTE = "route";

    private Map<String, DataSource> rules = null;

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param parameter
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public DataSource route_1(Object parameter) {
        if (rules == null || parameter == null || rules.size() == 0 || !(parameter instanceof DbInstanceId)) {
            return null;
        }
        Iterator<Map.Entry<String, DataSource>> it = rules.entrySet().iterator();
        while (it.hasNext()) {
            try {
                Map.Entry<String, DataSource> entry = it.next();
                String rule = entry.getKey();
                if (rule.equals(((DbInstanceId) parameter).getDbInstanceId())) {
                    DataSource dataSource = entry.getValue();
                    return dataSource;
                }
            } catch (Exception e) {
                continue;
            }
        }

        return null;
    }
    public DataSource route(Object parameter) {
        if (rules != null && parameter != null) {
            Object[] params = parameter instanceof Object[] ? (Object[]) parameter:new Object[] { parameter };
            for (Object param : params) {
                Map<String, Object> env = new HashMap<String, Object>();
                env.put(ROUTE, param);
                Iterator<Map.Entry<String, DataSource>> it = rules.entrySet().iterator();
                while (it.hasNext()) {
                    try {
                        Map.Entry<String, DataSource> entry = it.next();
                        String rule = entry.getKey();
                        String expression = FreeMakerParser.process(rule, env);
                        if ((Boolean) AviatorEvaluator.execute(expression, null)) {
                            DataSource dataSource = entry.getValue();
                            return dataSource;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }

        return null;
    }
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param rules
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setRules(Map<String, DataSource> rules) {
        this.rules = rules;
    }
}
