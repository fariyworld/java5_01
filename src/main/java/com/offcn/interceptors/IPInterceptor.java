package com.offcn.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.offcn.mappers.IPMapper;

public class IPInterceptor implements HandlerInterceptor {
	
	@Autowired
	private IPMapper ipMapper; 

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}
	
	//0:0:0:0:0:0:0:1
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		String ipAddress = request.getRemoteAddr();
		
		System.out.println("访问ip地址："+ ipAddress);
		
		/*
		 * 1.先获得次数， if count >= 10 不能访问
		 * 						<10 插入数据库，放行
		 */
		
		if ( ipMapper.findIPAccessCount(ipAddress) >= 10){
			
			System.out.println("ip访问频繁");
			
			return false;
		
		}else{
			
			System.out.println("可以访问,插入记录");
			
			ipMapper.insert(ipAddress);
			
			return true;
		}
		
	}

}
