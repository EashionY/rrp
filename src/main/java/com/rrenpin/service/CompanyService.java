package com.rrenpin.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rrenpin.entity.Company;

public interface CompanyService {

	/**
	 * �����ʼ�
	 * @param userId
	 * @param email
	 */
	public void sendEmail(HttpServletRequest request,int userId,String email);
	
	/**
	 * ��֤����
	 * @param authCode
	 */
	public void verifyEmail(HttpServletRequest request,String email,String authCode);
	
	/**
	 * ������伤��״̬
	 * @param email
	 * @return
	 */
	public boolean checkStatus(String email);
	
	/**
	 * ��д��˾����
	 * @param name
	 * @param email
	 */
	public void setCompanyName(String name,String email);
	
	/**
	 * �鿴��˾��Ϣ
	 * @param email
	 * @return
	 */
	public Company findCompanyInfo(String email);
	
	/**
	 * ���/���ƹ�˾��Ϣ
	 * @param int
	 * @param name
	 * @param logo
	 * @param address
	 * @param industry
	 * @param website
	 * @param scale
	 * @param financing
	 * @param intro
	 * @return 
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public Company addCompanyInfo(HttpServletRequest request,int id,String name,String logo,String address,String industry,String website,
			String scale,String financing,String intro,String tel,String info) throws UnsupportedEncodingException, IOException;
	
	/**
	 * ��˾����
	 * @param companyId
	 * @return
	 */
	public Company companyDetail(int companyId);
	
	/**
	 * ��ѯ���еĹ�˾��Ϣ  
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllCompany(String status,int page,int pageSize);
	
	/**
	 * ��˹�˾
	 * @param companyId
	 * @param status
	 * @return
	 */
	public String dealCompany(int companyId,String status);
}
