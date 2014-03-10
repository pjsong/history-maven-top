package bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name="sohu_history_min_max_date_of_code")
public class SohuMinMaxDTO extends DbInstanceId {
    private int rowId;
    String code;
    String minDate;
    String maxDate;
    Timestamp updateTime;
    @Id
    @Column(name = "rowId")
    @GeneratedValue(strategy=GenerationType.TABLE)
    public int getRowId() {
        return rowId;
    }
    public void setRowId(int rowId) {
        this.rowId = rowId;
    }
    @Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "minDate")
    public String getMinDate() {
        return minDate;
    }
    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }
    @Column(name = "maxDate")
    public String getMaxDate() {
        return maxDate;
    }
    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
