package com.czxy.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


public class SMSUtils {


	/*
	pom.xml
	<dependency>
	  <groupId>com.aliyun</groupId>
	  <artifactId>aliyun-java-sdk-core</artifactId>
	  <version>4.0.3</version>
	</dependency>
	*/
	static final String accessKeyId = "LTAIrjve1cYho31b";
	static final String accessKeySecret = "rcbKKAmRcm6bkqnLWQnhZy590lnZQB";

			public static CommonResponse sendSms(String phone,String code){

				//初始化acsClient,暂不支持region化
				DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessKeySecret);
				IAcsClient client = new DefaultAcsClient(profile);
				//组装请求对象-具体描述见控制台-文档部分内容
				CommonRequest request = new CommonRequest();
				//request.setProtocol(ProtocolType.HTTPS);
				request.setMethod(MethodType.POST);
				request.setDomain("dysmsapi.aliyuncs.com");
				request.setVersion("2017-05-25");
				request.setAction("SendSms");

				//必填:待发送手机号
				request.putQueryParameter("PhoneNumbers", phone);
				//必填:短信签名-可在短信控制台中找到
				request.putQueryParameter("SignName", "仁哥来信");
				//必填:短信模板-可在短信控制台中找到
				request.putQueryParameter("TemplateCode", "SMS_165414627");

				//可选:模板中的变量替换JSON串,如模板内容为"您的验证码为${code}"时,此处的值为
				request.putQueryParameter("TemplateParam", "{\"code\": \""+code+"\"}");


				//可自助调整超时时间
				System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
				System.setProperty("sun.net.client.defaultReadTimeout", "10000");


				try {
					CommonResponse response = client.getCommonResponse(request);
					return response;
				} catch (ServerException e) {
					e.printStackTrace();
				} catch (ClientException e) {
					e.printStackTrace();
				}

				return null;
			}


		}
