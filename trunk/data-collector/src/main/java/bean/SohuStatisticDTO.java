package bean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.*;


/**
 * The persistent class for the yahoo_statistics database table.
 * 
 */
@Entity(name="sohu_statistics")
public class SohuStatisticDTO extends DbInstanceId {
	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private String code;
	private String createDate;
	private Integer down1;
	private Integer down13;
	private Integer down144;
	private Integer down2;
	private Integer down21;
	private Integer down233;
	private Integer down3;
	private Integer down34;
	private Integer down377;
	private Integer down5;

	private Integer down55;
	private Integer down610;
	private Integer down8;
	private Integer down89;
	private String step1;
	private String step13;
	private String step2;
	private String step21;
	private String step3;
	private String step34;
	private String step5;
	private String step55;
	private String step8;
	private String step89;
	private Integer up1;
	private Integer up13;
	private Integer up144;
	private Integer up2;
	private Integer up21;
	private Integer up233;
	private Integer up3;
	private Integer up34;
	private Integer up377;
	private Integer up5;
	private Integer up55;
	private Integer up610;
	private Integer up8;
	private Integer up89;
	private Integer lowestInDays;
	private Integer highestInDays;
	public SohuStatisticDTO() {
	}
    @Id
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
    @Column(name = "down1")
	public Integer getDown1() {
		return this.down1;
	}

	public void setDown1(Integer down1) {
		this.down1 = down1;
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
    @Column(name = "down2")
	public Integer getDown2() {
		return this.down2;
	}

	public void setDown2(Integer down2) {
		this.down2 = down2;
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
    @Column(name = "down3")
	public Integer getDown3() {
		return this.down3;
	}

	public void setDown3(Integer down3) {
		this.down3 = down3;
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
    @Column(name = "down5")
	public Integer getDown5() {
		return this.down5;
	}

	public void setDown5(Integer down5) {
		this.down5 = down5;
	}
    @Column(name = "down55")
	public Integer getDown55() {
		return this.down55;
	}

	public void setDown55(Integer down55) {
		this.down55 = down55;
	}
    @Column(name = "down610")
	public Integer getDown610() {
		return this.down610;
	}

	public void setDown610(Integer down610) {
		this.down610 = down610;
	}
    @Column(name = "down8")
	public Integer getDown8() {
		return this.down8;
	}

	public void setDown8(Integer down8) {
		this.down8 = down8;
	}
    @Column(name = "down89")
	public Integer getDown89() {
		return this.down89;
	}

	public void setDown89(Integer down89) {
		this.down89 = down89;
	}
    @Column(name = "step1")
	public String getStep1() {
		return this.step1;
	}

	public void setStep1(String step1) {
		this.step1 = step1;
	}
    @Column(name = "step13")
	public String getStep13() {
		return this.step13;
	}

	public void setStep13(String step13) {
		this.step13 = step13;
	}
    @Column(name = "step2")
	public String getStep2() {
		return this.step2;
	}

	public void setStep2(String step2) {
		this.step2 = step2;
	}
    @Column(name = "step21")
	public String getStep21() {
		return this.step21;
	}

	public void setStep21(String step21) {
		this.step21 = step21;
	}
    @Column(name = "step3")
	public String getStep3() {
		return this.step3;
	}

	public void setStep3(String step3) {
		this.step3 = step3;
	}
    @Column(name = "step34")
	public String getStep34() {
		return this.step34;
	}

	public void setStep34(String step34) {
		this.step34 = step34;
	}
    @Column(name = "step5")
	public String getStep5() {
		return this.step5;
	}

	public void setStep5(String step5) {
		this.step5 = step5;
	}
    @Column(name = "step55")
	public String getStep55() {
		return this.step55;
	}

	public void setStep55(String step55) {
		this.step55 = step55;
	}
    @Column(name = "step8")
	public String getStep8() {
		return this.step8;
	}

	public void setStep8(String step8) {
		this.step8 = step8;
	}
    @Column(name = "step89")
	public String getStep89() {
		return this.step89;
	}

	public void setStep89(String step89) {
		this.step89 = step89;
	}
    @Column(name = "up1")
	public Integer getUp1() {
		return this.up1;
	}

	public void setUp1(Integer up1) {
		this.up1 = up1;
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
    @Column(name = "up2")
	public Integer getUp2() {
		return this.up2;
	}

	public void setUp2(Integer up2) {
		this.up2 = up2;
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
    @Column(name = "up3")
	public Integer getUp3() {
		return this.up3;
	}

	public void setUp3(Integer up3) {
		this.up3 = up3;
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
    @Column(name = "up5")
	public Integer getUp5() {
		return this.up5;
	}

	public void setUp5(Integer up5) {
		this.up5 = up5;
	}
    @Column(name = "up55")
	public Integer getUp55() {
		return this.up55;
	}

	public void setUp55(Integer up55) {
		this.up55 = up55;
	}
    @Column(name = "up610")
	public Integer getUp610() {
		return this.up610;
	}

	public void setUp610(Integer up610) {
		this.up610 = up610;
	}
    @Column(name = "up8")
	public Integer getUp8() {
		return this.up8;
	}
	public void setUp8(Integer up8) {
		this.up8 = up8;
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