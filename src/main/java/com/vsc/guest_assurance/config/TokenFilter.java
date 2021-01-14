package com.vsc.guest_assurance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
@Component
public class TokenFilter implements HandlerInterceptor {

    //@Autowired
    //private LoginTokenService loginTokenService;
    //@Autowired
    //private UsersService usersService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //String token = request.getHeader("token");
        //if (StringUtils.equalsIgnoreCase(HttpMethod.OPTIONS, request.getMethod())) {
        //    return true;
        //}
        //LoginToken loginToken = loginTokenService.refreshLoginToken(token);
        ///*|| !StringUtils.equals(request.getRemoteAddr(), loginToken.getIp())*/
        //if (loginToken == null ) {
        //    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //    throw new ApiException(MessageCode.CODE_NEED_LOGIN, null);
        //}
        //String uri = StringUtils.equalsIgnoreCase(HttpMethod.GET, request.getMethod())
        //        ? request.getServletPath() + request.getQueryString() : request.getServletPath();
        //if (!checkPrivilege(uri, loginToken)) {
        //    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        //    throw new ApiException(MessageCode.CODE_NO_PRIVILEGE, loginToken.getLanguage());
        //}
        //request.setAttribute("loginToken", loginToken);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 验证用户权限
     */
//    private boolean checkPrivilege(String uri, LoginToken loginToken) {
//        //非common接口不需要判断权限
//        if (uri.startsWith("/common")) {
//            return true;
//        } else if (uri.startsWith("/app") && !StringUtils.equals(Constant.TOKEN_TYPE_APP, loginToken.getType())) {
//            return false;
//        } else if (uri.startsWith("/backend") && !StringUtils.equals(Constant.TOKEN_TYPE_BACKEND, loginToken.getType())) {
//            return false;
//        }
//
////        List<Privileges> unauthorizedPrivileges = usersService.getUnauthorizedPrivileges(loginTokens.getUserId(), loginTokens.getLanguage());
//        List<Privileges> unauthorizedPrivileges = null;
//        if (unauthorizedPrivileges == null || unauthorizedPrivileges.isEmpty()) {
//            return true;
//        }
//
//        for (Privileges privileges : unauthorizedPrivileges) {
//            String pattern = "^" + privileges.getUrlPattern().replaceAll("\\*", ".*");
//            if (Pattern.matches(pattern, uri)) {
//                return false;
//            }
//        }
//        return true;
//    }
}
