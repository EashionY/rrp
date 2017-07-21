package com.rrenpin.entity;

import java.io.Serializable;
/**
 * ְλ��λ��
 * @author Eashion
 *
 */
public class Job implements Serializable{
	
	private static final long serialVersionUID = 2226847999785051000L;
	
	//��¼id
	private Integer id;
    //��λ���
    private String jobNo;
    //��λ��
    private String jobName;
    //��λ�ĸ�ϵ���
    private String parentNo;
    //��ϵ��
    private String parentName;
    //��λ����ϵ���
    private String grandNo;
    //��λ����ϵ��
    private String grandName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getGrandNo() {
        return grandNo;
    }

    public void setGrandNo(String grandNo) {
        this.grandNo = grandNo;
    }

    public String getGrandName() {
        return grandName;
    }

    public void setGrandName(String grandName) {
        this.grandName = grandName;
    }

	@Override
	public String toString() {
		return "Job [id=" + id + ", jobNo=" + jobNo + ", jobName=" + jobName + ", parentNo=" + parentNo
				+ ", parentName=" + parentName + ", grandNo=" + grandNo + ", grandName=" + grandName + "]";
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
		Job other = (Job) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}