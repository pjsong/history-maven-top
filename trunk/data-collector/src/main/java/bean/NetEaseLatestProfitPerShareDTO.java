package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "163_latest_profit_pershare")
public class NetEaseLatestProfitPerShareDTO extends DbInstanceId {
    private int rowId;
    String code;
    /** 报告日期[0] **/
    String createDate;
    /** 基本每股收益(元)[1] **/
    Double basicBenefitPerShare;

    @Id
    @Column(name = "rowId")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    @Column(name = "createDate")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "basicBenefitPerShare")
    public Double getBasicBenefitPerShare() {
        return basicBenefitPerShare;
    }

    public void setBasicBenefitPerShare(Double basicBenefitPerShare) {
        this.basicBenefitPerShare = basicBenefitPerShare;
    }
}
