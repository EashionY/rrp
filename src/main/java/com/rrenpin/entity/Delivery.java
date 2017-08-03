package com.rrenpin.entity;

import java.io.Serializable;
import java.util.Date;

public class Delivery implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8430372177135861900L;

	private Integer deliveryId;

    private Integer resumeId;

    private Integer companyId;

    private Integer postId;

    private String deliveryStatus;

    private Date deliveryTime;

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

	@Override
	public String toString() {
		return "Delivery [deliveryId=" + deliveryId + ", resumeId=" + resumeId + ", companyId=" + companyId
				+ ", postId=" + postId + ", deliveryStatus=" + deliveryStatus + ", deliveryTime=" + deliveryTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliveryId == null) ? 0 : deliveryId.hashCode());
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
		if (deliveryId == null) {
			if (other.deliveryId != null)
				return false;
		} else if (!deliveryId.equals(other.deliveryId))
			return false;
		return true;
	}
    
}