package bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import constants.DbInstanceId;


/**
 * SO_BIZ_TASK_OPERATION entity. @author MyEclipse Persistence Tools
 */

@Entity(name = "SO_BIZ_TASK_OPERATION")
public class BizTaskOperation extends DbInstanceId {

    // Fields

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The row id. */
    private Long rowId;

    /** The task code. */
    private String taskCode;

    /** The order id. */
    private String orderId;

    /** The order item id. */
    private String orderItemId;

    /** The ord opt time. */
    private Timestamp ordOptTime;

    /** The ord opt remark. */
    private String ordOptRemark;

    /** The is opt flag. */
    private Integer isOptFlag;

    /** The last upd time. */
    private Timestamp lastUpdTime;

    // Constructors

    /**
     * default constructor.
     */
    public BizTaskOperation() {
    }

    /**
     * full constructor.
     *
     * @param taskCode the task code
     * @param orderId the order id
     * @param orderItemId the order item id
     * @param ordOptTime the ord opt time
     * @param ordOptRemark the ord opt remark
     * @param isOptFlag the is opt flag
     * @param lastUpdTime the last upd time
     */
    public BizTaskOperation(String taskCode, String orderId, String orderItemId, Timestamp ordOptTime,
            String ordOptRemark, Integer isOptFlag, Timestamp lastUpdTime) {
        this.taskCode = taskCode;
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.ordOptTime = ordOptTime;
        this.ordOptRemark = ordOptRemark;
        this.isOptFlag = isOptFlag;
        this.lastUpdTime = lastUpdTime;
    }

    // Property accessors
    /**
     * Gets the row id.
     *
     * @return the row id
     */
    @Id
    @Column(name = "ROW_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getRowId() {
        return this.rowId;
    }

    /**
     * Sets the row id.
     *
     * @param rowId the new row id
     */
    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    /**
     * Gets the task code.
     *
     * @return the task code
     */
    @Column(name = "TASK_CODE")
    public String getTaskCode() {
        return this.taskCode;
    }

    /**
     * Sets the task code.
     *
     * @param taskCode the new task code
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    /**
     * Gets the order id.
     *
     * @return the order id
     */
    @Column(name = "ORDER_ID", length = 12)
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * Sets the order id.
     *
     * @param orderId the new order id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the order item id.
     *
     * @return the order item id
     */
    @Column(name = "ORDER_ITEM_ID", length = 14)
    public String getOrderItemId() {
        return this.orderItemId;
    }

    /**
     * Sets the order item id.
     *
     * @param orderItemId the new order item id
     */
    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    /**
     * Gets the ord opt time.
     *
     * @return the ord opt time
     */
    @Column(name = "ORD_OPT_TIME", length = 26)
    public Timestamp getOrdOptTime() {
        return this.ordOptTime;
    }

    /**
     * Sets the ord opt time.
     *
     * @param ordOptTime the new ord opt time
     */
    public void setOrdOptTime(Timestamp ordOptTime) {
        this.ordOptTime = ordOptTime;
    }

    /**
     * Gets the ord opt remark.
     *
     * @return the ord opt remark
     */
    @Column(name = "ORD_OPT_REMARK")
    public String getOrdOptRemark() {
        return this.ordOptRemark;
    }

    /**
     * Sets the ord opt remark.
     *
     * @param ordOptRemark the new ord opt remark
     */
    public void setOrdOptRemark(String ordOptRemark) {
        this.ordOptRemark = ordOptRemark;
    }

    /**
     * Gets the checks if is opt flag.
     *
     * @return the checks if is opt flag
     */
    @Column(name = "IS_OPT_FLAG")
    public Integer getIsOptFlag() {
        return this.isOptFlag;
    }

    /**
     * Sets the checks if is opt flag.
     *
     * @param isOptFlag the new checks if is opt flag
     */
    public void setIsOptFlag(Integer isOptFlag) {
        this.isOptFlag = isOptFlag;
    }

    /**
     * Gets the last upd time.
     *
     * @return the last upd time
     */
    @Column(name = "LAST_UPD_TIME", length = 26)
    public Timestamp getLastUpdTime() {
        return this.lastUpdTime;
    }

    /**
     * Sets the last upd time.
     *
     * @param lastUpdTime the new last upd time
     */
    public void setLastUpdTime(Timestamp lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }

}