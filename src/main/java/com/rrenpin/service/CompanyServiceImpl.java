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
		///�ʼ�������  
        StringBuffer sb=new StringBuffer("�������������֤���伴�ɿ�ͨ��Ƹ��30��������Ч������ֻ��ʹ��һ�Σ��뾡����֤���䣡</br>");  
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
			throw new EmailException("�����ʼ�ʧ��");
		}
	}

	public void verifyEmail(HttpServletRequest request,String email,String authCode) {
		Company company = companyMapper.findByEmail(email);
		if(company != null){
			//�������δ����״̬
			if(company.getStatus().equals("0")){
				HttpSession session = request.getSession();
				String code = (String)session.getAttribute("authCode");
				System.out.println(code);
				if(code==null || code.trim().isEmpty()){
					throw new EmailException("�������ѹ���");
				}
				if(!authCode.equals(code)){
					throw new EmailException("�����벻��ȷ");
				}
				//��״̬��Ϊ�Ѽ���
				company.setStatus("1");
				companyMapper.updateByPrimaryKeySelective(company);
				//���session
				session.setAttribute("authCode", null);
			}
		}else{
			throw new EmailException("������δע��(�����ַ������)");
		}
	}

}
