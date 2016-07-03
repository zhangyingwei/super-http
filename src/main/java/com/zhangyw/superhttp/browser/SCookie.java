package com.zhangyw.superhttp.browser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import org.apache.commons.httpclient.Cookie;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

public class SCookie {
	private String host;
	private Cookie[] cookies;
	private String checkOutPoint;
	public SCookie(String host,Cookie[] cookies){
		this.host = host;
		this.cookies = cookies;
		for(Cookie c:cookies){
			System.out.println(c.toExternalForm());
		}
	}
	public SCookie(SCookie sCookie) {
		this.host = sCookie.getHost();
		this.cookies = sCookie.getCookies();
		this.checkOutPoint = sCookie.getCheckOutPoint();
	}

	public SCookie(String checkOutPoint,String host){
		SCookie cookie = this.readSCookie(checkOutPoint, host);
		this.host = cookie.getHost();
		this.cookies = cookie.getCookies();
		this.checkOutPoint = cookie.getCheckOutPoint();
	}
	
	public String getHost() {
		return host;
	}
	public SCookie setHost(String host) {
		this.host = host;
		return this;
	}
	public Cookie[] getCookies() {
		return cookies;
	}
	public SCookie setCookies(Cookie[] cookies) {
		this.cookies = cookies;
		return this;
	}
	public String getCheckOutPoint() {
		return checkOutPoint;
	}

	public SCookie setCheckOutPoint(String checkOutPoint) {
		this.checkOutPoint = checkOutPoint;
		return this;
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

	
	public void persist(){
		String fullName = this.checkOutPoint==null?this.host:this.checkOutPoint.endsWith("/")?this.checkOutPoint+this.host:this.checkOutPoint+"/"+this.host;
		File checkOutPointDir = new File(checkOutPoint);
		File cFile = new File(fullName);
		ObjectOutputStream cookieStream = null;
		try {
			if(!checkOutPointDir.exists()){
				new File(checkOutPoint).mkdirs();
			}
			cookieStream = new ObjectOutputStream(new FileOutputStream(cFile));
			cookieStream.writeObject(cookies);
//			for(Cookie c:cookies){
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(cookieStream!=null){
				try {
					cookieStream.flush();
					cookieStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public SCookie readSCookie(String checkOutPoint,String host){
		this.checkOutPoint = checkOutPoint;
		this.host = host;
		String path = this.getFullName();
		ObjectInputStream cookieStream = null;
		try {
			cookieStream = new ObjectInputStream(new FileInputStream(new File(path)));
			this.cookies = (Cookie[]) cookieStream.readObject();
			this.host = host;
			this.checkOutPoint = checkOutPoint;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	private String getFullName(){
		return this.checkOutPoint==null?this.host:this.checkOutPoint.endsWith("/")?this.checkOutPoint+this.host:this.checkOutPoint+"/"+this.host;
	}
}
