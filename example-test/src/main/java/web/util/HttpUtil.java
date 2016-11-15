package web.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//同babywei-common中HttpUtil
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	/**
	 *  Get 请求  参数编码
	 * @param param
	 * @param url
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String parseGetParam(Map<String,String> param,String url){
		StringBuffer buffer = new StringBuffer();
		for(String key : param.keySet()){
			buffer.append(key+"="+URLEncoder.encode(param.get(key))+"&");
		}
		return url+"?"+buffer.toString();
	}

	/**
	 *application/json;charset=utf-8
	 * json格式传输，作为body体
	 * @param body
	 * @param url
	 * @return
	 */
	public static String httpPost(String url, String body){
		logger.info("请求的url={}",url);
		logger.info("请求的json数据:body={}",body);
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		StringEntity entity = new StringEntity(body,"utf-8");
		try {
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("httpPost 发送请求错误，e={}",e);
			return "";
		}

	}

	/**
	 *application/x-www-form-urlencoded;charset=utf-8
	 * 键值对的形式 key1=value1&key2=value2
	 * key和value经过了编码
	 * @param param
	 * @param url
	 * @return
	 */
	public static String httpPost(Map<String,Object> param, String url){
		logger.info("http请求: Map<String,Object> param = {} \n,url = {}", param, url);
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost http = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : param.keySet()){
			params.add(new BasicNameValuePair(key,param.get(key).toString()));
		}
		try {
			http.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			logger.info("http请求: Headers = {} ", http.getRequestLine());
			HttpResponse response = httpclient.execute(http);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			logger.error("http request fail:",e);;
			return "";
		}

	}

	/**
	 * 以表单方式上传单个文件和多个文本参数值
	 *
	 * @param textBodyMap
	 * @param binaryBodyMap
	 * @param url
	 * @return
	 */
	public static String httpPost(Map<String, Object> textBodyMap, Map<String, Object> binaryBodyMap, String url) {
		List<Map<String, Object>> binaryBodyList = new ArrayList<Map<String, Object>>();
		binaryBodyList.add(binaryBodyMap);
		return httpPost(textBodyMap, binaryBodyList, url);
	}

	/**
	 * 以表单方式上传多个文件和多个文本参数值
	 *
	 * @param textBodyMap
	 * @param binaryBodyList
	 * @param url
	 * @return
	 */
	public static String httpPost(Map<String, Object> textBodyMap, List<Map<String, Object>> binaryBodyList, String url) {
		logger.info("http请求: Map<String,Object> param = {} \n,url = {}", textBodyMap, url);
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost http = new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		//设置文本参数
		for (String key : textBodyMap.keySet()) {
			builder.addTextBody(key, textBodyMap.get(key).toString());
		}
		//设置文件流
		if (binaryBodyList != null && !binaryBodyList.isEmpty()) {
			for (Map<String, Object> fm : binaryBodyList) {
				Assert.hasText((String) fm.get("name"));
				Assert.hasText((String) fm.get("fileName"));
				Assert.notNull(fm.get("file"));
				String name = fm.get("name").toString();
				String fileName = fm.get("fileName").toString();
				File file = (File) fm.get("file");
				builder.addBinaryBody(name, file, ContentType.DEFAULT_BINARY, fileName);
			}
		}
		try {
			HttpEntity entity = builder.build();
			http.setEntity(entity);
			logger.info("http请求: Headers = {} ", http.getRequestLine());
			HttpResponse response = httpclient.execute(http);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			logger.error("http request fail:", e);
			return "";
		}
	}

	/**
	 *
	 * @param param
	 * @param url
	 * @return
	 */
	public static String httpGet(Map<String,String> param, String url){
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		url = parseGetParam(param,url);
		HttpGet http = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(http);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			logger.error("http request fail:",e);
			return "";
		}

	}

}
