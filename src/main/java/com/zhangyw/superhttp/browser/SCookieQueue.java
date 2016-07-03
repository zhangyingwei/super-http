package com.zhangyw.superhttp.browser;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.httpclient.Cookie;

public class SCookieQueue {
	private Set<SCookie> cookies = new HashSet<SCookie>();
	private String checkOutPoint;
	public String getCheckOutPoint() {
		return checkOutPoint;
	}
	public SCookieQueue setCheckOutPoint(String checkOutPoint) {
		this.checkOutPoint = checkOutPoint;
		return this;
	}
	public SCookieQueue add(SCookie sCookie){
		sCookie.setCheckOutPoint(this.checkOutPoint);
		this.cookies.add(sCookie);
		return this;
	}
	public void remove(SCookie sCookie){
		this.cookies.remove(sCookie);
	}
	public void persist(){
		for(SCookie s:cookies){
			s.persist();
		}
	}
	public SCookieQueue readSCookie(String checkOutPoint){
		File file = new File(checkOutPoint);
		String[] cookieHosts = file.list();
		if(cookieHosts!=null&&cookieHosts.length!=0){
			for(String host:cookieHosts){
				this.cookies.add(new SCookie(checkOutPoint, host));
			}
		}
		return this;
	}
	public Cookie[] findCookiesByHost(String host){
		Cookie[] cookie = new Cookie[0];
		for(SCookie s:this.cookies){
			if(host.equals(s.getHost())){
				cookie = s.getCookies();
			}
		}
		return cookie;
	}
}
