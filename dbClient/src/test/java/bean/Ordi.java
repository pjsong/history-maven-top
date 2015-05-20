package bean;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import constants.DbInstanceId;


/**
 * SoOrdi entity. @author MyEclipse Persistence Tools
 */
@Entity(name = "SO_ORDI")
public class Ordi extends DbInstanceId implements java.io.Serializable {

    // Fields

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The row id. */
    private Long rowId;

    /** The order id. */
    private String orderId;

    /** The order item id. */
    private String orderItemId;

    /** The b2c order id. */
    private String b2cOrderId;

    /** The b2corder item id. */
    private String b2corderItemId;

    /** The sap order id. */
    private String sapOrderId;

    /** The order type. */
    private String orderType;

    /** The posorder id. */
    private String posorderId;

    /** The dist channel. */
    private String distChannel;

    /** The store code. */
    private String storeCode;

    /** The commodity code. */
    private String commodityCode;

    /** The sapproduct code. */
    private String sapproductCode;

    /** The commodity name. */
    private String commodityName;

    /** The ean code. */
    private String eanCode;

    /** The supplier code. */
    private String supplierCode;

    /** The product level. */
    private String productLevel;

    /** The sale org. */
    private String saleOrg;

    /** The sale num. */
    private Double saleNum;

    /** The sale unit. */
    private String saleUnit;

    /** The unit price. */
    private Double unitPrice;

    /** The sal org shop. */
    private String salOrgShop;

    /** The stock area. */
    private String stockArea;

    /** The provide address. */
    private String provideAddress;

    /** The service fee. */
    private Double serviceFee;

    /** The transport fee. */
    private Double transportFee;

    /** The pay amount. */
    private Double payAmount;

    /** The total amount. */
    private Double totalAmount;

    /** The task flag. */
    private String taskFlag;

    /** The has service. */
    private String hasService;

    /** The maintain num. */
    private Double maintainNum;

    /** The enchase detail. */
    private String enchaseDetail;

    /** The bol no. */
    private String bolNo;

    /** The ship condition. */
    private String shipCondition;

    /** The info source. */
    private String infoSource;

    /** The purchase flag. */
    private String purchaseFlag;

    /** The ads page. */
    private String adsPage;

    /** The casher id. */
    private String casherId;

    /** The sales person. */
    private String salesPerson;

    /** The protion action code. */
    private String protionActionCode;

    /** The coupon total money. */
    private Double couponTotalMoney;

    /** The manager card money. */
    private Double managerCardMoney;

    /** The voucher total money. */
    private Double voucherTotalMoney;

    /** The manager card no. */
    private String managerCardNo;

    /** The distribute type. */
    private String distributeType;

    /** The consume term. */
    private String consumeTerm;

    /** The hope arrival time. */
    private Timestamp hopeArrivalTime;

    /** The plan out time. */
    private Timestamp planOutTime;

    /** The delivery comments. */
    private String deliveryComments;

    /** The prom ticket money. */
    private Double promTicketMoney;

    /** The pickup sites code. */
    private String pickupSitesCode;

    /** The gifg num. */
    private Double gifgNum;

    /** The if part send. */
    private String ifPartSend;

    /** The bill type. */
    private Integer billType;

    /** The orditm class. */
    private String orditmClass;

    /** The order item stauts. */
    private String orderItemStauts;

    /** The order item remark. */
    private String orderItemRemark;

    /** The created by. */
    private String createdBy;

    /** The created time. */
    private Timestamp createdTime;

    /** The last upd by. */
    private String lastUpdBy;

    /** The last upd time. */
    private Timestamp lastUpdTime;

    /** The active flag. */
    private Integer activeFlag;

    /** The remark. */
    private String remark;

    /** The biz flow code. */
    private String bizFlowCode;

    /** The division. */
    private String division;

    /** The sup rpstry code. */
    private String supRpstryCode;

    /** The sap voucher no. */
    private String sapVoucherNo;

    /** The agency flag. */
    private String agencyFlag;

    /** The price file no. */
    private String priceFileNo;

    /** The sup provide address. */
    private String supProvideAddress;

    /** The com character. */
    private String comCharacter;

    /** 验证码 用于货到付款，订单查询等. */
    private String IUnik;

    /** 订单行的支付方式种类数. */
    private String payModeNum;

    /** 包裹号. */
    private String packageNo;

    /** 是否有实时延保. */
    private String maintainFlag;

    /** 套餐编码. */
    private String setmealCode;

    /** 是否可部分退货. */
    private String ifPartCancel;

    /** 物流提供商. */
    private String distributeProvider;

    /** 发票打印商家. */
    private String invoicePrintShop;

    /** 配送区域=地址（编码）+大小件（编码）. */
    private String deliveryArea;

    /** 还款方式. */
    private String payType;

    /** The pay terminal. */
    private String payTerminal;

    /** The casher terminal id. */
    private String casherTerminalId;

    /** The currency type. */
    private String currencyType;

    /** The point amount. */
    private BigDecimal pointAmount;

    /** The ab bank flag. */
    private String abBankFlag;

    /** The serial number. */
    private String serialNumber;

    /** The imprefection grade. */
    private String imprefectionGrade;

    /** The C prototype id. */
    private String cPrototypeId;

    /** The relation order item id. */
    private String relationOrderItemId;

    /** The inevitable flag. */
    private String inevitableFlag;

    /** The receipt type. */
    private String receiptType;

    /** The deli signature type. */
    private String deliSignatureType;

    /** The agreement id. */
    private String agreementId;

    /** The agreement type. */
    private String agreementType;

    /** The special business. */
    private String specialBusiness;

    /** 会员中心单据类型. */
    private String cmfOrderType;

    /** 会员中心单据描述. */
    private String cmfOrderName;

    /** 使用积分金额. */
    private BigDecimal pointMoney;

    /** C类样机真实商品EAN码. */
    private String cPrototypeEan;

    /** C类样机真实商品EAN码. */
    private String invoiceId;

    /** B2C交易单号，一个商机对应一个交易单号. */
    private String b2cBusinessOrder;

    /** 超卖数量. */
    private BigDecimal overSaleQty;

    /** 发货优先级. */
    private Integer deliPriority;

    /** 采购发送状态. */
    private Integer purchaseSendStatus;

    /** 客户订单包装号. */
    private String customOrderPackageId;

    /** 参考商品编码. */
    private String refGoodsCode;

    /** 来源系统. */
    private String sourceSystem;

    /** 是否需要发票. */
    private String isInvoice;

    /** 多次发货标识. */
    private String mutipleDeliFlag;

    /** 序列号2，如：空调外机序列号. */
    private String serialNumber2;

    /** 视图号1. */
    private String viewNumber1;

    /** 视图号2，. */
    private String viewNumber2;

    /** 视图标识1. */
    private String viewFlag1;

    /** 视图标识2. */
    private String viewFlag2;

    /** The energy saved flag. */
    private String energySavedFlag;

    /** The tocountry flag. */
    private String tocountryFlag;

    /** 特殊退货标识. */
    private String specialReturnFlag;

    /** 真实营业员. */
    private String realSalesPerson;

    /** 历史订单标识. */
    private String historyFlag;

    /** 已发货数量. */
    private BigDecimal shippedAmount;

    // Constructors

    /**
     * default constructor.
     */
    public Ordi() {
    }

    /**
     * Instantiates a new ordi.
     * 
     * @param orderId the order id
     * @param orderItemId the order item id
     * @param b2cOrderId the b2c order id
     * @param b2corderItemId the b2corder item id
     * @param sapOrderId the sap order id
     * @param orderType the order type
     * @param posorderId the posorder id
     * @param distChannel the dist channel
     * @param storeCode the store code
     * @param commodityCode the commodity code
     * @param sapproductCode the sapproduct code
     * @param commodityName the commodity name
     * @param eanCode the ean code
     * @param supplierCode the supplier code
     * @param productLevel the product level
     * @param saleOrg the sale org
     * @param saleNum the sale num
     * @param saleUnit the sale unit
     * @param unitPrice the unit price
     * @param salOrgShop the sal org shop
     * @param stockArea the stock area
     * @param provideAddress the provide address
     * @param serviceFee the service fee
     * @param transportFee the transport fee
     * @param payAmount the pay amount
     * @param totalAmount the total amount
     * @param taskFlag the task flag
     * @param hasService the has service
     * @param maintainNum the maintain num
     * @param enchaseDetail the enchase detail
     * @param bolNo the bol no
     * @param shipCondition the ship condition
     * @param infoSource the info source
     * @param purchaseFlag the purchase flag
     * @param adsPage the ads page
     * @param casherId the casher id
     * @param salesPerson the sales person
     * @param protionActionCode the protion action code
     * @param couponTotalMoney the coupon total money
     * @param managerCardMoney the manager card money
     * @param voucherTotalMoney the voucher total money
     * @param managerCardNo the manager card no
     * @param distributeType the distribute type
     * @param consumeTerm the consume term
     * @param hopeArrivalTime the hope arrival time
     * @param planOutTime the plan out time
     * @param deliveryComments the delivery comments
     * @param promTicketMoney the prom ticket money
     * @param pickupSitesCode the pickup sites code
     * @param gifgNum the gifg num
     * @param ifPartSend the if part send
     * @param billType the bill type
     * @param orditmClass the orditm class
     * @param orderItemStauts the order item stauts
     * @param orderItemRemark the order item remark
     * @param createdBy the created by
     * @param createdTime the created time
     * @param lastUpdBy the last upd by
     * @param lastUpdTime the last upd time
     * @param activeFlag the active flag
     * @param remark the remark
     * @param bizFlowCode the biz flow code
     * @param division the division
     * @param supRpstryCode the sup rpstry code
     * @param sapVoucherNo the sap voucher no
     * @param agencyFlag the agency flag
     * @param priceFileNo the price file no
     * @param supProvideAddress the sup provide address
     * @param comCharacter the com character
     * @param IUnik the i unik
     * @param payModeNum the pay mode num
     * @param packageNo the package no
     * @param maintainFlag the maintain flag
     * @param setmealCode the setmeal code
     * @param ifPartCancel the if part cancel
     * @param distributeProvider the distribute provider
     * @param invoicePrintShop the invoice print shop
     * @param deliveryArea the delivery area
     * @param payType the pay type
     * @param payTerminal the pay terminal
     * @param casherTerminalId the casher terminal id
     * @param currencyType the currency type
     * @param pointAmount the point amount
     * @param abBankFlag the ab bank flag
     * @param serialNumber the serial number
     * @param imprefectionGrade the imprefection grade
     * @param cPrototypeId the c prototype id
     * @param relationOrderItemId the relation order item id
     * @param inevitableFlag the inevitable flag
     * @param receiptType the receipt type
     * @param deliSignatureType the deli signature type
     * @param agreementId the agreement id
     * @param agreementType the agreement type
     * @param specialBusiness the special business
     * @param cmfOrderType the cmf order type
     * @param cmfOrderName the cmf order name
     * @param pointMoney the point money
     * @param cPrototypeEan the c prototype ean
     * @param invoiceId the invoice id
     * @param b2cBusinessOrder the b2c business order
     * @param overSaleQty the over sale qty
     * @param deliPriority the deli priority
     * @param purchaseSendStatus the purchase send status
     * @param customOrderPackageId the custom order package id
     * @param refGoodsCode the ref goods code
     * @param sourceSystem the source system
     * @param isInvoice the is invoice
     * @param mutipleDeliFlag the mutiple ddeli flag
     * @param serialNumber2 the serial number2
     * @param viewNumber1 the view number1
     * @param viewNumber2 the view number2
     * @param viewFlag1 the view flag1
     * @param viewFlag2 the view flag2
     * @param energySavedFlag the energy saved flag
     * @param tocountryFlag the tocountry flag
     * @param specialReturnFlag the special return flag
     * @param realSalesPerson the real sales person
     * @param historyFlag the history flag
     * @param shippedAmount the shipped amount
     */
    public Ordi(String orderId, String orderItemId, String b2cOrderId, String b2corderItemId, String sapOrderId,
            String orderType, String posorderId, String distChannel, String storeCode, String commodityCode,
            String sapproductCode, String commodityName, String eanCode, String supplierCode, String productLevel,
            String saleOrg, Double saleNum, String saleUnit, Double unitPrice, String salOrgShop, String stockArea,
            String provideAddress, Double serviceFee, Double transportFee, Double payAmount, Double totalAmount,
            String taskFlag, String hasService, Double maintainNum, String enchaseDetail, String bolNo,
            String shipCondition, String infoSource, String purchaseFlag, String adsPage, String casherId,
            String salesPerson, String protionActionCode, Double couponTotalMoney, Double managerCardMoney,
            Double voucherTotalMoney, String managerCardNo, String distributeType, String consumeTerm,
            Timestamp hopeArrivalTime, Timestamp planOutTime, String deliveryComments, Double promTicketMoney,
            String pickupSitesCode, Double gifgNum, String ifPartSend, Integer billType, String orditmClass,
            String orderItemStauts, String orderItemRemark, String createdBy, Timestamp createdTime, String lastUpdBy,
            Timestamp lastUpdTime, Integer activeFlag, String remark, String bizFlowCode, String division,
            String supRpstryCode, String sapVoucherNo, String agencyFlag, String priceFileNo, String supProvideAddress,
            String comCharacter, String IUnik, String payModeNum, String packageNo, String maintainFlag,
            String setmealCode, String ifPartCancel, String distributeProvider, String invoicePrintShop,
            String deliveryArea, String payType, String payTerminal, String casherTerminalId, String currencyType,
            BigDecimal pointAmount, String abBankFlag, String serialNumber, String imprefectionGrade,
            String cPrototypeId, String relationOrderItemId, String inevitableFlag, String receiptType,
            String deliSignatureType, String agreementId, String agreementType, String specialBusiness,
            String cmfOrderType, String cmfOrderName, BigDecimal pointMoney, String cPrototypeEan, String invoiceId,
            String b2cBusinessOrder, BigDecimal overSaleQty, Integer deliPriority, Integer purchaseSendStatus,
            String customOrderPackageId, String refGoodsCode, String sourceSystem, String isInvoice,
            String mutipleDeliFlag, String serialNumber2, String viewNumber1, String viewNumber2, String viewFlag1,
            String viewFlag2, String energySavedFlag, String tocountryFlag, String specialReturnFlag,
            String realSalesPerson, String historyFlag, BigDecimal shippedAmount) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.b2cOrderId = b2cOrderId;
        this.b2corderItemId = b2corderItemId;
        this.sapOrderId = sapOrderId;
        this.orderType = orderType;
        this.posorderId = posorderId;
        this.distChannel = distChannel;
        this.storeCode = storeCode;
        this.commodityCode = commodityCode;
        this.sapproductCode = sapproductCode;
        this.commodityName = commodityName;
        this.eanCode = eanCode;
        this.supplierCode = supplierCode;
        this.productLevel = productLevel;
        this.saleOrg = saleOrg;
        this.saleNum = saleNum;
        this.saleUnit = saleUnit;
        this.unitPrice = unitPrice;
        this.salOrgShop = salOrgShop;
        this.stockArea = stockArea;
        this.provideAddress = provideAddress;
        this.serviceFee = serviceFee;
        this.transportFee = transportFee;
        this.payAmount = payAmount;
        this.totalAmount = totalAmount;
        this.taskFlag = taskFlag;
        this.hasService = hasService;
        this.maintainNum = maintainNum;
        this.enchaseDetail = enchaseDetail;
        this.bolNo = bolNo;
        this.shipCondition = shipCondition;
        this.infoSource = infoSource;
        this.purchaseFlag = purchaseFlag;
        this.adsPage = adsPage;
        this.casherId = casherId;
        this.salesPerson = salesPerson;
        this.protionActionCode = protionActionCode;
        this.couponTotalMoney = couponTotalMoney;
        this.managerCardMoney = managerCardMoney;
        this.voucherTotalMoney = voucherTotalMoney;
        this.managerCardNo = managerCardNo;
        this.distributeType = distributeType;
        this.consumeTerm = consumeTerm;
        this.hopeArrivalTime = hopeArrivalTime;
        this.planOutTime = planOutTime;
        this.deliveryComments = deliveryComments;
        this.promTicketMoney = promTicketMoney;
        this.pickupSitesCode = pickupSitesCode;
        this.gifgNum = gifgNum;
        this.ifPartSend = ifPartSend;
        this.billType = billType;
        this.orditmClass = orditmClass;
        this.orderItemStauts = orderItemStauts;
        this.orderItemRemark = orderItemRemark;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdBy = lastUpdBy;
        this.lastUpdTime = lastUpdTime;
        this.activeFlag = activeFlag;
        this.remark = remark;
        this.bizFlowCode = bizFlowCode;
        this.division = division;
        this.supRpstryCode = supRpstryCode;
        this.sapVoucherNo = sapVoucherNo;
        this.agencyFlag = agencyFlag;
        this.priceFileNo = priceFileNo;
        this.supProvideAddress = supProvideAddress;
        this.comCharacter = comCharacter;
        this.IUnik = IUnik;
        this.payModeNum = payModeNum;
        this.packageNo = packageNo;
        this.maintainFlag = maintainFlag;
        this.setmealCode = setmealCode;
        this.ifPartCancel = ifPartCancel;
        this.distributeProvider = distributeProvider;
        this.invoicePrintShop = invoicePrintShop;
        this.deliveryArea = deliveryArea;
        this.payType = payType;
        this.payTerminal = payTerminal;
        this.casherTerminalId = casherTerminalId;
        this.currencyType = currencyType;
        this.pointAmount = pointAmount;
        this.abBankFlag = abBankFlag;
        this.serialNumber = serialNumber;
        this.imprefectionGrade = imprefectionGrade;
        this.cPrototypeId = cPrototypeId;
        this.relationOrderItemId = relationOrderItemId;
        this.inevitableFlag = inevitableFlag;
        this.receiptType = receiptType;
        this.deliSignatureType = deliSignatureType;
        this.agreementId = agreementId;
        this.agreementType = agreementType;
        this.specialBusiness = specialBusiness;
        this.cmfOrderType = cmfOrderType;
        this.cmfOrderName = cmfOrderName;
        this.pointMoney = pointMoney;
        this.cPrototypeEan = cPrototypeEan;
        this.invoiceId = invoiceId;
        this.b2cBusinessOrder = b2cBusinessOrder;
        this.overSaleQty = overSaleQty;
        this.deliPriority = deliPriority;
        this.purchaseSendStatus = purchaseSendStatus;
        this.customOrderPackageId = customOrderPackageId;
        this.refGoodsCode = refGoodsCode;
        this.sourceSystem = sourceSystem;
        this.isInvoice = isInvoice;
        this.mutipleDeliFlag = mutipleDeliFlag;
        this.serialNumber2 = serialNumber2;
        this.viewNumber1 = viewNumber1;
        this.viewNumber2 = viewNumber2;
        this.viewFlag1 = viewFlag1;
        this.viewFlag2 = viewFlag2;
        this.energySavedFlag = energySavedFlag;
        this.tocountryFlag = tocountryFlag;
        this.specialReturnFlag = specialReturnFlag;
        this.realSalesPerson = realSalesPerson;
        this.historyFlag = historyFlag;
        this.shippedAmount = shippedAmount;
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
     * Gets the order id.
     * 
     * @return the order id
     */
    @Column(name = "ORDER_ID", length = 20)
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
    @Column(name = "ORDER_ITEM_ID", unique = true, length = 20)
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
     * Gets the b2c order id.
     * 
     * @return the b2c order id
     */
    @Column(name = "B2C_ORDER_ID", length = 12)
    public String getB2cOrderId() {
        return this.b2cOrderId;
    }

    /**
     * Sets the b2c order id.
     * 
     * @param b2cOrderId the new b2c order id
     */
    public void setB2cOrderId(String b2cOrderId) {
        this.b2cOrderId = b2cOrderId;
    }

    /**
     * Gets the b2corder item id.
     * 
     * @return the b2corder item id
     */
    @Column(name = "B2CORDER_ITEM_ID", length = 20)
    public String getB2corderItemId() {
        return this.b2corderItemId;
    }

    /**
     * Sets the b2corder item id.
     * 
     * @param b2corderItemId the new b2corder item id
     */
    public void setB2corderItemId(String b2corderItemId) {
        this.b2corderItemId = b2corderItemId;
    }

    /**
     * Gets the sap order id.
     * 
     * @return the sap order id
     */
    @Column(name = "SAP_ORDER_ID", length = 10)
    public String getSapOrderId() {
        return this.sapOrderId;
    }

    /**
     * Sets the sap order id.
     * 
     * @param sapOrderId the new sap order id
     */
    public void setSapOrderId(String sapOrderId) {
        this.sapOrderId = sapOrderId;
    }

    /**
     * Gets the order type.
     * 
     * @return the order type
     */
    @Column(name = "ORDER_TYPE", length = 6)
    public String getOrderType() {
        return this.orderType;
    }

    /**
     * Sets the order type.
     * 
     * @param orderType the new order type
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * Gets the posorder id.
     * 
     * @return the posorder id
     */
    @Column(name = "POSORDER_ID", length = 9)
    public String getPosorderId() {
        return this.posorderId;
    }

    /**
     * Sets the posorder id.
     * 
     * @param posorderId the new posorder id
     */
    public void setPosorderId(String posorderId) {
        this.posorderId = posorderId;
    }

    /**
     * Gets the dist channel.
     * 
     * @return the dist channel
     */
    @Column(name = "DIST_CHANNEL", length = 32)
    public String getDistChannel() {
        return this.distChannel;
    }

    /**
     * Sets the dist channel.
     * 
     * @param distChannel the new dist channel
     */
    public void setDistChannel(String distChannel) {
        this.distChannel = distChannel;
    }

    /**
     * Gets the store code.
     * 
     * @return the store code
     */
    @Column(name = "STORE_CODE", length = 4)
    public String getStoreCode() {
        return this.storeCode;
    }

    /**
     * Sets the store code.
     * 
     * @param storeCode the new store code
     */
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    /**
     * Gets the commodity code.
     * 
     * @return the commodity code
     */
    @Column(name = "COMMODITY_CODE", length = 18)
    public String getCommodityCode() {
        return this.commodityCode;
    }

    /**
     * Sets the commodity code.
     * 
     * @param commodityCode the new commodity code
     */
    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    /**
     * Gets the sapproduct code.
     * 
     * @return the sapproduct code
     */
    @Column(name = "SAPPRODUCT_CODE", length = 32)
    public String getSapproductCode() {
        return this.sapproductCode;
    }

    /**
     * Sets the sapproduct code.
     * 
     * @param sapproductCode the new sapproduct code
     */
    public void setSapproductCode(String sapproductCode) {
        this.sapproductCode = sapproductCode;
    }

    /**
     * Gets the commodity name.
     * 
     * @return the commodity name
     */
    @Column(name = "COMMODITY_NAME", length = 100)
    public String getCommodityName() {
        return this.commodityName;
    }

    /**
     * Sets the commodity name.
     * 
     * @param commodityName the new commodity name
     */
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    /**
     * Gets the ean code.
     * 
     * @return the ean code
     */
    @Column(name = "EAN_CODE", length = 32)
    public String getEanCode() {
        return this.eanCode;
    }

    /**
     * Sets the ean code.
     * 
     * @param eanCode the new ean code
     */
    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    /**
     * Gets the supplier code.
     * 
     * @return the supplier code
     */
    @Column(name = "SUPPLIER_CODE", length = 10)
    public String getSupplierCode() {
        return this.supplierCode;
    }

    /**
     * Sets the supplier code.
     * 
     * @param supplierCode the new supplier code
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * Gets the product level.
     * 
     * @return the product level
     */
    @Column(name = "PRODUCT_LEVEL", length = 32)
    public String getProductLevel() {
        return this.productLevel;
    }

    /**
     * Sets the product level.
     * 
     * @param productLevel the new product level
     */
    public void setProductLevel(String productLevel) {
        this.productLevel = productLevel;
    }

    /**
     * Gets the sale org.
     * 
     * @return the sale org
     */
    @Column(name = "SALE_ORG", length = 4)
    public String getSaleOrg() {
        return this.saleOrg;
    }

    /**
     * Sets the sale org.
     * 
     * @param saleOrg the new sale org
     */
    public void setSaleOrg(String saleOrg) {
        this.saleOrg = saleOrg;
    }

    /**
     * Gets the sale num.
     * 
     * @return the sale num
     */
    @Column(name = "SALE_NUM")
    public Double getSaleNum() {
        return this.saleNum;
    }

    /**
     * Sets the sale num.
     * 
     * @param saleNum the new sale num
     */
    public void setSaleNum(Double saleNum) {
        this.saleNum = saleNum;
    }

    /**
     * Gets the sale unit.
     * 
     * @return the sale unit
     */
    @Column(name = "SALE_UNIT", length = 32)
    public String getSaleUnit() {
        return this.saleUnit;
    }

    /**
     * Sets the sale unit.
     * 
     * @param saleUnit the new sale unit
     */
    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    /**
     * Gets the unit price.
     * 
     * @return the unit price
     */
    @Column(name = "UNIT_PRICE", precision = 16)
    public Double getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * Sets the unit price.
     * 
     * @param unitPrice the new unit price
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Gets the sal org shop.
     * 
     * @return the sal org shop
     */
    @Column(name = "SAL_ORG_SHOP", length = 32)
    public String getSalOrgShop() {
        return this.salOrgShop;
    }

    /**
     * Sets the sal org shop.
     * 
     * @param salOrgShop the new sal org shop
     */
    public void setSalOrgShop(String salOrgShop) {
        this.salOrgShop = salOrgShop;
    }

    /**
     * Gets the stock area.
     * 
     * @return the stock area
     */
    @Column(name = "STOCK_AREA", length = 32)
    public String getStockArea() {
        return this.stockArea;
    }

    /**
     * Sets the stock area.
     * 
     * @param stockArea the new stock area
     */
    public void setStockArea(String stockArea) {
        this.stockArea = stockArea;
    }

    /**
     * Gets the provide address.
     * 
     * @return the provide address
     */
    @Column(name = "PROVIDE_ADDRESS", length = 32)
    public String getProvideAddress() {
        return this.provideAddress;
    }

    /**
     * Sets the provide address.
     * 
     * @param provideAddress the new provide address
     */
    public void setProvideAddress(String provideAddress) {
        this.provideAddress = provideAddress;
    }

    /**
     * Gets the service fee.
     * 
     * @return the service fee
     */
    @Column(name = "SERVICE_FEE", precision = 16)
    public Double getServiceFee() {
        return this.serviceFee;
    }

    /**
     * Sets the service fee.
     * 
     * @param serviceFee the new service fee
     */
    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    /**
     * Gets the transport fee.
     * 
     * @return the transport fee
     */
    @Column(name = "TRANSPORT_FEE", precision = 16)
    public Double getTransportFee() {
        return this.transportFee;
    }

    /**
     * Sets the transport fee.
     * 
     * @param transportFee the new transport fee
     */
    public void setTransportFee(Double transportFee) {
        this.transportFee = transportFee;
    }

    /**
     * Gets the pay amount.
     * 
     * @return the pay amount
     */
    @Column(name = "PAY_AMOUNT", precision = 16)
    public Double getPayAmount() {
        if (payAmount == null) {
            return null;
        }
        return this.payAmount;
    }

    /**
     * Sets the pay amount.
     * 
     * @param payAmount the new pay amount
     */
    public void setPayAmount(Double payAmount) {
        if (payAmount == null) {
            this.payAmount = null;
        } else {
            this.payAmount = payAmount;
        }
    }

    /**
     * Gets the total amount.
     * 
     * @return the total amount
     */
    @Column(name = "TOTAL_AMOUNT", precision = 16)
    public Double getTotalAmount() {
        if (totalAmount == null) {
            return null;
        }
        return this.totalAmount;
    }

    /**
     * Sets the total amount.
     * 
     * @param totalAmount the new total amount
     */
    public void setTotalAmount(Double totalAmount) {
        if (totalAmount == null) {
            this.totalAmount = null;
        } else {
            this.totalAmount = totalAmount;
        }
    }

    /**
     * Gets the task flag.
     * 
     * @return the task flag
     */
    @Column(name = "TASK_FLAG", length = 1)
    public String getTaskFlag() {
        return this.taskFlag;
    }

    /**
     * Sets the task flag.
     * 
     * @param taskFlag the new task flag
     */
    public void setTaskFlag(String taskFlag) {
        this.taskFlag = taskFlag;
    }

    /**
     * Gets the checks for service.
     * 
     * @return the checks for service
     */
    @Column(name = "HAS_SERVICE", length = 1)
    public String getHasService() {
        return this.hasService;
    }

    /**
     * Sets the checks for service.
     * 
     * @param hasService the new checks for service
     */
    public void setHasService(String hasService) {
        this.hasService = hasService;
    }

    /**
     * Gets the maintain num.
     * 
     * @return the maintain num
     */
    @Column(name = "MAINTAIN_NUM")
    public Double getMaintainNum() {
        return this.maintainNum;
    }

    /**
     * Sets the maintain num.
     * 
     * @param maintainNum the new maintain num
     */
    public void setMaintainNum(Double maintainNum) {
        this.maintainNum = maintainNum;
    }

    /**
     * Gets the enchase detail.
     * 
     * @return the enchase detail
     */
    @Column(name = "ENCHASE_DETAIL", length = 1000)
    public String getEnchaseDetail() {
        return this.enchaseDetail;
    }

    /**
     * Sets the enchase detail.
     * 
     * @param enchaseDetail the new enchase detail
     */
    public void setEnchaseDetail(String enchaseDetail) {
        this.enchaseDetail = enchaseDetail;
    }

    /**
     * Gets the bol no.
     * 
     * @return the bol no
     */
    @Column(name = "BOL_NO", length = 15)
    public String getBolNo() {
        return this.bolNo;
    }

    /**
     * Sets the bol no.
     * 
     * @param bolNo the new bol no
     */
    public void setBolNo(String bolNo) {
        this.bolNo = bolNo;
    }

    /**
     * Gets the ship condition.
     * 
     * @return the ship condition
     */
    @Column(name = "SHIP_CONDITION", length = 32)
    public String getShipCondition() {
        return this.shipCondition;
    }

    /**
     * Sets the ship condition.
     * 
     * @param shipCondition the new ship condition
     */
    public void setShipCondition(String shipCondition) {
        this.shipCondition = shipCondition;
    }

    /**
     * Gets the info source.
     * 
     * @return the info source
     */
    @Column(name = "INFO_SOURCE", length = 32)
    public String getInfoSource() {
        return this.infoSource;
    }

    /**
     * Sets the info source.
     * 
     * @param infoSource the new info source
     */
    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    /**
     * Gets the purchase flag.
     * 
     * @return the purchase flag
     */
    @Column(name = "PURCHASE_FLAG", length = 1)
    public String getPurchaseFlag() {
        return this.purchaseFlag;
    }

    /**
     * Sets the purchase flag.
     * 
     * @param purchaseFlag the new purchase flag
     */
    public void setPurchaseFlag(String purchaseFlag) {
        this.purchaseFlag = purchaseFlag;
    }

    /**
     * Gets the ads page.
     * 
     * @return the ads page
     */
    @Column(name = "ADS_PAGE")
    public String getAdsPage() {
        return this.adsPage;
    }

    /**
     * Sets the ads page.
     * 
     * @param adsPage the new ads page
     */
    public void setAdsPage(String adsPage) {
        this.adsPage = adsPage;
    }

    /**
     * Gets the casher id.
     * 
     * @return the casher id
     */
    @Column(name = "CASHER_ID", length = 32)
    public String getCasherId() {
        return this.casherId;
    }

    /**
     * Sets the casher id.
     * 
     * @param casherId the new casher id
     */
    public void setCasherId(String casherId) {
        this.casherId = casherId;
    }

    /**
     * Gets the sales person.
     * 
     * @return the sales person
     */
    @Column(name = "SALES_PERSON", length = 32)
    public String getSalesPerson() {
        return this.salesPerson;
    }

    /**
     * Sets the sales person.
     * 
     * @param salesPerson the new sales person
     */
    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    /**
     * Gets the protion action code.
     * 
     * @return the protion action code
     */
    @Column(name = "PROTION_ACTION_CODE", length = 32)
    public String getProtionActionCode() {
        return this.protionActionCode;
    }

    /**
     * Sets the protion action code.
     * 
     * @param protionActionCode the new protion action code
     */
    public void setProtionActionCode(String protionActionCode) {
        this.protionActionCode = protionActionCode;
    }

    /**
     * Gets the coupon total money.
     * 
     * @return the coupon total money
     */
    @Column(name = "COUPON_TOTAL_MONEY", precision = 16)
    public Double getCouponTotalMoney() {
        return this.couponTotalMoney;
    }

    /**
     * Sets the coupon total money.
     * 
     * @param couponTotalMoney the new coupon total money
     */
    public void setCouponTotalMoney(Double couponTotalMoney) {
        this.couponTotalMoney = couponTotalMoney;
    }

    /**
     * Gets the manager card money.
     * 
     * @return the manager card money
     */
    @Column(name = "MANAGER_CARD_MONEY", precision = 16)
    public Double getManagerCardMoney() {
        return this.managerCardMoney;
    }

    /**
     * Sets the manager card money.
     * 
     * @param managerCardMoney the new manager card money
     */
    public void setManagerCardMoney(Double managerCardMoney) {
        this.managerCardMoney = managerCardMoney;
    }

    /**
     * Gets the voucher total money.
     * 
     * @return the voucher total money
     */
    @Column(name = "VOUCHER_TOTAL_MONEY", precision = 16)
    public Double getVoucherTotalMoney() {
        return this.voucherTotalMoney;
    }

    /**
     * Sets the voucher total money.
     * 
     * @param voucherTotalMoney the new voucher total money
     */
    public void setVoucherTotalMoney(Double voucherTotalMoney) {
        this.voucherTotalMoney = voucherTotalMoney;
    }

    /**
     * Gets the manager card no.
     * 
     * @return the manager card no
     */
    @Column(name = "MANAGER_CARD_NO", length = 32)
    public String getManagerCardNo() {
        return this.managerCardNo;
    }

    /**
     * Sets the manager card no.
     * 
     * @param managerCardNo the new manager card no
     */
    public void setManagerCardNo(String managerCardNo) {
        this.managerCardNo = managerCardNo;
    }

    /**
     * Gets the distribute type.
     * 
     * @return the distribute type
     */
    @Column(name = "DISTRIBUTE_TYPE", length = 32)
    public String getDistributeType() {
        return this.distributeType;
    }

    /**
     * Sets the distribute type.
     * 
     * @param distributeType the new distribute type
     */
    public void setDistributeType(String distributeType) {
        this.distributeType = distributeType;
    }

    /**
     * Gets the consume term.
     * 
     * @return the consume term
     */
    @Column(name = "CONSUME_TERM", length = 32)
    public String getConsumeTerm() {
        return this.consumeTerm;
    }

    /**
     * Sets the consume term.
     * 
     * @param consumeTerm the new consume term
     */
    public void setConsumeTerm(String consumeTerm) {
        this.consumeTerm = consumeTerm;
    }

    /**
     * Gets the hope arrival time.
     * 
     * @return the hope arrival time
     */
    @Column(name = "HOPE_ARRIVAL_TIME", length = 26)
    public Timestamp getHopeArrivalTime() {
        return this.hopeArrivalTime;
    }

    /**
     * Sets the hope arrival time.
     * 
     * @param hopeArrivalTime the new hope arrival time
     */
    public void setHopeArrivalTime(Timestamp hopeArrivalTime) {
        this.hopeArrivalTime = hopeArrivalTime;
    }

    /**
     * Gets the plan out time.
     * 
     * @return the plan out time
     */
    @Column(name = "PLAN_OUT_TIME", length = 26)
    public Timestamp getPlanOutTime() {
        return this.planOutTime;
    }

    /**
     * Sets the plan out time.
     * 
     * @param planOutTime the new plan out time
     */
    public void setPlanOutTime(Timestamp planOutTime) {
        this.planOutTime = planOutTime;
    }

    /**
     * Gets the delivery comments.
     * 
     * @return the delivery comments
     */
    @Column(name = "DELIVERY_COMMENTS")
    public String getDeliveryComments() {
        return this.deliveryComments;
    }

    /**
     * Sets the delivery comments.
     * 
     * @param deliveryComments the new delivery comments
     */
    public void setDeliveryComments(String deliveryComments) {
        this.deliveryComments = deliveryComments;
    }

    /**
     * Gets the prom ticket money.
     * 
     * @return the prom ticket money
     */
    @Column(name = "PROM_TICKET_MONEY", precision = 16)
    public Double getPromTicketMoney() {
        return this.promTicketMoney;
    }

    /**
     * Sets the prom ticket money.
     * 
     * @param promTicketMoney the new prom ticket money
     */
    public void setPromTicketMoney(Double promTicketMoney) {
        this.promTicketMoney = promTicketMoney;
    }

    /**
     * Gets the pickup sites code.
     * 
     * @return the pickup sites code
     */
    @Column(name = "PICKUP_SITES_CODE", length = 32)
    public String getPickupSitesCode() {
        return this.pickupSitesCode;
    }

    /**
     * Sets the pickup sites code.
     * 
     * @param pickupSitesCode the new pickup sites code
     */
    public void setPickupSitesCode(String pickupSitesCode) {
        this.pickupSitesCode = pickupSitesCode;
    }

    /**
     * Gets the gifg num.
     * 
     * @return the gifg num
     */
    @Column(name = "GIFG_NUM")
    public Double getGifgNum() {
        return this.gifgNum;
    }

    /**
     * Sets the gifg num.
     * 
     * @param gifgNum the new gifg num
     */
    public void setGifgNum(Double gifgNum) {
        this.gifgNum = gifgNum;
    }

    /**
     * Gets the if part send.
     * 
     * @return the if part send
     */
    @Column(name = "IF_PART_SEND", length = 1)
    public String getIfPartSend() {
        return this.ifPartSend;
    }

    /**
     * Sets the if part send.
     * 
     * @param ifPartSend the new if part send
     */
    public void setIfPartSend(String ifPartSend) {
        this.ifPartSend = ifPartSend;
    }

    /**
     * Gets the bill type.
     * 
     * @return the bill type
     */
    @Column(name = "BILL_TYPE")
    public Integer getBillType() {
        return this.billType;
    }

    /**
     * Sets the bill type.
     * 
     * @param billType the new bill type
     */
    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    /**
     * Gets the orditm class.
     * 
     * @return the orditm class
     */
    @Column(name = "ORDITM_CLASS", length = 4)
    public String getOrditmClass() {
        return this.orditmClass;
    }

    /**
     * Sets the orditm class.
     * 
     * @param orditmClass the new orditm class
     */
    public void setOrditmClass(String orditmClass) {
        this.orditmClass = orditmClass;
    }

    /**
     * Gets the order item stauts.
     * 
     * @return the order item stauts
     */
    @Column(name = "ORDER_ITEM_STAUTS", length = 10)
    public String getOrderItemStauts() {
        return this.orderItemStauts;
    }

    /**
     * Sets the order item stauts.
     * 
     * @param orderItemStauts the new order item stauts
     */
    public void setOrderItemStauts(String orderItemStauts) {
        this.orderItemStauts = orderItemStauts;
    }

    /**
     * Gets the order item remark.
     * 
     * @return the order item remark
     */
    @Column(name = "ORDER_ITEM_REMARK")
    public String getOrderItemRemark() {
        return this.orderItemRemark;
    }

    /**
     * Sets the order item remark.
     * 
     * @param orderItemRemark the new order item remark
     */
    public void setOrderItemRemark(String orderItemRemark) {
        this.orderItemRemark = orderItemRemark;
    }

    /**
     * Gets the created by.
     * 
     * @return the created by
     */
    @Column(name = "CREATED_BY", length = 32)
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Sets the created by.
     * 
     * @param createdBy the new created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the created time.
     * 
     * @return the created time
     */
    @Column(name = "CREATED_TIME", insertable = false, length = 26)
    public Timestamp getCreatedTime() {
        return this.createdTime;
    }

    /**
     * Sets the created time.
     * 
     * @param createdTime the new created time
     */
    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Gets the last upd by.
     * 
     * @return the last upd by
     */
    @Column(name = "LAST_UPD_BY", length = 32)
    public String getLastUpdBy() {
        return this.lastUpdBy;
    }

    /**
     * Sets the last upd by.
     * 
     * @param lastUpdBy the new last upd by
     */
    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    /**
     * Gets the last upd time.
     * 
     * @return the last upd time
     */
    @Column(name = "LAST_UPD_TIME", insertable = false, length = 26)
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

    /**
     * Gets the active flag.
     * 
     * @return the active flag
     */
    @Column(name = "ACTIVE_FLAG", insertable = false)
    public Integer getActiveFlag() {
        return this.activeFlag;
    }

    /**
     * Sets the active flag.
     * 
     * @param activeFlag the new active flag
     */
    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    /**
     * Gets the remark.
     * 
     * @return the remark
     */
    @Column(name = "REMARK")
    public String getRemark() {
        return this.remark;
    }

    /**
     * Sets the remark.
     * 
     * @param remark the new remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Gets the biz flow code.
     * 
     * @return the biz flow code
     */
    @Column(name = "BIZ_FLOW_CODE", length = 36)
    public String getBizFlowCode() {
        return this.bizFlowCode;
    }

    /**
     * Sets the biz flow code.
     * 
     * @param bizFlowCode the new biz flow code
     */
    public void setBizFlowCode(String bizFlowCode) {
        this.bizFlowCode = bizFlowCode;
    }

    /**
     * Gets the division.
     * 
     * @return the division
     */
    @Column(name = "DIVISION", length = 32)
    public String getDivision() {
        return this.division;
    }

    /**
     * Sets the division.
     * 
     * @param division the new division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Gets the sup rpstry code.
     * 
     * @return the sup rpstry code
     */
    @Column(name = "SUP_RPSTRY_CODE", length = 32)
    public String getSupRpstryCode() {
        return this.supRpstryCode;
    }

    /**
     * Sets the sup rpstry code.
     * 
     * @param supRpstryCode the new sup rpstry code
     */
    public void setSupRpstryCode(String supRpstryCode) {
        this.supRpstryCode = supRpstryCode;
    }

    /**
     * Gets the sap voucher no.
     * 
     * @return the sap voucher no
     */
    @Column(name = "SAP_VOUCHER_NO", length = 20)
    public String getSapVoucherNo() {
        return this.sapVoucherNo;
    }

    /**
     * Sets the sap voucher no.
     * 
     * @param sapVoucherNo the new sap voucher no
     */
    public void setSapVoucherNo(String sapVoucherNo) {
        this.sapVoucherNo = sapVoucherNo;
    }

    /**
     * Gets the agency flag.
     * 
     * @return the agency flag
     */
    @Column(name = "AGENCY_FLAG", length = 4)
    public String getAgencyFlag() {
        return this.agencyFlag;
    }

    /**
     * Sets the agency flag.
     * 
     * @param agencyFlag the new agency flag
     */
    public void setAgencyFlag(String agencyFlag) {
        this.agencyFlag = agencyFlag;
    }

    /**
     * Gets the price file no.
     * 
     * @return the price file no
     */
    @Column(name = "PRICE_FILE_NO", length = 32)
    public String getPriceFileNo() {
        return this.priceFileNo;
    }

    /**
     * Sets the price file no.
     * 
     * @param priceFileNo the new price file no
     */
    public void setPriceFileNo(String priceFileNo) {
        this.priceFileNo = priceFileNo;
    }

    /**
     * Gets the sup provide address.
     * 
     * @return the sup provide address
     */
    @Column(name = "SUP_PROVIDE_ADDRESS", length = 20)
    public String getSupProvideAddress() {
        return this.supProvideAddress;
    }

    /**
     * Sets the sup provide address.
     * 
     * @param supProvideAddress the new sup provide address
     */
    public void setSupProvideAddress(String supProvideAddress) {
        this.supProvideAddress = supProvideAddress;
    }

    /**
     * Gets the com character.
     * 
     * @return the com character
     */
    @Column(name = "COM_CHARACTER", length = 2)
    public String getComCharacter() {
        return this.comCharacter;
    }

    /**
     * Sets the com character.
     * 
     * @param comCharacter the new com character
     */
    public void setComCharacter(String comCharacter) {
        this.comCharacter = comCharacter;
    }

    /**
     * Gets the i unik.
     * 
     * @return the i unik
     */
    @Column(name = "I_UNIK", length = 10)
    public String getIUnik() {
        return this.IUnik;
    }

    /**
     * Sets the i unik.
     * 
     * @param IUnik the new i unik
     */
    public void setIUnik(String IUnik) {
        this.IUnik = IUnik;
    }

    /**
     * Gets the delivery area.
     * 
     * @return the delivery area
     */
    @Column(name = "DELIVERY_AREA")
    public String getDeliveryArea() {
        return deliveryArea;
    }

    /**
     * Sets the delivery area.
     * 
     * @param deliveryArea the new delivery area
     */
    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    /**
     * Gets the pay mode num.
     * 
     * @return the pay mode num
     */
    @Column(name = "PAY_MODE_NUM")
    public String getPayModeNum() {
        return payModeNum;
    }

    /**
     * Sets the pay mode num.
     * 
     * @param payModeNum the payModeNum to set
     */
    public void setPayModeNum(String payModeNum) {
        this.payModeNum = payModeNum;
    }

    /**
     * Gets the package no.
     * 
     * @return the package no
     */
    @Column(name = "PACKAGE_NO")
    public String getPackageNo() {
        return packageNo;
    }

    /**
     * Sets the package no.
     * 
     * @param packageNo the packageNo to set
     */
    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    /**
     * Gets the maintain flag.
     * 
     * @return the maintain flag
     */
    @Column(name = "MAINTAIN_FLAG")
    public String getMaintainFlag() {
        return maintainFlag;
    }

    /**
     * Sets the maintain flag.
     * 
     * @param maintainFlag the maintainFlag to set
     */
    public void setMaintainFlag(String maintainFlag) {
        this.maintainFlag = maintainFlag;
    }

    /**
     * Gets the setmeal code.
     * 
     * @return the setmeal code
     */
    @Column(name = "SETMEAL_CODE")
    public String getSetmealCode() {
        return setmealCode;
    }

    /**
     * Sets the setmeal code.
     * 
     * @param setmealCode the setmealCode to set
     */
    public void setSetmealCode(String setmealCode) {
        this.setmealCode = setmealCode;
    }

    /**
     * Gets the if part cancel.
     * 
     * @return the if part cancel
     */
    @Column(name = "IF_PART_CANCEL")
    public String getIfPartCancel() {
        return ifPartCancel;
    }

    /**
     * Sets the if part cancel.
     * 
     * @param ifPartCancel the ifPartCancel to set
     */
    public void setIfPartCancel(String ifPartCancel) {
        this.ifPartCancel = ifPartCancel;
    }

    /**
     * Gets the distribute provider.
     * 
     * @return the distribute provider
     */
    @Column(name = "DISTRIBUTE_PROVIDER")
    public String getDistributeProvider() {
        return distributeProvider;
    }

    /**
     * Sets the distribute provider.
     * 
     * @param distributeProvider the distributeProvider to set
     */
    public void setDistributeProvider(String distributeProvider) {
        this.distributeProvider = distributeProvider;
    }

    /**
     * Gets the invoice print shop.
     * 
     * @return the invoice print shop
     */
    @Column(name = "INVOICE_PRINT_SHOP")
    public String getInvoicePrintShop() {
        return invoicePrintShop;
    }

    /**
     * Sets the invoice print shop.
     * 
     * @param invoicePrintShop the invoicePrintShop to set
     */
    public void setInvoicePrintShop(String invoicePrintShop) {
        this.invoicePrintShop = invoicePrintShop;
    }

    /**
     * Gets the pay type.
     * 
     * @return the payType
     */
    @Column(name = "PAY_TYPE")
    public String getPayType() {
        return payType;
    }

    /**
     * Sets the pay type.
     * 
     * @param payType the payType to set
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * Gets the pay terminal.
     * 
     * @return the pay terminal
     */
    @Column(name = "PAY_TERMINAL", length = 30)
    public String getPayTerminal() {
        return this.payTerminal;
    }

    /**
     * Sets the pay terminal.
     * 
     * @param payTerminal the new pay terminal
     */
    public void setPayTerminal(String payTerminal) {
        this.payTerminal = payTerminal;
    }

    /**
     * Gets the casher terminal id.
     * 
     * @return the casher terminal id
     */
    @Column(name = "CASHER_TERMINAL_ID", length = 10)
    public String getCasherTerminalId() {
        return this.casherTerminalId;
    }

    /**
     * Sets the casher terminal id.
     * 
     * @param casherTerminalId the new casher terminal id
     */
    public void setCasherTerminalId(String casherTerminalId) {
        this.casherTerminalId = casherTerminalId;
    }

    /**
     * Gets the currency type.
     * 
     * @return the currency type
     */
    @Column(name = "CURRENCY_TYPE", length = 4)
    public String getCurrencyType() {
        return this.currencyType;
    }

    /**
     * Sets the currency type.
     * 
     * @param currencyType the new currency type
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * Gets the point amount.
     * 
     * @return the point amount
     */
    @Column(name = "POINT_AMOUNT", precision = 16)
    public BigDecimal getPointAmount() {
        return this.pointAmount;
    }

    /**
     * Sets the point amount.
     * 
     * @param pointAmount the new point amount
     */
    public void setPointAmount(BigDecimal pointAmount) {
        this.pointAmount = pointAmount;
    }

    /**
     * Gets the ab bank flag.
     * 
     * @return the ab bank flag
     */
    @Column(name = "AB_BANK_FLAG", length = 1)
    public String getAbBankFlag() {
        return this.abBankFlag;
    }

    /**
     * Sets the ab bank flag.
     * 
     * @param abBankFlag the new ab bank flag
     */
    public void setAbBankFlag(String abBankFlag) {
        this.abBankFlag = abBankFlag;
    }

    /**
     * Gets the serial number.
     * 
     * @return the serial number
     */
    @Column(name = "SERIAL_NUMBER", length = 10)
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * Sets the serial number.
     * 
     * @param serialNumber the new serial number
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the imprefection grade.
     * 
     * @return the imprefection grade
     */
    @Column(name = "IMPREFECTION_GRADE", length = 4)
    public String getImprefectionGrade() {
        return this.imprefectionGrade;
    }

    /**
     * Sets the imprefection grade.
     * 
     * @param imprefectionGrade the new imprefection grade
     */
    public void setImprefectionGrade(String imprefectionGrade) {
        this.imprefectionGrade = imprefectionGrade;
    }

    /**
     * Gets the relation order item id.
     * 
     * @return the relation order item id
     */
    @Column(name = "RELATION_ORDER_ITEM_ID", length = 20)
    public String getRelationOrderItemId() {
        return this.relationOrderItemId;
    }

    /**
     * Sets the relation order item id.
     * 
     * @param relationOrderItemId the new relation order item id
     */
    public void setRelationOrderItemId(String relationOrderItemId) {
        this.relationOrderItemId = relationOrderItemId;
    }

    /**
     * Gets the inevitable flag.
     * 
     * @return the inevitable flag
     */
    @Column(name = "INEVITABLE_FLAG", length = 1)
    public String getInevitableFlag() {
        return this.inevitableFlag;
    }

    /**
     * Sets the inevitable flag.
     * 
     * @param inevitableFlag the new inevitable flag
     */
    public void setInevitableFlag(String inevitableFlag) {
        this.inevitableFlag = inevitableFlag;
    }

    /**
     * Gets the receipt type.
     * 
     * @return the receipt type
     */
    @Column(name = "RECEIPT_TYPE", length = 2)
    public String getReceiptType() {
        return this.receiptType;
    }

    /**
     * Sets the receipt type.
     * 
     * @param receiptType the new receipt type
     */
    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    /**
     * Gets the deli signature type.
     * 
     * @return the deli signature type
     */
    @Column(name = "DELI_SIGNATURE_TYPE", length = 60)
    public String getDeliSignatureType() {
        return this.deliSignatureType;
    }

    /**
     * Sets the deli signature type.
     * 
     * @param deliSignatureType the new deli signature type
     */
    public void setDeliSignatureType(String deliSignatureType) {
        this.deliSignatureType = deliSignatureType;
    }

    /**
     * Gets the agreement id.
     * 
     * @return the agreement id
     */
    @Column(name = "AGREEMENT_ID", length = 10)
    public String getAgreementId() {
        return this.agreementId;
    }

    /**
     * Sets the agreement id.
     * 
     * @param agreementId the new agreement id
     */
    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    /**
     * Gets the agreement type.
     * 
     * @return the agreement type
     */
    @Column(name = "AGREEMENT_TYPE", length = 4)
    public String getAgreementType() {
        return this.agreementType;
    }

    /**
     * Sets the agreement type.
     * 
     * @param agreementType the new agreement type
     */
    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    /**
     * Gets the special business.
     * 
     * @return the special business
     */
    @Column(name = "SPECIAL_BUSINESS", length = 2)
    public String getSpecialBusiness() {
        return this.specialBusiness;
    }

    /**
     * Sets the special business.
     * 
     * @param specialBusiness the new special business
     */
    public void setSpecialBusiness(String specialBusiness) {
        this.specialBusiness = specialBusiness;
    }

    /**
     * Gets the cmf order type.
     * 
     * @return the cmf order type
     */
    @Column(name = "CMF_ORDER_TYPE")
    public String getCmfOrderType() {
        return cmfOrderType;
    }

    /**
     * Sets the cmf order type.
     * 
     * @param cmfOrderType the new cmf order type
     */
    public void setCmfOrderType(String cmfOrderType) {
        this.cmfOrderType = cmfOrderType;
    }

    /**
     * Gets the cmf order name.
     * 
     * @return the cmf order name
     */
    @Column(name = "CMF_ORDER_NAME")
    public String getCmfOrderName() {
        return cmfOrderName;
    }

    /**
     * Sets the cmf order name.
     * 
     * @param cmfOrderName the new cmf order name
     */
    public void setCmfOrderName(String cmfOrderName) {
        this.cmfOrderName = cmfOrderName;
    }

    /**
     * Gets the point money.
     * 
     * @return the point money
     */
    @Column(name = "POINT_MONEY")
    public BigDecimal getPointMoney() {
        return pointMoney;
    }

    /**
     * Sets the point money.
     * 
     * @param pointMoney the new point money
     */
    public void setPointMoney(BigDecimal pointMoney) {
        this.pointMoney = pointMoney;
    }

    /**
     * Gets the c prototype ean.
     * 
     * @return the c prototype ean
     */
    @Column(name = "C_PROTOTYPE_EAN")
    public String getcPrototypeEan() {
        return cPrototypeEan;
    }

    /**
     * Sets the c prototype ean.
     * 
     * @param cPrototypeEan the new c prototype ean
     */
    public void setcPrototypeEan(String cPrototypeEan) {
        this.cPrototypeEan = cPrototypeEan;
    }

    /**
     * Gets the invoice id.
     * 
     * @return the invoice id
     */
    @Column(name = "INVOICE_ID")
    public String getInvoiceId() {
        return invoiceId;
    }

    /**
     * Sets the invoice id.
     * 
     * @param invoiceId the new invoice id
     */
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * Gets the c prototype id.
     * 
     * @return the c prototype id
     */
    @Column(name = "C_PROTOTYPE_ID")
    public String getcPrototypeId() {
        return cPrototypeId;
    }

    /**
     * Sets the c prototype id.
     * 
     * @param cPrototypeId the new c prototype id
     */
    public void setcPrototypeId(String cPrototypeId) {
        this.cPrototypeId = cPrototypeId;
    }

    /**
     * Gets the b2c business order.
     * 
     * @return the b2c business order
     */
    @Column(name = "B2C_BUSINESS_ORDER")
    public String getB2cBusinessOrder() {
        return b2cBusinessOrder;
    }

    /**
     * Sets the b2c business order.
     * 
     * @param b2cBusinessOrder the new b2c business order
     */
    public void setB2cBusinessOrder(String b2cBusinessOrder) {
        this.b2cBusinessOrder = b2cBusinessOrder;
    }

    /**
     * Gets the over sale qty.
     * 
     * @return the over sale qty
     */
    @Column(name = "OVER_SALE_QTY")
    public BigDecimal getOverSaleQty() {
        return overSaleQty;
    }

    /**
     * Sets the over sale qty.
     * 
     * @param overSaleQty the new over sale qty
     */
    public void setOverSaleQty(BigDecimal overSaleQty) {
        this.overSaleQty = overSaleQty;
    }

    /**
     * Gets the deli priority.
     * 
     * @return the deli priority
     */
    @Column(name = "DELI_PRIORITY")
    public Integer getDeliPriority() {
        return deliPriority;
    }

    /**
     * Sets the deli priority.
     * 
     * @param deliPriority the new deli priority
     */
    public void setDeliPriority(Integer deliPriority) {
        this.deliPriority = deliPriority;
    }

    /**
     * Gets the purchase send status.
     * 
     * @return the purchase send status
     */
    @Column(name = "PURCHASE_SEND_STATUS")
    public Integer getPurchaseSendStatus() {
        return purchaseSendStatus;
    }

    /**
     * Sets the purchase send status.
     * 
     * @param purchaseSendStatus the new purchase send status
     */
    public void setPurchaseSendStatus(Integer purchaseSendStatus) {
        this.purchaseSendStatus = purchaseSendStatus;
    }

    /**
     * Gets the custom order package id.
     * 
     * @return the custom order package id
     */
    @Column(name = "CUSTOM_ORDER_PACKAGE_ID")
    public String getCustomOrderPackageId() {
        return customOrderPackageId;
    }

    /**
     * Sets the custom order package id.
     * 
     * @param customOrderPackageId the new custom order package id
     */
    public void setCustomOrderPackageId(String customOrderPackageId) {
        this.customOrderPackageId = customOrderPackageId;
    }

    /**
     * Gets the ref goods code.
     * 
     * @return the ref goods code
     */
    @Column(name = "REF_GOODS_CODE")
    public String getRefGoodsCode() {
        return refGoodsCode;
    }

    /**
     * Sets the ref goods code.
     * 
     * @param refGoodsCode the new ref goods code
     */
    public void setRefGoodsCode(String refGoodsCode) {
        this.refGoodsCode = refGoodsCode;
    }

    /**
     * Gets the source system.
     * 
     * @return the source system
     */
    @Column(name = "SOURCE_SYSTEM")
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets the source system.
     * 
     * @param sourceSystem the new source system
     */
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    /**
     * Gets the checks if is invoice.
     * 
     * @return the checks if is invoice
     */
    @Column(name = "IS_INVOICE")
    public String getIsInvoice() {
        return isInvoice;
    }

    /**
     * Sets the checks if is invoice.
     * 
     * @param isInvoice the new checks if is invoice
     */
    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    /**
     * Gets the mutiple ddeli flag.
     * 
     * @return the mutiple ddeli flag
     */
    @Column(name = "MUTIPLE_DELI_FLAG")
    public String getMutipleDeliFlag() {
        return mutipleDeliFlag;
    }

    /**
     * Sets the mutiple ddeli flag.
     * 
     * @param mutipleDeliFlag the new mutiple ddeli flag
     */
    public void setMutipleDeliFlag(String mutipleDeliFlag) {
        this.mutipleDeliFlag = mutipleDeliFlag;
    }

    /**
     * Gets the serial number2.
     * 
     * @return the serial number2
     */
    @Column(name = "SERIAL_NUMBER2")
    public String getSerialNumber2() {
        return serialNumber2;
    }

    /**
     * Sets the serial number2.
     * 
     * @param serialNumber2 the new serial number2
     */
    public void setSerialNumber2(String serialNumber2) {
        this.serialNumber2 = serialNumber2;
    }

    /**
     * Gets the view number1.
     * 
     * @return the view number1
     */
    @Column(name = "VIEW_NUMBER1")
    public String getViewNumber1() {
        return viewNumber1;
    }

    /**
     * Sets the view number1.
     * 
     * @param viewNumber1 the new view number1
     */
    public void setViewNumber1(String viewNumber1) {
        this.viewNumber1 = viewNumber1;
    }

    /**
     * Gets the view number2.
     * 
     * @return the view number2
     */
    @Column(name = "VIEW_NUMBER2")
    public String getViewNumber2() {
        return viewNumber2;
    }

    /**
     * Sets the view number2.
     * 
     * @param viewNumber2 the new view number2
     */
    public void setViewNumber2(String viewNumber2) {
        this.viewNumber2 = viewNumber2;
    }

    /**
     * Gets the view flag1.
     * 
     * @return the view flag1
     */
    @Column(name = "VIEW_FLAG1")
    public String getViewFlag1() {
        return viewFlag1;
    }

    /**
     * Sets the view flag1.
     * 
     * @param viewFlag1 the new view flag1
     */
    public void setViewFlag1(String viewFlag1) {
        this.viewFlag1 = viewFlag1;
    }

    /**
     * Gets the view flag2.
     * 
     * @return the view flag2
     */
    @Column(name = "VIEW_FLAG2")
    public String getViewFlag2() {
        return viewFlag2;
    }

    /**
     * Sets the view flag2.
     * 
     * @param viewFlag2 the new view flag2
     */
    public void setViewFlag2(String viewFlag2) {
        this.viewFlag2 = viewFlag2;
    }

    /**
     * Gets the energy saved flag.
     * 
     * @return the energy saved flag
     */
    @Column(name = "ENERGY_SAVED_FLAG")
    public String getEnergySavedFlag() {
        return energySavedFlag;
    }

    /**
     * Sets the energy saved flag.
     * 
     * @param energySavedFlag the new energy saved flag
     */
    public void setEnergySavedFlag(String energySavedFlag) {
        this.energySavedFlag = energySavedFlag;
    }

    /**
     * Gets the tocountry flag.
     * 
     * @return the tocountry flag
     */
    @Column(name = "TOCOUNTRY_FLAG")
    public String getTocountryFlag() {
        return tocountryFlag;
    }

    /**
     * Sets the tocountry flag.
     * 
     * @param tocountryFlag the new tocountry flag
     */
    public void setTocountryFlag(String tocountryFlag) {
        this.tocountryFlag = tocountryFlag;
    }

    /**
     * Gets the special return flag.
     * 
     * @return the special return flag
     */
    @Column(name = "SPECIAL_RETURN_FLAG")
    public String getSpecialReturnFlag() {
        return specialReturnFlag;
    }

    /**
     * Sets the special return flag.
     * 
     * @param specialReturnFlag the new special return flag
     */
    public void setSpecialReturnFlag(String specialReturnFlag) {
        this.specialReturnFlag = specialReturnFlag;
    }

    /**
     * Gets the real sales person.
     * 
     * @return the real sales person
     */
    @Column(name = "REAL_SALES_PERSON")
    public String getRealSalesPerson() {
        return realSalesPerson;
    }

    /**
     * Sets the real sales person.
     * 
     * @param realSalesPerson the new real sales person
     */
    public void setRealSalesPerson(String realSalesPerson) {
        this.realSalesPerson = realSalesPerson;
    }

    /**
     * Gets the history flag.
     * 
     * @return the history flag
     */
    @Column(name = "HISTORY_FLAG")
    public String getHistoryFlag() {
        return historyFlag;
    }

    /**
     * Sets the history flag.
     * 
     * @param historyFlag the new history flag
     */
    public void setHistoryFlag(String historyFlag) {
        this.historyFlag = historyFlag;
    }

    /**
     * Gets the shipped amount.
     * 
     * @return the shipped amount
     */
    @Column(name = "SHIPPED_AMOUNT")
    public BigDecimal getShippedAmount() {
        return shippedAmount;
    }

    /**
     * Sets the shipped amount.
     * 
     * @param shippedAmount the new shipped amount
     */
    public void setShippedAmount(BigDecimal shippedAmount) {
        this.shippedAmount = shippedAmount;
    }

}