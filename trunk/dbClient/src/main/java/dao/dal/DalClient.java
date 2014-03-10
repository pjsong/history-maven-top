package dao.dal;

import java.util.List;
import java.util.Map;

import dao.transaction.TransactionTemplateDAL;



/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface DalClient{
    /**
     * 单表添加操作 返回Number类型的主键
     * @param entity 
     * @return 
     */
//    Number persist(Object entity);

    /**
     * 根据传入主键类型requiredType，返回主键值<br>
     * 非自增长主键，如果传入类型与主键类型不符，会抛出ClassCastException<br>
     * 自增长主键，必须传入类型为Number或BigDecimal类型
     * @param entity 
     * @param requiredType 
     * @return 
     */
    <T> T persist(Object entity);

    /** 单表修改操作 根据主键修改记录 
     * @param entity 
     * @return **/
    int merge(Object entity);

    /** 动态更新 
     * @param entity 
     * @return */
    int dynamicMerge(Object entity);

    /** 单表删除操作 根据主键删除记录 
     * @param entity 
     * @return **/
    int remove(Object entity);

    /** 单表查询操作 根据主键查询记录 
     * @param entityClass 
     * @param entity 
     * @return **/
    <T> T find(Class<T> entityClass, Object entity);

    /** 根据sqlId查询单个对象，返回requiredType类型对象，不需要强转，查不到或查询多个返回第一个 
     * @param sqlId 
     * @param paramMap 
     * @param requiredType 
     * @return */
    <T> T queryForObject(String sqlId, Map<String, ?> paramMap, Class<T> requiredType);

    /** 根据sqlId查询单个对象，返回requiredType类型对象，不需要强转，查不到或查询多个返回第一个 
     * @param sqlId 
     * @param param 
     * @param requiredType 
     * @return */
    <T> T queryForObject(String sqlId, Object param, Class<T> requiredType);

    /** 根据sqlId查询单个对象，返回Map集合，key是数据库字段 ，查不到或查询多个返回第一个 
     * @param sqlId 
     * @param paramMap 
     * @return */
    Map<String, Object> queryForMap(String sqlId, Map<String, ?> paramMap);

    /** 根据sqlId查询单个对象，返回Map集合，key是数据库字段 ，查不到或查询多个返回第一个 
     * @param sqlId 
     * @param param 
     * @return */
    Map<String, Object> queryForMap(String sqlId, Object param);

    /** 根据sqlId查询多个对象，返回requiredType类型对象List集合，不需要强转 
     * @param sqlId 
     * @param paramMap 
     * @param elementType 
     * @return */
    <T> List<T> queryForList(String sqlId, Map<String, ?> paramMap, Class<T> elementType);

    /** 根据sqlId查询多个对象，返回requiredType类型对象List集合，不需要强转 
     * @param sqlId 
     * @param param 
     * @param requiredType 
     * @return */
    <T> List<T> queryForList(String sqlId, Object param, Class<T> requiredType);

    /** 根据sqlId查询，返回Map集合List，key是数据库字段 
     * @param sqlId 
     * @param paramMap 
     * @return */
    List<Map<String, Object>> queryForList(String sqlId, Map<String, ?> paramMap);

    /** 根据sqlId查询，返回Map集合List，key是数据库字段 
     * @param sqlId 
     * @param param 
     * @return */
    List<Map<String, Object>> queryForList(String sqlId, Object param);

    /** 根据sqlId执行，返回执行成功的记录条数 
     * @param sqlId 
     * @param paramMap 
     * @return */
    int execute(String sqlId, Map<String, ?> paramMap);

    /** 根据sqlId执行，返回执行成功的记录条数 
     * @param sqlId 
     * @param param 
     * @return */
    int execute(String sqlId, Object param);

    /** 根据sqlId执行，批量执行 
     * @param sqlId 
     * @param batchValues 
     * @return */
    int[] batchUpdate(String sqlId, Map<String, ?>[] batchValues);

    /** 根据业务参数获取符合业务规则的事务模板 
     * @param parameter 
     * @return */
    TransactionTemplateDAL getTransactionTemplate(Object parameter);
}
