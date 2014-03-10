package dao.util;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;


/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class XmlParser {
    private static final Logger logger = LoggerFactory.getLogger(XmlParser.class);

    private Map<String, String> sqlMapResult = new HashMap<String, String>();

    private static XmlParser parser = new XmlParser();

    private XmlParser() {
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static XmlParser getInstance() {
        return parser;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param resources
     * @param sqlContainer
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public synchronized void parse(Resource[] resources, Map<String, String> sqlContainer) {
        parseDocuments(createDocuments(resources));
        sqlContainer.putAll(sqlMapResult);
    }

    private Map<String, Document> createDocuments(Resource[] resources) {
        Map<String, Document> documents = new HashMap<String, Document>();

        if (resources != null && resources.length > 0) {
            SAXReader saxReader = new SAXReader();
            for (Resource resource : resources) {
                try {
                    Document doc = saxReader.read(resource.getInputStream());
                    documents.put(resource.getFilename(), doc);
                } catch (Exception e) {
                    logger.error("SAXReader parse sqlMap xml error!");
                    throw new RuntimeException(e);
                }
            }
        }
        return documents;
    }

    private void parseDocuments(Map<String, Document> documents) {
        try {
            Iterator<Map.Entry<String, Document>> it = documents.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Document> entry = it.next();
                logger.debug("Loadding sqlMap.xml :" + entry.getKey());
                parseDocument(entry.getKey(), entry.getValue().getRootElement());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseDocument(String fileName, Element rootElement) {
        if (rootElement != null) {
            String namespace = rootElement.attributeValue("namespace");
            if (namespace == null || "".equals(namespace)) {
                logger.debug("SqlMap Element must have namespace : " + fileName);
                throw new RuntimeException("SqlMap has no namespace : " + fileName);
            }
            List<Element> sqlElements = rootElement.elements();
            for (Element element : sqlElements) {
                String id = element.attributeValue("id");
                if (id == null || "".equals(id)) {
                    logger.debug("Sql Element must have id : " + fileName);
                    throw new RuntimeException("Sql Element must have id : " + fileName);
                }
                String sql = element.getTextTrim();
                sqlMapResult.put(appendSqlId(namespace, id), sql);
            }
        }
    }

    private String appendSqlId(String namespace, String id) {
        return new StringBuffer().append(namespace).append(".").append(id).toString();
    }
}
