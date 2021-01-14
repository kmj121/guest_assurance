package com.vsc.guest_assurance.config;

import com.vsc.guest_assurance.common.ApiException;
import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "exceptionFilter")
public class ExceptionFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException {
		try {
			chain.doFilter(request, response);
		} catch (ApiException e) {
			MessageCode messageCode = e.getCode();
			ResultObject result = new ResultObject(messageCode, e.getArgv());
			response.getWriter().println(JSONObject.fromObject(result).toString());
		} catch (ServletException e) {
		    logger.error(e.getMessage(), e);
			if (e.getRootCause() instanceof ApiException) {
				ApiException apiException = (ApiException)e.getRootCause();
				MessageCode messageCode = apiException.getCode();
				ResultObject result = new ResultObject(messageCode, null, apiException.getArgv());
				if(messageCode.equals(MessageCode.CODE_NO_PRIVILEGE)){
					((HttpServletResponse)response).setStatus(HttpServletResponse.SC_FORBIDDEN);
				}
				response.getWriter().println(JSONObject.fromObject(result).toString());
			} else {
				MessageCode messageCode = MessageCode.CODE_EXCEPTION;
				ResultObject result = new ResultObject(messageCode, null);
				result.setMessage(result.getMessage() + ";" + e.getRootCause().toString());
				response.getWriter().println(JSONObject.fromObject(result).toString());
			}
		} catch (Exception e) {			
		    logger.error(e.getMessage(), e);
			MessageCode messageCode = MessageCode.CODE_EXCEPTION;
			ResultObject result = new ResultObject(messageCode, null);
			response.getWriter().println(JSONObject.fromObject(result).toString());
		}
	}

	@Override
	public void init(FilterConfig arg0) {
	}
}
