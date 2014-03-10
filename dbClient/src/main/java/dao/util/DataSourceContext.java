package dao.util;

import java.util.Stack;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 为当前线程创建DataSource栈<br> 
 * 〈功能详细描述〉
 *
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DataSourceContext {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceContext.class);

    private static ThreadLocal<Stack<DataSource>> transcationContext = new ThreadLocal<Stack<DataSource>>();

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static DataSource getDataSource() {
        DataSource dataSource = null;

        Stack<DataSource> stack = getStack();
        if (!stack.empty()) {
            dataSource = stack.peek();
        }

        logger.debug("get currentThread datasource : " + dataSource);
        return dataSource;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param dataSource
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void pushCurrentDataSource(DataSource dataSource) {
        Stack<DataSource> stack = getStack();
        stack.push(dataSource);
        logger.debug("bind currentThread datasource : " + dataSource);
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void popCurrentDataSource() {
        Stack<DataSource> stack = getStack();
        if (!stack.empty()) {
            logger.debug("release currentThread datasource : " + stack.pop());
        }
    }

    private static Stack<DataSource> getStack() {
        if (transcationContext.get() == null) {
            transcationContext.set(new Stack<DataSource>());
        }
        return transcationContext.get();
    }
}

