package bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="sina_live")
public class SinaLive extends DbInstanceId {
	String code;

	private int rowId;

	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "openPriceToday")
	public Double getOpenPriceToday() {
		return openPriceToday;
	}
	public void setOpenPriceToday(Double openPriceToday) {
		this.openPriceToday = openPriceToday;
	}
	@Column(name = "closePriceYesterday")
	public Double getClosePriceYesterday() {
		return closePriceYesterday;
	}
	
	public void setClosePriceYesterday(Double closePriceYesterday) {
		this.closePriceYesterday = closePriceYesterday;
	}
	@Column(name = "closePriceToday")
	public Double getClosePriceToday() {
		return closePriceToday;
	}
	public void setClosePriceToday(Double closePriceToday) {
		this.closePriceToday = closePriceToday;
	}
	@Column(name = "highestToday")
	public Double getHighestToday() {
		return highestToday;
	}
	public void setHighestToday(Double highestToday) {
		this.highestToday = highestToday;
	}
	@Column(name = "lowestToday")
	public Double getLowestToday() {
		return lowestToday;
	}
	public void setLowestToday(Double lowestToday) {
		this.lowestToday = lowestToday;
	}
	@Column(name = "dealAmount")
	public Double getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(Double dealAmount) {
		this.dealAmount = dealAmount;
	}
	@Column(name = "dealMoney")
	public Double getDealMoney() {
		return dealMoney;
	}
	public void setDealMoney(Double dealMoney) {
		this.dealMoney = dealMoney;
	}
	@Column(name = "createDate")
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String date) {
		this.createDate = date;
	}
	@Column(name = "createTime")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String time) {
		this.createTime = time;
	}
	
	@Id
	@Column(name = "rowId")
	@GeneratedValue(strategy=GenerationType.TABLE)
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}


	String name;
	Double openPriceToday;
	Double closePriceYesterday;
	Double closePriceToday;
	Double highestToday;
	Double lowestToday;
	Double dealAmount;
	Double dealMoney;
	String createDate;
	String createTime;
}
