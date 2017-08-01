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
	 * @Descriptionmap ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
	 * @author
	 * @param path ͼƬ·��
	 * @return
	 */
	public static String imageToBase64(String path) {
	    byte[] data = null;
	    // ��ȡͼƬ�ֽ�����
	    try {
	        InputStream in = new FileInputStream(path);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // ���ֽ�����Base64����
	    BASE64Encoder encoder = new BASE64Encoder();
	    return encoder.encode(data);// ����Base64��������ֽ������ַ���
	}

	/**
	 * @Descriptionmap ���ֽ������ַ�������Base64���벢����ͼƬ
	 * @author 
	 * @param base64 ͼƬBase64����
	 * @param path ͼƬ·��
	 * @return
	 */
	public static boolean base64ToImage(String base64, String path, String filename) {
	    if (base64 == null){ // ͼ������Ϊ��
	        return false;
	    }
	    BASE64Decoder decoder = new BASE64Decoder();
	    try {
	        // Base64����
	        byte[] bytes = decoder.decodeBuffer(base64);
	        for (int i = 0; i < bytes.length; ++i) {
	            if (bytes[i] < 0) {// �����쳣����
	                bytes[i] += 256;
	            }
	        }
	        File localFile = new File(path);
	        //�ж�·���Ƿ���ڣ��������ڣ����½��ļ���
    		if(!localFile.exists()){
                localFile.mkdirs();
            }
    		path += File.separator+filename;
	        // ����jpegͼƬ
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
