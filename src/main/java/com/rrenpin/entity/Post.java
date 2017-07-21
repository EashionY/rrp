package com.rrenpin.entity;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable{

	private static final long serialVersionUID = -2771145349656056619L;
	
	//��¼id
    private Integer id;
    //��˾id
    private Integer companyId;
    //ְλ����
    private String name;
    //н�����
    private String salary;
    //�����ص�
    private String region;
    //��������
    private String workExp;
    //ѧ��Ҫ��
    private String degree;
    //ְλ����
    private String workType;
    //����ʱ��
    private Date postTime;
    //ְλ�ջ�
    private String benefits;
    //��λְ��
    private String duty;
    //��ְҪ��
    private String requirement;
    //״̬("1"��Ƹ�У�"0"��Ƹ����)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Post [id=" + id + ", companyId=" + companyId + ", name=" + name + ", salary=" + salary + ", region="
				+ region + ", workExp=" + workExp + ", degree=" + degree + ", workType=" + workType + ", postTime="
				+ postTime + ", benefits=" + benefits + ", duty=" + duty + ", requirement=" + requirement + ", status="
				+ status + "]";
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
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    
}