package com.rrenpin.entity;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7456506486828224823L;

	private Integer postId;

    private Integer companyId;

    private String postName;

    private String salary;

    private String region;

    private String workExp;
    //学历（中专及以下=1，高中=2，大专=3，本科=4， 硕士=5，博士=6）
    private String degree;

    private String workType;

    private Date postTime;

    private String benefits;

    private String duty;

    private String requirement;

    private String postStatus;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getWorkExp() {
        return workExp;
    }

    public void setWorkExp(String workExp) {
        this.workExp = workExp;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", companyId=" + companyId + ", postName=" + postName + ", salary=" + salary
				+ ", region=" + region + ", workExp=" + workExp + ", degree=" + degree + ", workType=" + workType
				+ ", postTime=" + postTime + ", benefits=" + benefits + ", duty=" + duty + ", requirement="
				+ requirement + ", postStatus=" + postStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
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
		Post other = (Post) obj;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		return true;
	}
    
}