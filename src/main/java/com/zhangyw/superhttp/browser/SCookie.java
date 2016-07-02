package com.zhangyw.superhttp.browser;

import java.util.Arrays;

import org.apache.commons.httpclient.Cookie;

public class SCookie {
	private String host;
	private Cookie[] cookies;
	public SCookie(String host,Cookie[] cookies){
		this.host = host;
		this.cookies = cookies;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cookies);
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SCookie other = (SCookie) obj;
		if (!Arrays.equals(cookies, other.cookies))
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		return true;
	}

	public void saveAsFile(){
		String path = "";
	}
	public void saveAsFile(String path){
		
	}
}
