package com.rrenpin.util;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 给指定手机发送短信
 * @author Eashion
 *
 */
public class AliSms {

	//短信固定参数，产品名
	private final static String product = "任人聘";
	//官网URL
	private final static String URL = "http://gw.api.taobao.com/router/rest";
	//配置appkey和appsecret
	private final static String APPKEY = "24536336";
	private final static String SECRET = "446fb9b941ba7ee5c4dfa28985d4587d";
	
	/**
	 * 发送验证码
	 * @param phone
	 * @param verCode
	 * @param templateCode短信模板id
	 * @return 
	 */
	public static boolean sendCode(String phone,String code,String templateCode,String signName){
        //短信模板的内容
        String json="{\"code\":\""+code+"\",\"product\":\""+product+"\"}";

        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);

        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //公共回传参数，在“消息返回”中会透传回该参数；
        //举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
        req.setExtend("");
        //短信类型，传入值请填写normal
        req.setSmsType("normal");
        //签名名称
        req.setSmsFreeSignName(signName);
        //短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。
        req.setSmsParamString(json);
        //短信接收号码
        req.setRecNum(phone);
        //短信模板ID
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
