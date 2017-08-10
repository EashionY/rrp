package com.rrenpin.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.rrenpin.util.Upload;
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
			try {
				//�������쳣����ΪuserId�ظ�����Ψһ
				companyMapper.insertSelective(company);
			} catch (Exception e) {
				e.printStackTrace();
				throw new EmailException("��ʹ����֤ͨ���������");
			}
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
			//�ÿ�ʱ�䣬�����ݿ��Զ����µ�����ʱ��
			authcode.setAuthTime(null);
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
//				System.out.println(code);
				//��ȡ�����뷢��ʱ��
				Date authTime = authcode.getAuthTime();
				long day = (System.currentTimeMillis()-authTime.getTime())/(24*60*60*1000);
//				System.out.println("day:"+day);
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
			String financing, String intro, String tel, String info) throws IOException {
		request.setCharacterEncoding("UTF-8");
		Company company = companyMapper.selectByPrimaryKey(id);
		if(company==null){
			throw new NoCompanyFindException("δ�ҵ���Ӧ�Ĺ�˾");
		}
		company.setName(name);
		int userId = company.getUserId();
		if(logo!=null){
			//ȥ������ͷ
			String[] strBase64 = logo.split(",");
			String logoImg = strBase64[1];
			//windowsϵͳ����·��
			String path = "D:\\rrpUpload\\"+userId;
			//linuxϵͳ·����·���Ķ�֮����Ҫ��Ӧ�ĸ���server.xml�е�context��ǩ��
//		    String path = "";
			String filename = "companyLogo.png";
			//�ϴ�logoͼƬ
			boolean success = Image.base64ToImage(logoImg, path, filename);
			if(!success){
				throw new ImgUploadException("Logo�ϴ�ʧ��");
			}
			company.setLogo("images/"+userId+File.separator+filename);
		}else{
			company.setLogo(null);
		}
		company.setAddress(address);
		company.setIndustry(industry);
		company.setWebsite(website);
		company.setScale(scale);
		company.setFinancing(financing);
		company.setIntro(intro);
		//�ϴ�Ӫҵִ��
		List<String> paths = Upload.uploadImg(request, ""+userId);
		if(paths == null){
			company.setLicense(null);
		}else{
			company.setLicense(paths.get(0));
		}
		company.setTel(tel);
		company.setInfo(info);
		company.setStatus("2");
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

	public Company companyDetail(int companyId) {
		Company company;
		try {
			company = companyMapper.selectByPrimaryKey(companyId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		return company;
	}

	public List<Map<String, Object>> listAllCompany(String status, int page, int pageSize) {
		List<Map<String, Object>> result;
		try {
			result = companyMapper.listAllCompany(status, page, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		return result;
	}

	public String dealCompany(int companyId, String status) {
		Company company = new Company();
		company.setId(companyId);
		company.setStatus(status);
		int i = companyMapper.updateByPrimaryKeySelective(company);
		if(i!=1){
			throw new CompanyException("��˴���ʧ��"); 
		}
		return status;
	}

	
}
