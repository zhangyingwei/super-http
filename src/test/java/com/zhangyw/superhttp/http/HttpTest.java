package com.zhangyw.superhttp.http;

import java.io.IOException;

import org.apache.commons.httpclient.methods.GetMethod;

public class HttpTest {
	public static void main(String[] args) throws IOException {
		GetMethod get = Http.get("https://luolei.org/");
		System.out.println(get.getResponseBodyAsString());
	}
}
