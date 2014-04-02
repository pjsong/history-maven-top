package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "163_main_financial")
public class NetEaseMainFinancialDTO extends DbInstanceId {
    private int rowId;
    String code;
    /** 报告日期[0] **/
    String createDate;
    /** 基本每股收益(元)[1] **/
    Double basicBenefitPerShare;
    /** 每股净资产[2] **/
    Double assetsPerShare;
    /** 每股经营活动产生的现金流量净额[3] **/
    Double cashGenPerShare;
    /** 主营业务收入(万元)[4] **/
    Double mainBusinessIncome;
    /** 主营业务利润(万元)[5] **/
    Double mainBusinessInterest;
    /** 营业利润(万元)[6] **/
    Double businessInterest;
    /** 利润总额(万元)[9] **/
    Double totalInterest;
    /** 净利润(万元)[10] **/
    Double pureInterest;
    /** 现金及现金等价物净增加额[13] **/
    Double cashGenTotal;
    /** 总资产(万元)[14] **/
    Double assetsTotal;
    /** 流动资产[15] **/
    Double assetsFlow;
    /** 总负债(万元)[16] **/
    Double debtTotal;
    /** 流动负债(万元)[17] **/
    Double debtFlow;
    /** 股东权益不含少数股东权益(万元)[18] **/
    Double shareHolderBig;

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

    @Column(name = "assetsPerShare")
    public Double getAssetsPerShare() {
        return assetsPerShare;
    }

    public void setAssetsPerShare(Double assetsPerShare) {
        this.assetsPerShare = assetsPerShare;
    }

    @Column(name = "assetsTotal")
    public Double getAssetsTotal() {
        return assetsTotal;
    }

    public void setAssetsTotal(Double assetsTotal) {
        this.assetsTotal = assetsTotal;
    }

    @Column(name = "assetsFlow")
    public Double getAssetsFlow() {
        return assetsFlow;
    }

    public void setAssetsFlow(Double assetsFlow) {
        this.assetsFlow = assetsFlow;
    }

    @Column(name = "debtTotal")
    public Double getDebtTotal() {
        return debtTotal;
    }

    public void setDebtTotal(Double debtTotal) {
        this.debtTotal = debtTotal;
    }

    @Column(name = "debtFlow")
    public Double getDebtFlow() {
        return debtFlow;
    }

    public void setDebtFlow(Double debtFlow) {
        this.debtFlow = debtFlow;
    }

    @Column(name = "cashGenPerShare")
    public Double getCashGenPerShare() {
        return cashGenPerShare;
    }

    public void setCashGenPerShare(Double cashGenPerShare) {
        this.cashGenPerShare = cashGenPerShare;
    }

    @Column(name = "cashGenTotal")
    public Double getCashGenTotal() {
        return cashGenTotal;
    }

    public void setCashGenTotal(Double cashGenTotal) {
        this.cashGenTotal = cashGenTotal;
    }

    @Column(name = "mainBusinessIncome")
    public Double getMainBusinessIncome() {
        return mainBusinessIncome;
    }

    public void setMainBusinessIncome(Double mainBusinessIncome) {
        this.mainBusinessIncome = mainBusinessIncome;
    }

    @Column(name = "mainBusinessInterest")
    public Double getMainBusinessInterest() {
        return mainBusinessInterest;
    }

    public void setMainBusinessInterest(Double mainBusinessInterest) {
        this.mainBusinessInterest = mainBusinessInterest;
    }

    @Column(name = "businessInterest")
    public Double getBusinessInterest() {
        return businessInterest;
    }

    public void setBusinessInterest(Double businessInterest) {
        this.businessInterest = businessInterest;
    }

    @Column(name = "totalInterest")
    public Double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }

    @Column(name = "pureInterest")
    public Double getPureInterest() {
        return pureInterest;
    }

    public void setPureInterest(Double pureInterest) {
        this.pureInterest = pureInterest;
    }

    @Column(name = "shareHolderBig")
    public Double getShareHolderBig() {
        return shareHolderBig;
    }

    public void setShareHolderBig(Double shareHolderBig) {
        this.shareHolderBig = shareHolderBig;
    }

}
