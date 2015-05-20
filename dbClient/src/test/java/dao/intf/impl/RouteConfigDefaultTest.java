package dao.intf.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import com.googlecode.aviator.AviatorEvaluator;

import constants.DbInstanceId;


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
public class RouteConfigDefaultTest {

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
    public DataSource route(Object parameter) {
        if (rules == null || parameter == null || rules.size() == 0 || !(parameter instanceof DbInstanceId)) {
            return null;
        }
//        Object[] params = null;
//        if (parameter instanceof Object[]) {
//            params = (Object[]) parameter;
//        } else {
//            params = new Object[] { parameter };
//        }
//        for (Object param : params) {
//            Map<String, Object> env = new HashMap<String, Object>();
//            env.put(ROUTE, ((DbInstanceId)param).getDbInstanceId());

            Iterator<Map.Entry<String, DataSource>> it = rules.entrySet().iterator();
            while (it.hasNext()) {
                try {
                    Map.Entry<String, DataSource> entry = it.next();
                    String rule = entry.getKey();
                    // rule is like: '${route.omsCategoryID}'=='01'
                    // <entry key="'${route.omsCategoryID}'=='publicDB'" value-ref="dataSourcePublic" />
//                    String expression = FreeMakerParser.process(rule, env);
                    // http://code.google.com/p/aviator/
//                    if ((Boolean) AviatorEvaluator.execute(expression, null)) {
                    if (rule.equals(((DbInstanceId)parameter).getDbInstanceId())) {
                        DataSource dataSource = entry.getValue();
                        return dataSource;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
//        }

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

    public static void main(String[] args) {
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("email", "killme2008@gmail.com");
        String username = (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1:'unknow'", env);
        System.out.println(username);// killme2008
        System.out.println(AviatorEvaluator.execute("'01'=='01'", null).toString());// true
    }
}
