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
	 * 上传多张图片
	 * @param request
	 * @param userId 上传用户的id
	 * @return 图片路径集合
	 * @throws IOException
	 */
	public static List<String> uploadImg(HttpServletRequest request,String userId) throws IOException{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        String imgpath = "D:\\rrpUpload\\";
        imgpath += userId+File.separator;
        List<String> paths = new ArrayList<String>();
        //判断request是否有文件上传，即多部分请求 
        if(multipartResolver.isMultipart(request)){
        	//转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> ite = multiRequest.getFileNames(); 
            while(ite.hasNext()){
            	String displayImgpath = "images/"+userId+File.separator;
            	//取得上传文件
                MultipartFile file = multiRequest.getFile(ite.next());  
                if(!file.isEmpty()){
                	String targetImgpath = imgpath+file.getOriginalFilename();
                    File localFile = new File(targetImgpath);
                    //判断路径是否存在，若不存在，则新建文件夹
            		if(!localFile.exists()){
                        localFile.mkdirs();
                    }
                    try {
                        file.transferTo(localFile); //将上传文件写到服务器上指定的文件  
                        displayImgpath += file.getOriginalFilename();
                        paths.add(displayImgpath);
                    } catch (IllegalStateException e) {
                        throw new IllegalStateException("上传失败");
                    } catch (IOException e) {
                        throw new IOException("上传失败");
                    }
                }
            }
        }
        return paths;
	}
}
