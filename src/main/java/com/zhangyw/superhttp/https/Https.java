package com.zhangyw.superhttp.https;

import org.apache.commons.httpclient.protocol.Protocol;

public class Https {
	public static void init(){
		Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);
	}
}
