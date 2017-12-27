package com.offcn.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	
	public static String grabData(String url){
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet request = new HttpGet(url);
		
		String content = null;
		
		try {
			
			CloseableHttpResponse response = httpClient.execute(request);
			
			StatusLine status = response.getStatusLine();
//			System.out.println("服务器响应状态:"+status.getStatusCode());
			
			if(status.getStatusCode()==200){
				
				HttpEntity entity = response.getEntity();
				
				content = EntityUtils.toString(entity,"GBK");
				
				EntityUtils.consume(entity);
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return content;
	}
}
