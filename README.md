# super-http
##���
��װ��http��get��post��put��delete����
##ʹ�÷���
```java
String username = "Ҷ����";
String url = "http://www.jianshu.com/search/do?q="+URLEncoder.encode(username,"utf-8")+"&type=users";
GetMethod get = Http.getIS().get(url,Http.CLIENT_TYPE_BOWSER);
System.out.println(get.getResponseBodyAsString());
```
**����**
```java
/**
 *����ͷ�д��������Ϣ
 */
Http.CLIENT_TYPE_BOWSER
```
```java
/**
 *����ͷ�в�����Ϣ
 */
Http.CLIENT_TYPE_SIMPLE
```