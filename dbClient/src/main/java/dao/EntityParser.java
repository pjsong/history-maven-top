package dao;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 *  为实体bean生成sql语句<br> 
 * 〈功能详细描述〉
 *
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class EntityParser {
    private static Logger logger = LoggerFactory.getLogger(EntityParser.class);
    private String _table = null;

    private String id = null;

    private String _id = null;

    private boolean isGenerator = true;
    //属性名，用于SQL语句=:之后，
    private List<String> columnList = new ArrayList<String>();
    //column annotation名，
    private List<String> _columnList = new ArrayList<String>();

    private List<String> fieldList = new ArrayList<String>();

    private String insert = null;
    private String batchInsert = null;

    private String update = null;

    private String delete = null;

    private String select = null;
    //不更新值为null的属性
    private String dynamicUpdate = null;

    private String selectAll = null;
   //annotation <name, value> pair
    private Map<String, Column> propertyMap = new HashMap<String, Column>();
    // <property name, column value> pair
    private Map<String, Column> fieldMap = new HashMap<String, Column>();

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getId() {
        return id;
    }

    /**
     * @param clazz
     */
    private EntityParser(Class<?> clazz) {
        setFieldList(clazz);
        setTable(clazz);
        setId(clazz);
        setColumnList(clazz);

        setInsertSql();
        setUpdateSql();
        setDeleteSql();
        setSelectSql();
        setSelectAllSql();
    }
    /**
     * @param <T>
     * @param clazz
     */
    private <T> EntityParser(Class<T> clazz, List<T> list) {
        setFieldList(clazz);
        setTable(clazz);
        setId(clazz);
        setColumnList(clazz);

        setInsertSql();
        setInsertSqlBatch(clazz, list);
        setUpdateSql();
        setDeleteSql();
        setSelectSql();
        setSelectAllSql();
    }
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getInsert() {
        return insert;
    }

    

    public String getBatchInsert() {
		return batchInsert;
	}

	public void setBatchInsert(String batchInsert) {
		this.batchInsert = batchInsert;
	}

	/**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getUpdate() {
        return update;
    }

    /**
     * 功能描述: filter out fields containing value of null<br>
     * 〈功能详细描述〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getDynamicUpdate(Map<String, ?> object) {
        setDynamicUpdateSql(object);
        return dynamicUpdate;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getDelete() {
        return delete;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getSelect() {
        return select;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getSelectAll() {
        return selectAll;
    }

    private void setDynamicUpdateSql(Map<String, ?> object) {
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(_table).append(" SET ");
        for (int i = 0; i < _columnList.size(); i++) {
            Column c = propertyMap.get(_columnList.get(i));
            String tt  =columnList.get(i);
            if (object.get(columnList.get(i)) == null || !propertyMap.get(_columnList.get(i)).updatable()) {
                continue;
            }
            sb.append(_columnList.get(i)).append(" = :").append(columnList.get(i));
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(" WHERE ");
        sb.append(_id).append(" = :").append(id);
        dynamicUpdate = sb.toString();
    }

    private void setInsertSql() {
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(_table).append("(");

        if (!isGenerator) {
            if (_id != null && id != null) {
                sb.append(_id);
                sb.append(", ");
            }
        }

        for (int i = 0; i < _columnList.size(); i++) {
            if (propertyMap.get(_columnList.get(i)).insertable()) {
                sb.append(_columnList.get(i));
                sb.append(", ");
            }
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(") VALUES (");
        if (!isGenerator) {
            if (_id != null && id != null) {
                sb.append(":").append(id);
                sb.append(", ");
            }
        }

        for (int i = 0; i < columnList.size(); i++) {
            if (fieldMap.get(columnList.get(i)).insertable()) {
                sb.append(":").append(columnList.get(i));
                sb.append(", ");
            }
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(")");
        insert = sb.toString();
    }
    
    
    private <T> void setInsertSqlBatch(Class<T> clazz, List<T> list) {
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(_table).append("(");
//        
//        if (!isGenerator) {
//            if (_id != null && id != null) {
//                sb.append(_id);
//                sb.append(", ");
//            }
//        }

        for (int i = 0; i < _columnList.size(); i++) {
            if (propertyMap.get(_columnList.get(i)).insertable()) {
                sb.append(_columnList.get(i));
                sb.append(", ");
            }
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(") VALUES ");
        for(T t:list){
            sb.append("(");
//            if (!isGenerator) {
//                if (_id != null && id != null) {
//                    sb.append(":").append(id);
//                    sb.append(", ");
//                }
//            }
            Map<String, Object> result = parser(t);
            for (int i = 0; i < _columnList.size(); i++) {

				if (fieldMap.get(columnList.get(i)).insertable()) {
					Object strFormat = result.get(columnList.get(i));
					sb.append(strFormat == null? "null":"'"+strFormat.toString()+"'");
					sb.append(", ");
				}
                
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("),");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        batchInsert = sb.toString();
    }
    

    private void setUpdateSql() {
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(_table).append(" SET ");
        for (int i = 0; i < _columnList.size(); i++) {
            if (propertyMap.get(_columnList.get(i)).updatable()) {
                sb.append(_columnList.get(i)).append(" = :").append(columnList.get(i));
                sb.append(", ");
            }
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(" WHERE ");
        sb.append(_id).append(" = :").append(id);
        update = sb.toString();
    }

    private void setDeleteSql() {
        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(_table).append(" WHERE ");
        sb.append(_id).append(" = :").append(id);
        delete = sb.toString();
    }

    private void setSelectSql() {
        StringBuffer sb = new StringBuffer("SELECT ");
        List<String> tempList = new ArrayList<String>(_columnList);
        tempList.add(_id);

        for (int i = 0; i < tempList.size(); i++) {
            sb.append(tempList.get(i));
            if (i < tempList.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" FROM ").append(_table).append(" WHERE ");
        sb.append(_id).append(" = :").append(id);
        select = sb.toString();
    }

    private void setSelectAllSql() {
        StringBuffer sb = new StringBuffer("SELECT ");
        List<String> tempList = new ArrayList<String>(_columnList);
        tempList.add(_id);

        for (int i = 0; i < tempList.size(); i++) {
            sb.append(tempList.get(i));
            if (i < tempList.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" FROM ").append(_table);
        selectAll = sb.toString();
    }

    private void setTable(Class<?> clazz) {
        Entity entity = clazz.getAnnotation(Entity.class);
        _table = entity.name();
    }

    private void setFieldList(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldList.add(field.getName());
        }
    }

    private void setId(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Id.class)) {
                _id = method.getAnnotation(Column.class).name();
                id = BeanUtils.findPropertyForMethod(method).getName();
                GeneratedValue generatedValue = method.getAnnotation(GeneratedValue.class);
                if (generatedValue != null && generatedValue.strategy() != null
                        && generatedValue.strategy().compareTo(GenerationType.AUTO) != 0) {
                    isGenerator = false;
                } else {
                    isGenerator = true;
                }
            }
        }
    }

    private void setColumnList(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        Field[] fields = clazz.getDeclaredFields();
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Column.class) && !method.isAnnotationPresent(Id.class)) {
                PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
                if (isTransient(fields, descriptor.getName()) || isTransient(superFields, descriptor.getName())) {
                    continue;
                }
                Column columnAnnoation = method.getAnnotation(Column.class);
//                ORDER_ID
                _columnList.add(columnAnnoation.name());
//                orderId
                columnList.add(BeanUtils.findPropertyForMethod(method).getName());
//                {ORDER_ID=@javax.persistence.Column(insertable=true, scale=0, unique=false, precision=0, columnDefinition=, name=ORDER_ID, updatable=true, length=20, nullable=true, table=)}
                propertyMap.put(columnAnnoation.name(), columnAnnoation);
//                {orderId=@javax.persistence.Column(insertable=true, scale=0, unique=false, precision=0, columnDefinition=, name=ORDER_ID, updatable=true, length=20, nullable=true, table=)}
                fieldMap.put(BeanUtils.findPropertyForMethod(method).getName(), columnAnnoation);
            }
        }
    }

    private boolean isTransient(Field[] fields, String descriptorName) {
        for (Field field : fields) {
            if (field.getName().equals(descriptorName) && Modifier.isTransient(field.getModifiers())) {
                return true;
            }
        }
        return false;
    }
    
    private static class entityParserHolder{
        private static Map<Class<?>, EntityParser> cache = new HashMap<Class<?>, EntityParser>();
        public static EntityParser getEntityParser(Class<?> clazz) {
            EntityParser entityParser = cache.get(clazz);
            if (entityParser == null) {
                entityParser = new EntityParser(clazz);
                synchronized (cache) {
                    if (cache.get(clazz) == null) {
                        cache.put(clazz, entityParser);
                    }
                }
            }

            return entityParser;
        }
    }
    
//    private static class entityParserListHolder{
//        public static EntityParser getEntityParser(Class<?> clazz, List list) {
//            return new EntityParser(clazz, list);
//        }
//    }
    
    public static EntityParser getEntityParser(Class<?> clazz){
        return entityParserHolder.getEntityParser(clazz);
    }
    public static <T> EntityParser getEntityListParser(Class<?> clazz, List entityList){
        return new EntityParser(clazz, entityList);
    }
    public  Map<String, Object> parser(Object entity) {
        Map<String, Object> values = new HashMap<String, Object>();
        Method[] methods = entity.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Column.class)) {
                Column column = method.getAnnotation(Column.class);
                PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
                String key = descriptor.getName();
                Object value = null;
                try {
                    value = method.invoke(entity, new Object[] {});
                    if (value instanceof java.util.Date) {
                        value = dateFormat(column, (Date) value);
                    }
                } catch (Exception e) {
                    logger.debug("reflect error.[" + method + "]", e);
                }
                values.put(key, value);
            }
        }

        return values;
    }
    private Object dateFormat(Column column, Date date) {
        if (date != null && !"".equals(column.columnDefinition())) {
            SimpleDateFormat format = new SimpleDateFormat(column.columnDefinition());
            return format.format(date);
        }
        return date;
    }
    
    
    public static void main(String[] args){
    	List l = new ArrayList<String>();
    	List ll = new ArrayList<Date>();
    	if(ll.getClass().equals(ArrayList.class)){
    		System.out.println("s");
    	}else{
    		System.out.println("f");
    	}
    }
}
