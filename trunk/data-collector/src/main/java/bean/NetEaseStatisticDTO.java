package bean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.*;


/**
 * The persistent class for the yahoo_statistics database table.
 * 
 */
@Entity(name="163_statistics")
public class NetEaseStatisticDTO extends DbInstanceId {
	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private String code;
	private String createDate;
	private Integer down13;
	private Integer down144;
	private Integer down21;
	private Integer down233;
	private Integer down34;
	private Integer down377;
	private Integer down55;
	private Integer down89;
	private Integer up13;
	private Integer up144;
	private Integer up21;
	private Integer up233;
	private Integer up34;
	private Integer up377;
	private Integer up55;
	private Integer up89;
	private Integer lowestInDays;
	private Integer highestInDays;
	public NetEaseStatisticDTO() {
	}   @Id
    @Column(name = "rowId")
    @GeneratedValue(strategy=GenerationType.TABLE)
    public Integer getRowId() {
        return this.rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }
    @Column(name = "code")
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "createDate")
    public String getCreatedDate() {
        return this.createDate;
    }

    public void setCreatedDate(String createDate) {
        this.createDate = createDate;
    }

    @Column(name = "down13")
    public Integer getDown13() {
        return this.down13;
    }

    public void setDown13(Integer down13) {
        this.down13 = down13;
    }
    @Column(name = "down144")
    public Integer getDown144() {
        return this.down144;
    }

    public void setDown144(Integer down144) {
        this.down144 = down144;
    }

    @Column(name = "down21")
    public Integer getDown21() {
        return this.down21;
    }

    public void setDown21(Integer down21) {
        this.down21 = down21;
    }
    @Column(name = "down233")
    public Integer getDown233() {
        return this.down233;
    }

    public void setDown233(Integer down233) {
        this.down233 = down233;
    }

    @Column(name = "down34")
    public Integer getDown34() {
        return this.down34;
    }

    public void setDown34(Integer down34) {
        this.down34 = down34;
    }
    @Column(name = "down377")
    public Integer getDown377() {
        return this.down377;
    }

    public void setDown377(Integer down377) {
        this.down377 = down377;
    }

    @Column(name = "down55")
    public Integer getDown55() {
        return this.down55;
    }

    public void setDown55(Integer down55) {
        this.down55 = down55;
    }

    @Column(name = "down89")
    public Integer getDown89() {
        return this.down89;
    }

    public void setDown89(Integer down89) {
        this.down89 = down89;
    }


    @Column(name = "up13")
    public Integer getUp13() {
        return this.up13;
    }

    public void setUp13(Integer up13) {
        this.up13 = up13;
    }
    @Column(name = "up144")
    public Integer getUp144() {
        return this.up144;
    }

    public void setUp144(Integer up144) {
        this.up144 = up144;
    }

    @Column(name = "up21")
    public Integer getUp21() {
        return this.up21;
    }

    public void setUp21(Integer up21) {
        this.up21 = up21;
    }
    @Column(name = "up233")
    public Integer getUp233() {
        return this.up233;
    }

    public void setUp233(Integer up233) {
        this.up233 = up233;
    }

    @Column(name = "up34")
    public Integer getUp34() {
        return this.up34;
    }

    public void setUp34(Integer up34) {
        this.up34 = up34;
    }
    @Column(name = "up377")
    public Integer getUp377() {
        return this.up377;
    }

    public void setUp377(Integer up377) {
        this.up377 = up377;
    }

    @Column(name = "up55")
    public Integer getUp55() {
        return this.up55;
    }

    public void setUp55(Integer up55) {
        this.up55 = up55;
    }


    @Column(name = "up89")
    public Integer getUp89() {
        return this.up89;
    }

    public void setUp89(Integer up89) {
        this.up89 = up89;
    }
    @Column(name = "lowestInDays")
    public Integer getLowestInDays() {
        return lowestInDays;
    }
    public void setLowestInDays(Integer lowestInDays) {
        this.lowestInDays = lowestInDays;
    }
    @Column(name = "highestInDays")
    public Integer getHighestInDays() {
        return highestInDays;
    }
    public void setHighestInDays(Integer highestInDays) {
        this.highestInDays = highestInDays;
    }

    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public void writeStepField(String methodName, String str){
        Method method;
        try {
            method = getClass().getDeclaredMethod(methodName, String.class);
            method.invoke(this, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readStepField(String methodName){
        Method method;
        String ret = "";
        try {
            method = getClass().getDeclaredMethod(methodName);
            ret = (String)method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    public Integer readUpDownField(String methodName){
        Method method;
        Integer ret = 0;
        try {
            method = getClass().getDeclaredMethod(methodName);
            ret = (Integer)method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    public void writeUpDownField(String methodName, Integer str){
        Method method;
        try {
            method = getClass().getDeclaredMethod(methodName, Integer.class);
            method.invoke(this, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}