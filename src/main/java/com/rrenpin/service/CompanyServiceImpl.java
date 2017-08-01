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
				throw new EmailException("�������Ѵ���");
			}
			company.setStatus("0");
			companyMapper.updateByPrimaryKeySelective(company);
		}
		//���ɼ�����
		String src = ""+System.currentTimeMillis();
		String authCode = Util.encode2hex(src);
		//��������������ݿ�
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
		///�ʼ�������  
        StringBuffer sb = new StringBuffer("�������������֤���伴�ɿ�ͨ��Ƹ��������Ч������ֻ��ʹ��һ�Σ��뾡����֤���䣡</br>");  
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
				Authcode authcode = authcodeMapper.findByEmail(email);
				if(authcode==null){
					throw new EmailException("�����ַ����");
				}
				//��ȡ������
				String code = authcode.getAuthCode();
				System.out.println(code);
				//��ȡ�����뷢��ʱ��
				Date authTime = authcode.getAuthTime();
				long day = (System.currentTimeMillis()-authTime.getTime())/(24*60*60*1000);
				System.out.println("day:"+day);
				if(code==null || day>=1){
					throw new EmailException("�������ѹ���");
				}
				if(!authCode.equals(code)){
					throw new EmailException("�����벻��ȷ");
				}
				//��״̬��Ϊ�Ѽ���
				company.setStatus("1");
				companyMapper.updateByPrimaryKeySelective(company);
			}
		}else{
			throw new EmailException("������δע��(�����ַ������)");
		}
	}

	public boolean checkStatus(String email) {
		Company company = companyMapper.findByEmail(email);
		if(company==null){
			throw new NoCompanyFindException("δ�ҵ���Ӧ�Ĺ�˾");
		}
		String status = company.getStatus();
		return !status.equals("0");
	}

	public void setCompanyName(String name, String email) {
		Company company = companyMapper.findByEmail(email);
		if(company==null){
			throw new NoCompanyFindException("δ�ҵ���Ӧ�Ĺ�˾");
		}
		company.setName(name);
		int i;
		try {
			i = companyMapper.updateByPrimaryKeySelective(company);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i!=1){
			throw new CompanyException("��˾��������ʧ��");
		}
	}

	public Company findCompanyInfo(String email) {
		Company company;
		try {
			company = companyMapper.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		return company;
	}

	public Company addCompanyInfo(HttpServletRequest request, int id, String name, String logo, String address, String industry, String website, String scale,
			String financing, String intro) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Company company = companyMapper.selectByPrimaryKey(id);
		if(company==null){
			throw new NoCompanyFindException("δ�ҵ���Ӧ�Ĺ�˾");
		}
		company.setName(name);
		int userId = company.getUserId();
		//�ϴ�logoͼƬ
		String path = request.getSession().getServletContext().getRealPath("/")+"images\\upload\\"+userId;;
		String filename = "companyLogo.png";
		boolean success = Image.base64ToImage(logo, path, filename);
		if(!success){
			throw new ImgUploadException("Logo�ϴ�ʧ��");
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
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i!=1){
			throw new CompanyException("������Ϣʧ��");
		}
		return companyMapper.selectByPrimaryKey(id);
	}

	
}
