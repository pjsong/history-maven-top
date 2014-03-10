package bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name="sohu_history_exception")
public class SohuHistoryExceptionDTO extends DbInstanceId {
    private int rowId;
    String code;
    String reason;
    String startDate;
    String endDate;
    Timestamp occurTime;
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
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    @Column(name = "startDate")
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }    
    @Column(name = "endDate")
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    @Column(name = "occurTime")
    public Timestamp getOccurTime() {
        return occurTime;
    }
    public void setOccurTime(Timestamp occurTime) {
        this.occurTime = occurTime;
    }

}
