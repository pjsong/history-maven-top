package dao;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class FreeMakerParser {
    private static final String DEFAULT_TEMPLATE_KEY = "default_template_key";
    private static final String DEFAULT_TEMPLATE_NAME = "default_template_expression";
    private static final Configuration configurer = new Configuration();
    /**
     * 配置SQL表达式缓存
     */
    private static Map<String, Template> templateCache = new HashMap<String, Template>();
    /**
     * 分库表达式缓存
     */
    private static Map<String, Template> expressionCache = new HashMap<String, Template>();

    /**
     * 功能描述: <br>
     * 处理分库
     *
     * @param expression
     * @param root
     * @return
     * @throws Exception 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String process(String expression, Map<String, ?> root) throws Exception {
        StringReader reader = null;
        StringWriter out = null;
        Template template = null;
        template = expressionCache.get(expression);
        if (template == null) {
            reader = new StringReader(expression);
            template = new Template(DEFAULT_TEMPLATE_NAME, reader, configurer);
            expressionCache.put(expression, template);
        }
        out = new StringWriter();
        template.setNumberFormat("#");
        template.process(root, out);
        return out.toString();
    }

    /**
     * 功能描述: <br>
     * 处理sql表达式
     *
     * @param root
     * @param sql
     * @param sqlId
     * @return
     * @throws Exception 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String proccess(Map<String, ?> root, String sql, String sqlId) {
        StringReader reader = null;
        StringWriter out = null;
        Template template = null;
        template = templateCache.get(sqlId);
        if (template == null) {
            reader = new StringReader(sql);
            try {
                template = new Template(DEFAULT_TEMPLATE_KEY, reader, configurer);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            templateCache.put(sqlId, template);
        }
        out = new StringWriter();
        try {
            template.process(root, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
