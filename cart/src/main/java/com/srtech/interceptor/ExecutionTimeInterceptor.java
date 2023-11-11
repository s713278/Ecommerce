package com.srtech.interceptor;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExecutionTimeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = new Date().getTime();
		request.setAttribute("startTime", startTime);
		log.debug("Before controller execution and Starttime : {}",startTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long endTime = new Date().getTime();
		long startTime= (Long)request.getAttribute("startTime");
		long executionTime = endTime-startTime;
		log.debug("After controller execution ,startTime :{}, endTime : {} and Execution time : {} in milli seconds",startTime,endTime,endTime,executionTime);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("After  controller and client's renderring execution");
	}

}
