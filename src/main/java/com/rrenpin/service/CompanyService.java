package com.rrenpin.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rrenpin.entity.Company;

public interface CompanyService {

	/**
	 * 发送邮件
	 * @param userId
	 * @param email
	 */
	public void sendEmail(HttpServletRequest request,int userId,String email);
	
	/**
	 * 验证邮箱
	 * @param authCode
	 */
	public void verifyEmail(HttpServletRequest request,String email,String authCode);
	
	/**
	 * 检查邮箱激活状态
	 * @param email
	 * @return
	 */
	public boolean checkStatus(String email);
	
	/**
	 * 填写公司名称
	 * @param name
	 * @param email
	 */
	public void setCompanyName(String name,String email);
	
	/**
	 * 查看公司信息
	 * @param email
	 * @return
	 */
	public Company findCompanyInfo(String email);
	
	/**
	 * 添加/完善公司信息
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
	 * 公司详情
	 * @param companyId
	 * @return
	 */
	public Company companyDetail(int companyId);
	
	/**
	 * 查询所有的公司信息  
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllCompany(String status,int page,int pageSize);
	
	/**
	 * 审核公司
	 * @param companyId
	 * @param status
	 * @return
	 */
	public String dealCompany(int companyId,String status);
}
