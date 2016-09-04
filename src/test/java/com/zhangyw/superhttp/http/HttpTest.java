package com.zhangyw.superhttp.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpTest {
	public static void main(String[] args) throws IOException {
		getTest();
	}
	
	public static void getTest() throws IOException{
		String username = "哈哈";
//		String url = "http://weixin.sogou.com/weixin?type=1&query=LittleFC&ie=utf8&_sug_=n&_sug_type_=";
		String url = "http://www.jianshu.com/search/do?q="+URLEncoder.encode(username,"utf-8")+"&type=users";
//		String url = "http://www.baidu.com";
		System.out.println(url);
		Http http = new Http();
		GetMethod get = http.get(url,Http.CLIENT_TYPE_BOWSER);
		System.out.println(get.getStatusCode());
		System.out.println(get.getResponseBodyAsString());
	}
	public static void postTest() throws IOException{
		Http http = new Http();
		String url = "http://www.tuling123.com/openapi/api";
		Map param = new HashMap();
		param.put("key", "ee937e2cc2a71cd93e60fed652212a70");
		param.put("info", "���챱������");
		param.put("userid", "12345678");
		System.out.println(param);
		PostMethod post = http.post(url, param,Http.CLIENT_TYPE_BOWSER);
		System.out.println(post.getResponseBodyAsString());
	}
}
