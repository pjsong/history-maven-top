package dao.transaction;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import dao.util.DataSourceContext;


interface CallBackTemplate<T> {
    public T invoke();
}
public class TransactionTemplateDAL {
    private DataSource dataSource = null;

    public TransactionTemplateDAL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T execute(final CallBackTemplate<T> action) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
       TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        try {
            DataSourceContext.pushCurrentDataSource(dataSource);
            return transactionTemplate.execute(new TransactionCallback<T>() {
                public T doInTransaction(TransactionStatus status) {
                    return action.invoke();
                }
            });
        } finally {
            DataSourceContext.popCurrentDataSource();
        }
    }

    public <T> T execute(final CallBackTemplate<T> action, int propagation) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(propagation);

        try {
            DataSourceContext.pushCurrentDataSource(dataSource);
            return transactionTemplate.execute(new TransactionCallback<T>() {
                public T doInTransaction(TransactionStatus status) {
                    return action.invoke();
                }
            });
        } finally {
            DataSourceContext.popCurrentDataSource();
        }
    }
}

