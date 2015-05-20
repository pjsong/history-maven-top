package constants;

import java.io.Serializable;
import javax.persistence.Column;

/**
 * 〈所有实体基类〉<br> 
 * 〈库号〉
 *
 * @author pjsong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DbInstanceId implements Serializable{

    private static final long serialVersionUID = -8760566371482273008L;
    public final static String DB_INSTANCE_ID = "dbInstanceId";
    private transient String dbInstanceId;
    @Column(name = "dbInstanceId")
    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }




}