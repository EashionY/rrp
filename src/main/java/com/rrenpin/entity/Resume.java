package com.rrenpin.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 简历
 * @author Eashion
 *
 */
public class Resume implements Serializable{

	private static final long serialVersionUID = -968212770228063119L;
	//简历id
	private Integer id;
    //简历名字
    private String resumeName;
    //简历更新时间
    private Date updateTime;
    //应聘人姓名
    private String empName;
    //应聘人地区
    private String empRegion;
    //工作年限()
    private String workExp;
    //性别
    private String sex;
    //出生日期
    private String birth;
    //应聘人目前状态（在职/离职）
    private String status;
    //应聘人手机号码
    private String phone;
    //电子邮箱
    private String email;
    //应聘人用户id
    private Integer userId;
    //期望薪资
    private String salary;
    //应聘岗位
    private String job;
    //工作类型(全职/兼职)
    private String workType;
    //期望工作地点
    private String workArea;
    //工作3职责描述
    private String work3Description;
    //公司1名字
    private String company1;
    //工作1
    private String work1;
    //工作1任职时间
    private String work1Time;
    //工作1职责描述
    private String work1Description;
    //公司2名字
    private String company2;
    //工作2
    private String work2;
    //工作2任职时间
    private String work2Time;
    //工作2职责描述
    private String work2Description;
    //公司3名字
    private String company3;
    //工作3
    private String work3;
    //工作3任职时间
    private String work3Time;
    //项目1名字
    private String project1;
    //项目1开发时间
    private String project1Time;
    //项目1项目描述
    private String project1Description;
    //项目1职责描述
    private String duty1;
    //项目2
    private String project2;
    //项目2开发时间
    private String project2Time;
    //项目2项目描述
    private String project2Description;
    //项目2职责描述
    private String duty2;
    //项目3
    private String project3;
    //项目3开发时间
    private String project3Time;
    //项目3项目描述
    private String project3Description;
    //项目3职责描述
    private String duty3;
    //学校1名字
    private String school1;
    //专业1
    private String major1;
    //学历1
    private String education1;
    //学校1在校时间 
    private String school1Time;
    //学校2
    private String school2;
    //专业2
    private String major2;
    //学历2
    private String education2;
    //学校2在校时间
    private String school2Time;
    //技能评价
    private String skill;
    //自我描述
    private String selfEvaluation;
    //头像路径
    private String headImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpRegion() {
        return empRegion;
    }

    public void setEmpRegion(String empRegion) {
        this.empRegion = empRegion;
    }

    public String getWorkExp() {
        return workExp;
    }

    public void setWorkExp(String workExp) {
        this.workExp = workExp;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getWork3Description() {
        return work3Description;
    }

    public void setWork3Description(String work3Description) {
        this.work3Description = work3Description;
    }

    public String getCompany1() {
        return company1;
    }

    public void setCompany1(String company1) {
        this.company1 = company1;
    }

    public String getWork1() {
        return work1;
    }

    public void setWork1(String work1) {
        this.work1 = work1;
    }

    public String getWork1Time() {
        return work1Time;
    }

    public void setWork1Time(String work1Time) {
        this.work1Time = work1Time;
    }

    public String getWork1Description() {
        return work1Description;
    }

    public void setWork1Description(String work1Description) {
        this.work1Description = work1Description;
    }

    public String getCompany2() {
        return company2;
    }

    public void setCompany2(String company2) {
        this.company2 = company2;
    }

    public String getWork2() {
        return work2;
    }

    public void setWork2(String work2) {
        this.work2 = work2;
    }

    public String getWork2Time() {
        return work2Time;
    }

    public void setWork2Time(String work2Time) {
        this.work2Time = work2Time;
    }

    public String getWork2Description() {
        return work2Description;
    }

    public void setWork2Description(String work2Description) {
        this.work2Description = work2Description;
    }

    public String getCompany3() {
        return company3;
    }

    public void setCompany3(String company3) {
        this.company3 = company3;
    }

    public String getWork3() {
        return work3;
    }

    public void setWork3(String work3) {
        this.work3 = work3;
    }

    public String getWork3Time() {
        return work3Time;
    }

    public void setWork3Time(String work3Time) {
        this.work3Time = work3Time;
    }

    public String getProject1() {
        return project1;
    }

    public void setProject1(String project1) {
        this.project1 = project1;
    }

    public String getProject1Time() {
        return project1Time;
    }

    public void setProject1Time(String project1Time) {
        this.project1Time = project1Time;
    }

    public String getProject1Description() {
        return project1Description;
    }

    public void setProject1Description(String project1Description) {
        this.project1Description = project1Description;
    }

    public String getDuty1() {
        return duty1;
    }

    public void setDuty1(String duty1) {
        this.duty1 = duty1;
    }

    public String getProject2() {
        return project2;
    }

    public void setProject2(String project2) {
        this.project2 = project2;
    }

    public String getProject2Time() {
        return project2Time;
    }

    public void setProject2Time(String project2Time) {
        this.project2Time = project2Time;
    }

    public String getProject2Description() {
        return project2Description;
    }

    public void setProject2Description(String project2Description) {
        this.project2Description = project2Description;
    }

    public String getDuty2() {
        return duty2;
    }

    public void setDuty2(String duty2) {
        this.duty2 = duty2;
    }

    public String getProject3() {
        return project3;
    }

    public void setProject3(String project3) {
        this.project3 = project3;
    }

    public String getProject3Time() {
        return project3Time;
    }

    public void setProject3Time(String project3Time) {
        this.project3Time = project3Time;
    }

    public String getProject3Description() {
        return project3Description;
    }

    public void setProject3Description(String project3Description) {
        this.project3Description = project3Description;
    }

    public String getDuty3() {
        return duty3;
    }

    public void setDuty3(String duty3) {
        this.duty3 = duty3;
    }

    public String getSchool1() {
        return school1;
    }

    public void setSchool1(String school1) {
        this.school1 = school1;
    }

    public String getMajor1() {
        return major1;
    }

    public void setMajor1(String major1) {
        this.major1 = major1;
    }

    public String getEducation1() {
        return education1;
    }

    public void setEducation1(String education1) {
        this.education1 = education1;
    }

    public String getSchool1Time() {
        return school1Time;
    }

    public void setSchool1Time(String school1Time) {
        this.school1Time = school1Time;
    }

    public String getSchool2() {
        return school2;
    }

    public void setSchool2(String school2) {
        this.school2 = school2;
    }

    public String getMajor2() {
        return major2;
    }

    public void setMajor2(String major2) {
        this.major2 = major2;
    }

    public String getEducation2() {
        return education2;
    }

    public void setEducation2(String education2) {
        this.education2 = education2;
    }

    public String getSchool2Time() {
        return school2Time;
    }

    public void setSchool2Time(String school2Time) {
        this.school2Time = school2Time;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSelfEvaluation() {
        return selfEvaluation;
    }

    public void setSelfEvaluation(String selfEvaluation) {
        this.selfEvaluation = selfEvaluation;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

	@Override
	public String toString() {
		return "Resume [id=" + id + ", resumeName=" + resumeName + ", updateTime=" + updateTime + ", empName=" + empName
				+ ", empRegion=" + empRegion + ", workExp=" + workExp + ", sex=" + sex + ", birth=" + birth
				+ ", status=" + status + ", phone=" + phone + ", email=" + email + ", userId=" + userId + ", salary="
				+ salary + ", job=" + job + ", workType=" + workType + ", workArea=" + workArea + ", work3Description="
				+ work3Description + ", company1=" + company1 + ", work1=" + work1 + ", work1Time=" + work1Time
				+ ", work1Description=" + work1Description + ", company2=" + company2 + ", work2=" + work2
				+ ", work2Time=" + work2Time + ", work2Description=" + work2Description + ", company3=" + company3
				+ ", work3=" + work3 + ", work3Time=" + work3Time + ", project1=" + project1 + ", project1Time="
				+ project1Time + ", project1Description=" + project1Description + ", duty1=" + duty1 + ", project2="
				+ project2 + ", project2Time=" + project2Time + ", project2Description=" + project2Description
				+ ", duty2=" + duty2 + ", project3=" + project3 + ", project3Time=" + project3Time
				+ ", project3Description=" + project3Description + ", duty3=" + duty3 + ", school1=" + school1
				+ ", major1=" + major1 + ", education1=" + education1 + ", school1Time=" + school1Time + ", school2="
				+ school2 + ", major2=" + major2 + ", education2=" + education2 + ", school2Time=" + school2Time
				+ ", skill=" + skill + ", selfEvaluation=" + selfEvaluation + ", headImg=" + headImg + "]";
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
		Resume other = (Resume) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}