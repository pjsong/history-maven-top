package dao.dal.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import dao.EntityParser;
import dao.FreeMakerParser;
import dao.util.DataSourceContext;


/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DataBaseOperation extends TransactionOperation {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseOperation.class);
    
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
//    public Number persist(Object entity) {
//        return persist(entity, Number.class);
//    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param entity
     * @param requiredType
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @SuppressWarnings("unchecked")
    public <T> T persist(Object entity) {
        EntityParser sqlParser = EntityParser.getEntityParser(entity.getClass());
        String insertSQL = sqlParser.getInsert();
        Map<String, Object> paramMap = sqlParser.parser(entity);
        logMessage("persist", insertSQL, paramMap);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getValidateJdbcTemplate(paramMap).update(insertSQL, new MapSqlParameterSource(paramMap), keyHolder);
        Object key = paramMap.get(sqlParser.getId());
        if (key == null) {
            return (T) keyHolder.getKey();
        }
        return (T) key;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int merge(Object entity) {
        EntityParser sqlParser = EntityParser.getEntityParser(entity.getClass());
        String updateSql = sqlParser.getUpdate();
        Map<String, Object> paramMap = sqlParser.parser(entity);
        logMessage("merge", updateSql, paramMap);
        return getValidateJdbcTemplate(paramMap).update(updateSql, paramMap);
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int dynamicMerge(Object entity) {
        EntityParser sqlParser = EntityParser.getEntityParser(entity.getClass());

        Map<String, Object> paramMap = sqlParser.parser(entity);
        String updateSql = sqlParser.getDynamicUpdate(paramMap);
        logMessage("dynamicMerge", updateSql, paramMap);
        return getValidateJdbcTemplate(paramMap).update(updateSql, paramMap);
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int remove(Object entity) {
        EntityParser sqlParser = EntityParser.getEntityParser(entity.getClass());

        String removeSql = sqlParser.getDelete();
        Map<String, Object> paramMap = sqlParser.parser(entity);
        logMessage("remove", removeSql, paramMap);
        return getValidateJdbcTemplate(paramMap).update(removeSql, paramMap);
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param entityClass
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public <T> T find(Class<T> entityClass, Object entity) {
        EntityParser sqlParser = EntityParser.getEntityParser(entity.getClass());

        String findSql = sqlParser.getSelect();
        Map<String, Object> paramMap = sqlParser.parser(entity);
        logMessage("find", findSql, paramMap);
        List<T> resultList = getJdbcTemplate(paramMap).query(findSql, paramMap,
                new BeanPropertyRowMapper<T>(entityClass));
        return singleResult(resultList);
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param paramMap
     * @param requiredType
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public <T> T queryForObject(String sqlId, Map<String, ?> paramMap, Class<T> requiredType) {
        String sql = FreeMakerParser.proccess(paramMap, getSQL(sqlId), sqlId);
        logMessage("queryForObject", sql, paramMap);
        if (requiredType == Object.class || requiredType == String.class || Number.class.isAssignableFrom(requiredType)
                || Date.class.isAssignableFrom(requiredType)) {
            return getJdbcTemplate(paramMap).queryForObject(sql, paramMap, requiredType);
        }
        List<T> resultList = getJdbcTemplate(paramMap).query(sql, paramMap, new BeanPropertyRowMapper<T>(requiredType));
        return singleResult(resultList);
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param param
     * @param requiredType
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public <T> T queryForObject(String sqlId, Object param, Class<T> requiredType) {
        return this.queryForObject(sqlId, convertToMap(param), requiredType);
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param paramMap
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Map<String, Object> queryForMap(String sqlId, Map<String, ?> paramMap) {
        String sql = FreeMakerParser.proccess(paramMap, getSQL(sqlId), sqlId);
        logMessage("queryForMap", sql, paramMap);
        return singleResult(getJdbcTemplate(paramMap).queryForList(sql, paramMap));
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param param
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Map<String, Object> queryForMap(String sqlId, Object param) {
        return this.queryForMap(sqlId, convertToMap(param));
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param paramMap
     * @param requiredType
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public <T> List<T> queryForList(String sqlId, Map<String, ?> paramMap, Class<T> requiredType) {
        String sql = FreeMakerParser.proccess(paramMap, getSQL(sqlId), sqlId);
        logMessage("queryForList(3 paramter)", sql, paramMap);
        return getJdbcTemplate(paramMap).query(sql, mapIfNull(paramMap), new BeanPropertyRowMapper<T>(requiredType));
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param param
     * @param requiredType
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public <T> List<T> queryForList(String sqlId, Object param, Class<T> requiredType) {
        return queryForList(sqlId, convertToMap(param), requiredType);
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param paramMap
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<Map<String, Object>> queryForList(String sqlId, Map<String, ?> paramMap) {
        String sql = FreeMakerParser.proccess(paramMap, getSQL(sqlId), sqlId);
        logMessage("queryForList(2 paramter)", sql, paramMap);
        return getJdbcTemplate(paramMap).queryForList(sql, mapIfNull(paramMap));
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param param
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<Map<String, Object>> queryForList(String sqlId, Object param) {
        return queryForList(sqlId, convertToMap(param));
    }
    private static Map<String,Object> convertToMap(Object arg){
        try {
            Map<String,Object> returnMap = BeanUtils.describe(arg);
            returnMap.remove("class");
            return returnMap;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param paramMap
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int execute(String sqlId, Map<String, ?> paramMap) {
        String sql = FreeMakerParser.proccess(paramMap, getSQL(sqlId), sqlId);
        logMessage("execute", sql, paramMap);
        return getValidateJdbcTemplate(paramMap).update(sql, mapIfNull(paramMap));
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param param
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int execute(String sqlId, Object param) {
        return this.execute(sqlId, convertToMap(param));
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param sqlId
     * @param batchValues
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int[] batchUpdate(String sqlId, Map<String, ?>[] batchValues) {
        Map<String, ?> paramMap = new HashMap<String, Object>();
        if (batchValues != null && batchValues[0] != null) {
            paramMap = batchValues[0];
        }
        String sql = FreeMakerParser.proccess(paramMap, getSQL(sqlId), sqlId);
        logMessage("batchUpdate", sql, String.valueOf(batchValues == null ? 0 : batchValues.length));
        return getValidateJdbcTemplate(paramMap).batchUpdate(sql, batchValues);
    }

    protected NamedParameterJdbcTemplate getJdbcTemplate(Object params) {
        DataSource targetDataSource = DataSourceContext.getDataSource();
        if (targetDataSource == null) {
            return new NamedParameterJdbcTemplate(routeDataSource(routeConfigDefault, params));
        } else {
            return new NamedParameterJdbcTemplate(targetDataSource);
        }
    }

    protected NamedParameterJdbcTemplate getValidateJdbcTemplate(Object params) {
        DataSource targetDataSource = DataSourceContext.getDataSource();
        DataSource routeDataSource = routeDataSource(routeConfigDefault, params);
        if (targetDataSource == null) {
            return new NamedParameterJdbcTemplate(routeDataSource);
        }
        if (!targetDataSource.equals(routeDataSource) && routeDataSource != null) {
            throw new RuntimeException("Could not operate different DataSouce[" + targetDataSource + ", " + routeDataSource
                    + "]");
        }
        return new NamedParameterJdbcTemplate(targetDataSource);
    }

    private void logMessage(String method, String sql, Object object) {
        if (logger.isDebugEnabled()) {
            logger.debug(method + " method SQL: [" + sql + "]");
            logger.debug(method + " method parameter:" + object);
        }
    }

    private <T> T singleResult(List<T> resultList) {
        if (resultList != null) {
            if (resultList.size() > 0) {
                if (logger.isDebugEnabled() && resultList.size() > 1)
                    logger.debug("Incorrect result size: expected " + 1 + ", actual " + resultList.size()
                            + " return the first element.");
                return resultList.get(0);
            }
            if (resultList.size() == 0) {
                if (logger.isDebugEnabled())
                    logger.debug("Incorrect result size: expected " + 1 + ", actual " + resultList.size());
                return null;
            }
        }
        return null;
    }

    private Map<String, ?> mapIfNull(Map<String, ?> map) {
        if (map == null) {
            return new HashMap<String, Object>();
        }
        return map;
    }
}
