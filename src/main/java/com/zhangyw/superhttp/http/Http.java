package com.zhangyw.superhttp.http;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

import com.zhangyw.superhttp.https.Https;

public class Http {
	private static GetMethod get = new GetMethod();
	static{
		Https.init();
	}
	public static GetMethod get(String url){
		try {
			get.setURI(new URI(url,true,get.getParams().getUriCharset()));
			HttpClient client = new HttpClient();
			client.executeMethod(get);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return get;
	}
}
