package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name="163_history")
public class NetEaseDTO extends DbInstanceId {
    private int rowId;
    String code;
    String createDate;
    
    Double priceOpen;
    Double priceClose;
    Double priceHigh;
    Double priceLow;

    Long volumn;
    Double chgRate;
    Double totalMarketValue;
    Double currentMarketValue;
    @Id
    @Column(name = "rowId")
    @GeneratedValue(strategy=GenerationType.TABLE)
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
    @Column(name = "priceOpen")
    public Double getPriceOpen() {
        return priceOpen;
    }
    public void setPriceOpen(Double priceOpen) {
        this.priceOpen = priceOpen;
    }
    @Column(name = "priceHigh")
    public Double getPriceHigh() {
        return priceHigh;
    }
    public void setPriceHigh(Double priceHigh) {
        this.priceHigh = priceHigh;
    }
    @Column(name = "priceLow")
    public Double getPriceLow() {
        return priceLow;
    }
    public void setPriceLow(Double priceLow) {
        this.priceLow = priceLow;
    }
    @Column(name = "priceClose")
    public Double getPriceClose() {
        return priceClose;
    }
    public void setPriceClose(Double priceClose) {
        this.priceClose = priceClose;
    }
    @Column(name = "volumn")
    public Long getVolumn() {
        return volumn;
    }
    public void setVolumn(Long volumn) {
        this.volumn = volumn;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name = "chgRate")
    public Double getChgRate() {
        return chgRate;
    }
    public void setChgRate(Double chgRate) {
        this.chgRate = chgRate;
    }
    
    @Column(name = "totalMarketValue")
    public Double getTotalMarketValue() {
        return totalMarketValue;
    }
    public void setTotalMarketValue(Double totalMarketValue) {
        this.totalMarketValue = totalMarketValue;
    }
    @Column(name = "currentMarketValue")
    public Double getCurrentMarketValue() {
        return currentMarketValue;
    }
    public void setCurrentMarketValue(Double currentMarketValue) {
        this.currentMarketValue = currentMarketValue;
    }
 
}
