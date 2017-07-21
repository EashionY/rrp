package com.rrenpin.entity;

import java.io.Serializable;
/**
 * ��˾
 * @author Eashion
 *
 */
public class Company implements Serializable{

	private static final long serialVersionUID = 7957627660842757016L;
	//��˾id
    private Integer id;
    //��˾����
    private String name;
    //��˾logo
    private String logo;
    //��˾�����ַ
    private String email;
    //��˾��Ϣ
    private String info;
    //��ַ
    private String address;
    //��ҵ
    private String industry;
    //��˾��ģ
    private String scale;
    //��˾��ַ
    private String website;
    //�������
    private String financing;
    //�û�id
    private Integer userId;
    //��˾�绰
    private String tel;
    //��˾���
    private String intro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFinancing() {
        return financing;
    }

    public void setFinancing(String financing) {
        this.financing = financing;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", logo=" + logo + ", email=" + email + ", info=" + info
				+ ", address=" + address + ", industry=" + industry + ", scale=" + scale + ", website=" + website
				+ ", financing=" + financing + ", userId=" + userId + ", tel=" + tel + ", intro=" + intro + "]";
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
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}