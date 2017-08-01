package com.rrenpin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Image {

	/**
	 * @Descriptionmap 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * @author
	 * @param path 图片路径
	 * @return
	 */
	public static String imageToBase64(String path) {
	    byte[] data = null;
	    // 读取图片字节数组
	    try {
	        InputStream in = new FileInputStream(path);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 对字节数组Base64编码
	    BASE64Encoder encoder = new BASE64Encoder();
	    return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * @Descriptionmap 对字节数组字符串进行Base64解码并生成图片
	 * @author 
	 * @param base64 图片Base64数据
	 * @param path 图片路径
	 * @return
	 */
	public static boolean base64ToImage(String base64, String path, String filename) {
	    if (base64 == null){ // 图像数据为空
	        return false;
	    }
	    BASE64Decoder decoder = new BASE64Decoder();
	    try {
	        // Base64解码
	        byte[] bytes = decoder.decodeBuffer(base64);
	        for (int i = 0; i < bytes.length; ++i) {
	            if (bytes[i] < 0) {// 调整异常数据
	                bytes[i] += 256;
	            }
	        }
	        File localFile = new File(path);
	        //判断路径是否存在，若不存在，则新建文件夹
    		if(!localFile.exists()){
                localFile.mkdirs();
            }
    		path += File.separator+filename;
	        // 生成jpeg图片
	        OutputStream out = new FileOutputStream(path);
	        out.write(bytes);
	        out.flush();
	        out.close();
	        return true;
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return false;
	    }
	}

}
