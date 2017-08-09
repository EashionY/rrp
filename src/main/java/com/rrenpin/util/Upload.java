package com.rrenpin.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class Upload {
	
	/**
	 * �ϴ�����ͼƬ
	 * @param request
	 * @param userId �ϴ��û���id
	 * @return ͼƬ·������
	 * @throws IOException
	 */
	public static List<String> uploadImg(HttpServletRequest request,String userId) throws IOException{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        String imgpath = "D:\\rrpUpload\\";
        imgpath += userId+File.separator;
        List<String> paths = new ArrayList<String>();
        //�ж�request�Ƿ����ļ��ϴ������ಿ������ 
        if(multipartResolver.isMultipart(request)){
        	//ת���ɶಿ��request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //ȡ��request�е������ļ���
            Iterator<String> ite = multiRequest.getFileNames(); 
            while(ite.hasNext()){
            	String displayImgpath = "images/"+userId+File.separator;
            	//ȡ���ϴ��ļ�
                MultipartFile file = multiRequest.getFile(ite.next());  
                if(!file.isEmpty()){
                	String targetImgpath = imgpath+file.getOriginalFilename();
                    File localFile = new File(targetImgpath);
                    //�ж�·���Ƿ���ڣ��������ڣ����½��ļ���
            		if(!localFile.exists()){
                        localFile.mkdirs();
                    }
                    try {
                        file.transferTo(localFile); //���ϴ��ļ�д����������ָ�����ļ�  
                        displayImgpath += file.getOriginalFilename();
                        paths.add(displayImgpath);
                    } catch (IllegalStateException e) {
                        throw new IllegalStateException("�ϴ�ʧ��");
                    } catch (IOException e) {
                        throw new IOException("�ϴ�ʧ��");
                    }
                }
            }
        }
        return paths;
	}
}
