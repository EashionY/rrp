package com.rrenpin.entity;

import java.io.Serializable;

public class WorkExp implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8209130517300973915L;

	private Integer workexpId;

    private Integer userId;

    private Integer resumeId;

    private String company;

    private String work;

    private String workTime;

    private String workDescription;

    public Integer getWorkexpId() {
        return workexpId;
    }

    public void setWorkexpId(Integer workexpId) {
        this.workexpId = workexpId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

	@Override
	public String toString() {
		return "WorkExp [workexpId=" + workexpId + ", userId=" + userId + ", resumeId=" + resumeId + ", company="
				+ company + ", work=" + work + ", workTime=" + workTime + ", workDescription=" + workDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((workexpId == null) ? 0 : workexpId.hashCode());
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
		WorkExp other = (WorkExp) obj;
		if (workexpId == null) {
			if (other.workexpId != null)
				return false;
		} else if (!workexpId.equals(other.workexpId))
			return false;
		return true;
	}
    
}