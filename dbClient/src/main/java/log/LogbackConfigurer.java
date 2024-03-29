package log;

import java.io.FileNotFoundException;
import java.net.URL;

import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * 
 * 功能描述： 根据logback指定配置文件目录及文件
 * 名实现对logback的初始化
 * @author 作者 yuanminga@cnsuning.com
 * @created 2012-3-14 上午9:40:56
 * @version 1.0.0
 * @date 2012-3-14 上午9:40:56
 */

public class LogbackConfigurer {
    /** Pseudo URL prefix for loading from the class path: "classpath:" */
    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    /** Extension that indicates a log4j XML config file: ".xml" */
    public static final String XML_FILE_EXTENSION = ".xml";



    public static void initLogging(String location) throws FileNotFoundException, JoranException {
        String resolvedLocation = SystemPropertyUtils.resolvePlaceholders(location);
        URL url = ResourceUtils.getURL(resolvedLocation);
        if (resolvedLocation.toLowerCase().endsWith(XML_FILE_EXTENSION)) {
            LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();  
            loggerContext.reset();  
            JoranConfigurator joranConfigurator = new JoranConfigurator();  
            joranConfigurator.setContext(loggerContext);  
            joranConfigurator.doConfigure(url); 
        }
    }
}

