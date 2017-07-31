package com.rrenpin.service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.rrenpin.dao.CompanyMapper;
import com.rrenpin.entity.Company;
import com.rrenpin.exception.EmailException;
import com.rrenpin.util.SendEmail;
import com.rrenpin.util.Util;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Resource
	private CompanyMapper companyMapper;
	
	public void sendEmail(HttpServletRequest request,int userId,String email) {
		Company company = companyMapper.findByEmail(email);
		if(company==null){
			company = new Company();
			company.setEmail(email);
			company.setUserId(userId);
			companyMapper.insertSelective(company);
		}else{
			company.setUserId(userId);
			company.setStatus("0");
			companyMapper.updateByPrimaryKeySelective(company);
		}
		String src = ""+System.currentTimeMillis();
		String authCode = Util.encode2hex(src);
		HttpSession session = request.getSession();
		session.setAttribute("authCode", authCode);
		///邮件的内容  
        StringBuffer sb=new StringBuffer("点击下面链接验证邮箱即可开通招聘，30分钟内有效，链接只能使用一次，请尽快验证邮箱！</br>");  
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
				HttpSession session = request.getSession();
				String code = (String)session.getAttribute("authCode");
				System.out.println(code);
				if(code==null || code.trim().isEmpty()){
					throw new EmailException("激活码已过期");
				}
				if(!authCode.equals(code)){
					throw new EmailException("激活码不正确");
				}
				//把状态改为已激活
				company.setStatus("1");
				companyMapper.updateByPrimaryKeySelective(company);
				//清空session
				session.setAttribute("authCode", null);
			}
		}else{
			throw new EmailException("该邮箱未注册(邮箱地址不存在)");
		}
	}

}
