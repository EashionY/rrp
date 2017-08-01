package com.rrenpin.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.rrenpin.dao.AuthcodeMapper;
import com.rrenpin.dao.CompanyMapper;
import com.rrenpin.entity.Authcode;
import com.rrenpin.entity.Company;

import com.rrenpin.exception.CompanyException;
import com.rrenpin.exception.DataBaseException;
import com.rrenpin.exception.EmailException;
import com.rrenpin.exception.ImgUploadException;
import com.rrenpin.exception.NoCompanyFindException;
import com.rrenpin.util.Image;

import com.rrenpin.util.SendEmail;
import com.rrenpin.util.Util;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Resource
	private CompanyMapper companyMapper;
	
	@Resource
	private AuthcodeMapper authcodeMapper;
	
	public void sendEmail(HttpServletRequest request,int userId,String email) {
		Company company = companyMapper.findByEmail(email);
		if(company==null){
			company = new Company();
			company.setEmail(email);
			company.setUserId(userId);
			companyMapper.insertSelective(company);
		}else{
			int existUserId = company.getUserId();
			if(existUserId != userId){
				throw new EmailException("该邮箱已存在");
			}
			company.setStatus("0");
			companyMapper.updateByPrimaryKeySelective(company);
		}
		//生成激活码
		String src = ""+System.currentTimeMillis();
		String authCode = Util.encode2hex(src);
		//将激活码存入数据库
		Authcode authcode = authcodeMapper.findByEmail(email);
		if(authcode==null){
			authcode = new Authcode();
			authcode.setEmail(email);
			authcode.setAuthCode(authCode);
			authcodeMapper.insert(authcode);
		}else{
			authcode.setAuthCode(authCode);
			authcodeMapper.updateByPrimaryKeySelective(authcode);
		}
		///邮件的内容  
        StringBuffer sb = new StringBuffer("点击下面链接验证邮箱即可开通招聘，当天有效，链接只能使用一次，请尽快验证邮箱！</br>");  
        sb.append("<a href=\"http://192.168.0.20:8080/rrp/company/verifyEmail.do?email=");
        sb.append(email);
        sb.append("&authCode=");
        sb.append(authCode);
        sb.append("\">http://192.168.0.20:8080/rrp/company/verifyEmail.do?email=");  
        sb.append(email);
        sb.append("&authCode=");
        sb.append(authCode);
        sb.append("</a>");
        try {
			SendEmail.send(email, sb.toString());
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailException("发送邮件失败");
		}
	}

	public void verifyEmail(HttpServletRequest request,String email,String authCode) {
		Company company = companyMapper.findByEmail(email);
		if(company != null){
			//如果处于未激活状态
			if(company.getStatus().equals("0")){
				Authcode authcode = authcodeMapper.findByEmail(email);
				if(authcode==null){
					throw new EmailException("邮箱地址错误");
				}
				//获取激活码
				String code = authcode.getAuthCode();
				System.out.println(code);
				//获取激活码发送时间
				Date authTime = authcode.getAuthTime();
				long day = (System.currentTimeMillis()-authTime.getTime())/(24*60*60*1000);
				System.out.println("day:"+day);
				if(code==null || day>=1){
					throw new EmailException("激活码已过期");
				}
				if(!authCode.equals(code)){
					throw new EmailException("激活码不正确");
				}
				//把状态改为已激活
				company.setStatus("1");
				companyMapper.updateByPrimaryKeySelective(company);
			}
		}else{
			throw new EmailException("该邮箱未注册(邮箱地址不存在)");
		}
	}

	public boolean checkStatus(String email) {
		Company company = companyMapper.findByEmail(email);
		if(company==null){
			throw new NoCompanyFindException("未找到对应的公司");
		}
		String status = company.getStatus();
		return !status.equals("0");
	}

	public void setCompanyName(String name, String email) {
		Company company = companyMapper.findByEmail(email);
		if(company==null){
			throw new NoCompanyFindException("未找到对应的公司");
		}
		company.setName(name);
		int i;
		try {
			i = companyMapper.updateByPrimaryKeySelective(company);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i!=1){
			throw new CompanyException("公司名称设置失败");
		}
	}

	public Company findCompanyInfo(String email) {
		Company company;
		try {
			company = companyMapper.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		return company;
	}

	public Company addCompanyInfo(HttpServletRequest request, int id, String name, String logo, String address, String industry, String website, String scale,
			String financing, String intro) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Company company = companyMapper.selectByPrimaryKey(id);
		if(company==null){
			throw new NoCompanyFindException("未找到对应的公司");
		}
		company.setName(name);
		int userId = company.getUserId();
		//上传logo图片
		String path = request.getSession().getServletContext().getRealPath("/")+"images\\upload\\"+userId;;
		String filename = "companyLogo.png";
		boolean success = Image.base64ToImage(logo, path, filename);
		if(!success){
			throw new ImgUploadException("Logo上传失败");
		}
		company.setLogo("images/upload/"+userId+File.separator+filename);
		company.setAddress(address);
		company.setIndustry(industry);
		company.setWebsite(website);
		company.setScale(scale);
		company.setFinancing(financing);
		company.setIntro(intro);
		int i;
		try {
			i = companyMapper.updateByPrimaryKeySelective(company);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i!=1){
			throw new CompanyException("完善信息失败");
		}
		return companyMapper.selectByPrimaryKey(id);
	}

	
}
