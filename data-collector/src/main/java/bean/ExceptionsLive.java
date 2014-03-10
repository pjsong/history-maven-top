package bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="exceptions_live")
public class ExceptionsLive extends DbInstanceId{
	String code;
	String source;
	Timestamp createdTime;
	Timestamp lastUpdatedTime;
	String malFormedLine;
	private int rowId;

	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	@Column(name = "source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column(name = "createdTime")
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name = "lastUpdatedTime")
	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	@Column(name = "malFormedLine")
	public String getMalFormedLine() {
		return malFormedLine;
	}
	public void setMalFormedLine(String malFormedLine) {
		this.malFormedLine = malFormedLine;
	}
	
}
