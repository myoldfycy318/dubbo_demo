package com.pojo.convert;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: ConvertOrder
* @Description: ‘兑吧订单’表。
* @author caobo
* @date 2015-10-27 下午15:48
*
*/
public class ConvertOrder implements Serializable{
    /**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

    /**
     * @Fields id : 订单号
     */
	private Integer id;

    /**
     * @Fields userId : 用户id
     */
    private Integer userId;

    /**
     * @Fields orderNum : 兑吧订单号
     */
    private String orderNum;

    /**
     * @Fields status : 订单状态
     */
    private Integer status;

    /**
     * @Fields waitAudit : 是否需要审核
     */
    private String waitAudit;

    /**
     * @Fields credits : 消耗的积分数
     */
    private String credits;

    /**
     * @Fields type : 兑换类型
     */
    private String type;

    /**
     * @Fields facePrice : 兑换商品的市价 单位为分
     */
    private String facePrice;

    /**
     * @Fields actualPrice : 此次兑换实际扣除开发者账户费用
     */
    private String actualPrice;

    /**
     * @Fields ip : 用户ip
     */
    private String ip;

    /**
     * @Fields cratetime : 创建时间
     */
    private Date crateTime;

    /**
     * @Fields updatetime : 更新时间
     */
    private Date updateTime;

    /**
     * @Fields params : 参数详情信息
     */
    private String params;


	/**
     * @Fields back1 : 备用字段1
     */
    private String back1;

	/**
     * @Fields description : 描述信息
     */
    private String description;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBack1() {
		return back1;
	}

	public void setBack1(String back1) {
		this.back1 = back1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWaitAudit() {
		return waitAudit;
	}

	public void setWaitAudit(String waitAudit) {
		this.waitAudit = waitAudit;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFacePrice() {
		return facePrice;
	}

	public void setFacePrice(String facePrice) {
		this.facePrice = facePrice;
	}

	public String getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}



    public Date getCrateTime() {
		return crateTime;
	}

	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}