package com.zhangyw.superhttp.http;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

import com.zhangyw.superhttp.https.Https;

public class Http {
	private static HttpMethod method = null;
	
	public static final String CLIENT_TYPE_CHROME="0";
	public static final String CLIENT_TYPE_IE="1";
	public static final String CLIENT_TYPE_FIREFOX="2";
	public static final String CLIENT_TYPE_SAFARI="3";
	static{
		Https.init();
	}
	public static GetMethod get(String url){
		try {
			method = new GetMethod();
			method.setURI(new URI(url,true,method.getParams().getUriCharset()));
			HttpClient client = new HttpClient();
			client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (GetMethod)method;
	}
	
	private static void head_Windows(){
		method.addRequestHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		method.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		method.addRequestHeader("Connection", "keep-alive");
		method.addRequestHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586");
		method.addRequestHeader("X-Requested-With", "XMLHttpRequest");
		method.addRequestHeader("Date", new Date().toString());
	}
}
