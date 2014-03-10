package bean;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;


/**
 * The persistent class for the code_list database table.
 * 
 */
@Entity(name="code_list")
public class CodeList extends DbInstanceId{
	private static final long serialVersionUID = 1L;

	private int rowId;

	private String category;

	private String code;

	private String name;

	private Time onMarketTime;

	public CodeList() {
	}


	@Id
	@Column(name = "rowId")
	@GeneratedValue(strategy=GenerationType.TABLE)
	public int getRowId() {
		return this.rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	@Column(name = "category")
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "onMarketTime")
	public Time getOnMarketTime() {
		return this.onMarketTime;
	}

	public void setOnMarketTime(Time onMarketTime) {
		this.onMarketTime = onMarketTime;
	}

}