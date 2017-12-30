package com.offcn.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.offcn.enums.FaceConstant;

public class HttpClientUtil {

	/**
	 * 
	 * @TODO: 抓取数据
	 * @param url
	 * @return String
	 */
	public static String grabData(String url) {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet request = new HttpGet(url);

		String content = null;

		try {

			CloseableHttpResponse response = httpClient.execute(request);

			StatusLine status = response.getStatusLine();
			// System.out.println("服务器响应状态:"+status.getStatusCode());

			if (status.getStatusCode() == 200) {

				HttpEntity entity = response.getEntity();

				content = EntityUtils.toString(entity, "GBK");

				EntityUtils.consume(entity);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return content;
	}

	/**
	 * 
	 * @TODO: HttpClient 发送 post请求
	 * @param clazz
	 * @param url
	 * @return T
	 */
	public static <T> T doPost(Class<T> clazz, String url) {

		T t = null;

		CloseableHttpClient client = HttpClients.createDefault();

		try {

			HttpPost request = new HttpPost(url);

			CloseableHttpResponse response = client.execute(request);

			StatusLine status = response.getStatusLine();

			if (status.getStatusCode() == 200) {

				HttpEntity entity = response.getEntity();

				String content = EntityUtils.toString(entity);

				ObjectMapper mapper = new ObjectMapper();

				t = mapper.readValue(content, clazz);
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {

			try {
				client.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		return t;
	}

	/**
	 * 
	 * @TODO: HttpClient 发送 post请求
	 * @param url
	 *            --> 请求地址
	 * @param t
	 *            --> 参数封装JavaBean
	 * @param args
	 *            --> header参数
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T doPost(String url, T t, Map<String, String> headerMap) {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost request = new HttpPost(url);

		/** 设置header */
		setHeader(request, headerMap);

		/** 设置body请求参数 */
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		
		try {
			
			request.setEntity(new UrlEncodedFormEntity(setBodyParamS(t, list)));
			
			CloseableHttpResponse response = httpClient.execute(request);
			
			StatusLine status = response.getStatusLine();

			if (status.getStatusCode() == 200) {

				HttpEntity entity = response.getEntity();

				String content = EntityUtils.toString(entity);

				ObjectMapper mapper = new ObjectMapper();

				t = (T) mapper.readValue(content, t.getClass());
			}
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		
		}finally{
			
			try {
				httpClient.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return t;
	}

	/** 设置header */
	public static void setHeader(HttpPost request, Map<String, String> headerMap) {

		Set<Entry<String, String>> entrySet = headerMap.entrySet();

		for (Entry<String, String> entry : entrySet) {

			request.setHeader(entry.getKey(), entry.getValue());
		}
	}

	/** 设置body请求参数 */
	public static <T> List<NameValuePair> setBodyParamS(T t, List<NameValuePair> list) {

		/** 反射获得T的不为空的值即为body参数 */

		Class<? extends Object> clazz = t.getClass();

		Field[] fields = clazz.getDeclaredFields();// JavaBean对象属性数组

		try {

			for (Field field : fields) {

				Method getMethod = clazz.getMethod("get" + initcap(field.toString()));

				Object val = getMethod.invoke(t);

				if (val != null) {//不为null 的 是有效参数

					list.add(new BasicNameValuePair(getPropertyName(field.toString()),val.toString()));
				}
			}
			
			t = null;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return list;
	}

	/**
	 * 
	 * @TODO: 首字母大写
	 * 
	 * @param str
	 * @return String
	 */
	public static String initcap(String str) {

		str = getPropertyName(str);

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 
	 * @TODO: 获得属性名
	 * 
	 * @param str
	 * @return String
	 */
	public static String getPropertyName(String str) {

		return str.substring(str.lastIndexOf(".") + 1);
	}

	/**
	 * 
	 * @TODO: 设置通用的header
	 * @return Map<String,String>
	 */
	public static Map<String, String> setCommonHeader(){
		
		String header = FaceConstant.Content_Type.getVal();

		Map<String, String> headerMap = new HashMap<String, String>();

		headerMap.put("Content-Type", header);
		
		return headerMap;
	}
}
