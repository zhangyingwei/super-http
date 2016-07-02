package com.zhangyw.superhttp.http;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

public abstract class AbsHttp {
	public abstract GetMethod get(String url);
	public abstract GetMethod get(String url,Map params);
	public abstract GetMethod get(String url,Map params,int type);
	public abstract GetMethod get(String url,int type);
	public abstract PostMethod post(String url,Map params);
	public abstract PostMethod post(String url,Map params,int type);
	public abstract PutMethod put(String url,JSONObject params);
	public abstract PutMethod put(String url,JSONObject params,int type);
	public abstract DeleteMethod delete(String url,JSONObject params);
	public abstract DeleteMethod delete(String url,JSONObject params,int type);
	
	protected void head_Browser(HttpMethod method){
		method.addRequestHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		method.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		method.addRequestHeader("Connection", "keep-alive");
		method.addRequestHeader("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586 Firefox/43.0");
		method.addRequestHeader("X-Requested-With", "XMLHttpRequest");
		method.addRequestHeader("Date", new Date().toString());
	}
}
