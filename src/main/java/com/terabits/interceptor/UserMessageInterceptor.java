package com.terabits.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.terabits.dao.UserDao;
import com.terabits.wapper.HttpRequestWapper;

/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月25日 上午10:17:24 
* 类说明 
*/
public class UserMessageInterceptor extends HandlerInterceptorAdapter {
	@Autowired
    @Qualifier("userDao")
    private UserDao userDao;
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String key=userDao.getAESKeyByUserId(request.getParameter("id"));
        ((HttpRequestWapper)request).setKey(key);
        return true;
    }

}
