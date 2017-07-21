package com.rrenpin.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 简历投递
 * @author Eashion
 *
 */
public class Delivery implements Serializable{
	
	private static final long serialVersionUID = 36525216928024183L;
	
	//记录id
    private Integer id;
    //简历id
    private Integer resumeId;
    //公司id
    private Integer companyId;
    //简历状态
    private String status;
    //投递时间
    private Date deliveryTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", resumeId=" + resumeId + ", companyId=" + companyId + ", status=" + status
				+ ", deliveryTime=" + deliveryTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    
}