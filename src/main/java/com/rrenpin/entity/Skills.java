package com.rrenpin.entity;

import java.io.Serializable;

public class Skills implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1472019896576280944L;

	private Integer skillId;

    private Integer userId;

    private Integer resumeId;

    private String skillName;

    private String skillLevel;

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
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

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

	@Override
	public String toString() {
		return "Skills [skillId=" + skillId + ", userId=" + userId + ", resumeId=" + resumeId + ", skillName="
				+ skillName + ", skillLevel=" + skillLevel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((skillId == null) ? 0 : skillId.hashCode());
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
		Skills other = (Skills) obj;
		if (skillId == null) {
			if (other.skillId != null)
				return false;
		} else if (!skillId.equals(other.skillId))
			return false;
		return true;
	}
    
}