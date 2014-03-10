package dao.transaction;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import annotation.RouteParam;
import annotation.Transactional;

import dao.dal.DalClient;
import dao.exception.DalException;

public class TransactionInterceptor implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(TransactionInterceptor.class);

    private DalClient dalClient;

    public Object invoke(final MethodInvocation invocation) throws Throwable {
        if (invocation.getMethod().isAnnotationPresent(Transactional.class)) {
            Transactional transactional = invocation.getMethod().getAnnotation(Transactional.class);

            TransactionTemplateDAL transactionTemplateDAL = dalClient.getTransactionTemplate(getParameter(invocation));
            return transactionTemplateDAL.execute(new CallBackTemplate<Object>() {
                public Object invoke() {
                    try {
                        return invocation.proceed();
                    } catch (Throwable e) {
                        throw new DalException(e);
                    }
                }
            }, transactional.propagation());
        } else {
            return invocation.proceed();
        }
    }

    private Object[] getParameter(MethodInvocation invocation) {
        Transactional transactional = invocation.getMethod().getAnnotation(Transactional.class);
        RouteParam routeParam = invocation.getMethod().getAnnotation(RouteParam.class);
        Object[] indexParams = transactional.paramIndex() < 0 ? invocation.getArguments() : new Object[] { invocation
                .getArguments()[transactional.paramIndex()] };

        if (routeParam == null) {
            return indexParams;
        }
        Object obj = isInstance(indexParams, routeParam);

        if (obj == null) {
            return indexParams;
        }
        try {
            obj = getValue(obj, routeParam.field());
            logger.debug("interceptor route param is : " + obj);
        } catch (Exception e) {
            return indexParams;
        }
        return new Object[] { obj };
    }

    //if RouteParam.isArray=true, for each param try param[indexValue], else try param.
    //when routeParam.clazz match, return
    private Object isInstance(Object[] indexParams, RouteParam routeParam) {
        for (Object param : indexParams) {
            if (routeParam.isArray()) {
                param = ((Object[]) param)[routeParam.index()];
            }
            if (routeParam.clazz().isInstance(param)) {
                return param;
            }
        }
        return null;
    }
    

    private Object getValue(Object obj, String field) throws Exception {
        Map<String, Object> routeMap = new HashMap<String, Object>();
        if (obj instanceof Map<?, ?>) {
            Map<?, ?> parentParamMap = (Map<?, ?>) obj;
            if (!parentParamMap.containsKey(field)) {
                throw new Exception("param " + obj + " not contains key " + field);
            }
            routeMap.put(field, parentParamMap.get(field));
        } else if (obj instanceof Number || obj instanceof String) {
            routeMap.put(field, obj);
        } else {
            PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(obj.getClass(), field);
            Object value = descriptor.getReadMethod().invoke(obj, new Object[] {});
            routeMap.put(field, value);
        }
        return routeMap;
    }


    public void setDalClient(DalClient dalClient) {
        this.dalClient = dalClient;
    }
    
}
