package com.rrenpin.util;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * ����Ƹ��վ
 * ��ָ���ֻ����Ͷ���
 * @author Eashion
 *
 */
public class Message {

	//��Ʒ��
	private final static String PRODUCT = "����Ƹ";
	//����URL
	private final static String URL = "http://gw.api.taobao.com/router/rest";
	//����appkey��appsecret
	private final static String APPKEY = "24536336";
	private final static String SECRET = "446fb9b941ba7ee5c4dfa28985d4587d";
	
	/**
	 * ������֤��
	 * @param phone
	 * @param verCode
	 * @param templateCode����ģ��id
	 * @return 
	 */
	public static boolean sendVerCode(String phone,String verCode,String templateCode){
        //����ģ�������
        String json="{\"code\":\""+verCode+"\",\"product\":\""+PRODUCT+"\"}";

        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);

        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //�����ش��������ڡ���Ϣ���ء��л�͸���ظò�����
        //�������û����Դ����Լ��¼��Ļ�ԱID������Ϣ����ʱ���û�ԱID��������ڣ��û����Ը��ݸû�ԱIDʶ������λ��Աʹ�������Ӧ��
        req.setExtend("");
        //�������ͣ�����ֵ����дnormal
        req.setSmsType("normal");
        //ǩ������
        req.setSmsFreeSignName("����Ƹ");
        //����ģ����������ι���{"key":"value"}��key�������������ģ���еı�����һ�£��������֮���Զ��Ÿ�����
        req.setSmsParamString(json);
        //���Ž��պ���
        req.setRecNum(phone);
        //����ģ��ID
        req.setSmsTemplateCode(templateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean tf = rsp.isSuccess();
        return tf;
	}
	
}
