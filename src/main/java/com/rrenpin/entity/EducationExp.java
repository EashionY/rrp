package com.rrenpin.entity;

import java.io.Serializable;

public class EducationExp implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8762763386675610794L;

	private Integer educationexpId;

    private Integer userId;

    private Integer resumeId;

    private String school;

    private String major;

    private String education;

    private String schoolTime;

    public Integer getEducationexpId() {
        return educationexpId;
    }

    public void setEducationexpId(Integer educationexpId) {
        this.educationexpId = educationexpId;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(String schoolTime) {
        this.schoolTime = schoolTime;
    }

	@Override
	public String toString() {
		return "EducationExp [educationexpId=" + educationexpId + ", userId=" + userId + ", resumeId=" + resumeId
				+ ", school=" + school + ", major=" + major + ", education=" + education + ", schoolTime=" + schoolTime
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((educationexpId == null) ? 0 : educationexpId.hashCode());
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
		EducationExp other = (EducationExp) obj;
		if (educationexpId == null) {
			if (other.educationexpId != null)
				return false;
		} else if (!educationexpId.equals(other.educationexpId))
			return false;
		return true;
	}
    
}