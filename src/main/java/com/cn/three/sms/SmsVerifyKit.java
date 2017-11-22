package com.cn.three.sms;


import com.alibaba.fastjson.JSON;
import sms.utils.MobClient;

import java.util.Map;

/**
 * 服务端发起验证请求验证移动端(手机)发送的短信
 * @author Administrator
 *
 */
public class SmsVerifyKit {
	
	private String appkey;
	private String phone ;
	private String zone ;
	private String code ;

	/**
	 * 
	 * @param appkey 应用KEY
	 * @param phone 电话号码 xxxxxxxxx
	 * @param zone 区号 86
	 * @param code 验证码 xx
	 */
	public SmsVerifyKit(String appkey, String phone, String zone, String code) {
		super();
		this.appkey = appkey;
		this.phone = phone;
		this.zone = zone;
		this.code = code;
	}

	/**
	 * 服务端发起验证请求验证移动端(手机)发送的短信
	 * @return
	 * @throws Exception
	 */
	public Boolean  go() throws Exception{
		
		String address = "https://webapi.sms.mob.com/sms/verify";
		MobClient client = null;
		Boolean result=false;
		try {
			client = new MobClient(address);
			client.addParam("appkey", appkey).addParam("phone", phone)
					.addParam("zone", zone).addParam("code", code);
			client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client.addRequestProperty("Accept", "application/json");
			String str = client.post();
			//return result;
			Map maps = (Map) JSON.parse(str);
			Integer status=(Integer) maps.get("status");
			if(status.equals(200)){
				result=true;
			}

		} finally {
			client.release();
			return result;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(new SmsVerifyKit("2280d89923c79", "18502682488", "86", "4090").go());
		
	}

}
