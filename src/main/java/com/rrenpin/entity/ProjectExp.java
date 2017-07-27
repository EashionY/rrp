package com.rrenpin.entity;

import java.io.Serializable;

public class ProjectExp implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1515754590028493060L;

	private Integer projectexpId;

    private Integer userId;

    private Integer resumeId;

    private String project;

    private String projectTime;

    private String projectDescription;

    private String duty;

    public Integer getProjectexpId() {
        return projectexpId;
    }

    public void setProjectexpId(Integer projectexpId) {
        this.projectexpId = projectexpId;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

	@Override
	public String toString() {
		return "ProjectExp [projectexpId=" + projectexpId + ", userId=" + userId + ", resumeId=" + resumeId
				+ ", project=" + project + ", projectTime=" + projectTime + ", projectDescription=" + projectDescription
				+ ", duty=" + duty + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectexpId == null) ? 0 : projectexpId.hashCode());
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
		ProjectExp other = (ProjectExp) obj;
		if (projectexpId == null) {
			if (other.projectexpId != null)
				return false;
		} else if (!projectexpId.equals(other.projectexpId))
			return false;
		return true;
	}
    
}