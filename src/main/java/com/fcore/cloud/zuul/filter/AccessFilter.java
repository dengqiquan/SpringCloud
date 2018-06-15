package com.fcore.cloud.zuul.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter{
	private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.getResponse().setCharacterEncoding("UTF-8");
            ctx.setResponseBody("你没有权限访问该接口！");
            return null;
        }
        log.info("access token ok");
        return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
