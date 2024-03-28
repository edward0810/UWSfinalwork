package com.szaiot.njg.config.intercepors;

import com.alibaba.fastjson.JSON;
import com.szaiot.njg.dao.LoginDao;
import com.szaiot.njg.entity.resp.UserInfo;
import com.szaiot.njg.entity.resp.UserSession;
import com.szaiot.njg.utils.BaseResponseObj;
import com.szaiot.njg.utils.NjgExceptionUtil;
import com.szaiot.njg.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

	@Resource
	private LoginDao loginDao;
	
	@Resource
    private RedisTemplate<String, Object> redisTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
	
	//这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		BaseResponseObj<Object> baseObj = new BaseResponseObj<Object>();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.setHeader("X-Powered-By", "Jetty");
		String method = request.getMethod();
		
		if (("OPTIONS").equals(method)) {
			response.setStatus(200);
			logger.info("mothod:" + method + "status:200");
			return true;
		}
		boolean flag = false;
		int code = 0;
		String msg = "";
		logger.info("mothod:" + method);
		
		UserSession user;
		try {
			user = RedisUtil.getUserSession(request,redisTemplate);
			if(user != null && user.getUserId() > 0){
				//根据userID获取用户信息
				UserInfo user1 = loginDao.getUserInfoByID(user.getUserId());
				if(user1 == null){
					code = 1;
					RedisUtil.deleteSessionID(request,redisTemplate);
				} else {
					flag = true;
				}
			} else {
				code = 1;
			}
		} catch (NjgExceptionUtil e) {
			msg = e.getMessage();
		}
		
		if (!flag) {
			baseObj.setCode(code);
			baseObj.setSuccess(false);
			baseObj.setMessage(msg);
			response.getWriter().write(JSON.toJSONString(baseObj));
			response.getWriter().close();
		}
		return flag;
	}
 
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
 
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
	
}
