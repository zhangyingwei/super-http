package com.zhangyw.superhttp.http;

import java.io.IOException;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.zhangyw.superhttp.browser.SCookie;
import com.zhangyw.superhttp.browser.SCookieQueue;
import com.zhangyw.superhttp.https.Https;

public class Http extends AbsHttp{
	private Logger logger = Logger.getLogger(Http.class);
	/**
	 * cookie保存位置
	 */
	private String cookieCheckOutPoint;
	/**
	 * cookie队列
	 */
	private SCookieQueue cookieQueue;
	/**
	 * 浏览器模式，即每次请求都会在请求头中，附带浏览器信息
	 */
	public static final int CLIENT_TYPE_BOWSER=0;
	/**
	 * 简单模式，即每次请求不会带浏览器信息
	 */
	public static final int CLIENT_TYPE_SIMPLE=1;
	/**
	 * 初始化https请求信息
	 */
	static{
		Https.init();
	}
	
	public Http(){
		this.cookieQueue = new SCookieQueue();
		this.cookieQueue.setCheckOutPoint(this.getCookieCheckOutPoint());
	}
	@Override
	public GetMethod get(String url) {
		return this.get(url, CLIENT_TYPE_SIMPLE);
	}
	@Override
	public GetMethod get(String url, Map params) {
		return this.get(url,params,CLIENT_TYPE_SIMPLE);
	}
	@Override
	public GetMethod get(String url, Map params,int type) {
		StringBuffer sb = new StringBuffer("?");
		for(Object key:params.keySet()){
			sb.append(key).append("=").append(params.get(key)).append("&");
		}
		return this.get(url+sb.toString(),type);
	}
	@Override
	public GetMethod get(String url,int type){
		logger.info("GET-"+url);
		GetMethod get = new GetMethod();
		try {
			get.setURI(new URI(url,true,get.getParams().getUriCharset()));
			if(CLIENT_TYPE_BOWSER==type){
				super.head_Browser(get);
			}
			this.cookieQueue.readSCookie(this.getCookieCheckOutPoint());
			HttpClient client = new HttpClient();
			Cookie[] cs = this.cookieQueue.findCookiesByHost(get.getHostConfiguration().getHost());
			client.getState().addCookies(cs);
			client.executeMethod(get);
			this.cookieQueue.add(new SCookie(get.getHostConfiguration().getHost(), client.getState().getCookies())).persist();;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return get;
	}
	@Override
	public PostMethod post(String url, Map params) {
		return this.post(url, params, CLIENT_TYPE_SIMPLE);
	}
	@Override
	public PostMethod post(String url,Map params,int type){
		logger.info("POST-"+url);
		logger.info("DATA-"+params);
		PostMethod post = new PostMethod(url);
		try {
			if(CLIENT_TYPE_BOWSER==type){
				super.head_Browser(post);
			}
			if(params!=null){
				NameValuePair[] ps = new NameValuePair[params.keySet().size()];
				Object[] keys = params.keySet().toArray();
				for(int i = 0;i<keys.length;i++){
					ps[i] = new NameValuePair(keys[i].toString(), params.get(keys[i]).toString());
				}
				post.setRequestBody(ps);
				post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			}
			this.cookieQueue.readSCookie(this.getCookieCheckOutPoint());
			HttpClient client = new HttpClient();
			Cookie[] cs = this.cookieQueue.findCookiesByHost(post.getHostConfiguration().getHost());
			client.getState().addCookies(cs);
			client.executeMethod(post);
			this.cookieQueue.add(new SCookie(client.getHost(), client.getState().getCookies()));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return post;
	}
	
	@Override
	public PutMethod put(String url, JSONObject params) {
		return this.put(url, params, CLIENT_TYPE_SIMPLE);
	}
	@Override
	public PutMethod put(String url, JSONObject params, int type) {
		logger.info("PUT-"+url);
		logger.info("DATA-"+params);
		PutMethod put = new PutMethod(url);
		try {
			if(CLIENT_TYPE_BOWSER==type){
				super.head_Browser(put);
			}
			if(params!=null){
				put.setRequestBody(params.toString());
				put.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			}
			this.cookieQueue.readSCookie(this.getCookieCheckOutPoint());
			HttpClient client = new HttpClient();
			Cookie[] cs = this.cookieQueue.findCookiesByHost(put.getHostConfiguration().getHost());
			client.getState().addCookies(cs);
			client.executeMethod(put);
			this.cookieQueue.add(new SCookie(client.getHost(), client.getState().getCookies()));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return put;
	}
	@Override
	public DeleteMethod delete(String url, JSONObject params) {
		return this.delete(url, params, CLIENT_TYPE_SIMPLE);
	}
	@Override
	public DeleteMethod delete(String url, JSONObject params, int type) {
		logger.info("DELETE-"+url);
		logger.info("DATA-"+params);
		DeleteMethod delete = new DeleteMethod(url);
		try {
			if(CLIENT_TYPE_BOWSER==type){
				super.head_Browser(delete);
			}
			if(params!=null){
				NameValuePair[] ps = new NameValuePair[params.keySet().size()];
				Object[] keys = params.keySet().toArray();
				for(int i = 0;i<keys.length;i++){
					ps[i] = new NameValuePair(keys[i].toString(), params.get(keys[i]).toString());
				}
				delete.setQueryString(ps);
				delete.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			}
			this.cookieQueue.readSCookie(this.getCookieCheckOutPoint());
			HttpClient client = new HttpClient();
			Cookie[] cs = this.cookieQueue.findCookiesByHost(delete.getHostConfiguration().getHost());
			client.getState().addCookies(cs);
			client.executeMethod(delete);
			this.cookieQueue.add(new SCookie(client.getHost(), client.getState().getCookies()));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return delete;
	}
	
	public String getCookieCheckOutPoint() {
		if(cookieCheckOutPoint==null){
			this.cookieCheckOutPoint = "cookie/";
		}else if(cookieCheckOutPoint.length()==0){
			this.cookieCheckOutPoint = "cookie/";
		}else if(!this.cookieCheckOutPoint.endsWith("/")){
			this.cookieCheckOutPoint += "/";
		}
		return this.cookieCheckOutPoint;
	}
	public void setCookieCheckOutPoint(String cookieCheckOutPoint) {
		this.cookieCheckOutPoint = cookieCheckOutPoint;
	}
}
