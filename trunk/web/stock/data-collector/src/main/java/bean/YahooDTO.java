package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name="yahoo_history")
public class YahooDTO extends DbInstanceId {
    private int rowId;
    String code;
    String createDate;
    Double priceOpen;
    Double priceHigh;
    Double priceLow;
    Double priceClose;
    Double volumn;
    Double adjClose;
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
    public Double getVolumn() {
        return volumn;
    }
    public void setVolumn(Double volumn) {
        this.volumn = volumn;
    }
    @Column(name = "adjClose")
    public Double getAdjClose() {
        return adjClose;
    }
    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }
    @Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
 
}
