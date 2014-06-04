package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "classifier")
public class ClassifierDTO extends DbInstanceId {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int rowId;
    String classSource;
    String classifiedBy;
    String classCode;
    String className;
    String stockCode;

    @Id
    @Column(name = "rowId")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    
    @Column(name = "classSource")
    public String getClassSource() {
        return classSource;
    }

    public void setClassSource(String classSource) {
        this.classSource = classSource;
    }
    @Column(name = "classifiedBy")
    public String getClassifiedBy() {
        return classifiedBy;
    }

    public void setClassifiedBy(String classifiedBy) {
        this.classifiedBy = classifiedBy;
    }
    @Column(name = "classCode")
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    @Column(name = "className")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    @Column(name = "stockCode")
    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }


}
