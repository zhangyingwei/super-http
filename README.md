# super-http
##简介
封装了http的get、post、put、delete方法
##使用方法
```java
String username = "叶康成";
String url = "http://www.jianshu.com/search/do?q="+URLEncoder.encode(username,"utf-8")+"&type=users";
GetMethod get = Http.getIS().get(url,Http.CLIENT_TYPE_BOWSER);
System.out.println(get.getResponseBodyAsString());
```
**参数**
```java
/**
 *请求头中带浏览器信息
 */
Http.CLIENT_TYPE_BOWSER
```
```java
/**
 *请求头中不带信息
 */
Http.CLIENT_TYPE_SIMPLE
```