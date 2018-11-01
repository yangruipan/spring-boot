package com.buy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/9/25.
 */
//@SpringBootConfiguration
public class WebHandlerConfig extends WebMvcConfigurerAdapter implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    private static WebHandlerConfig webHandler ;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public @PostConstruct void init() {
        webHandler = this;
        webHandler.redisService = this.redisService;
    }

    /**
     * 请求前执行
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String requestURI = request.getRequestURI();
        /*不拦截静态资源路径*/
        if(requestURI.indexOf("/js/") > -1 ||
                requestURI.indexOf("/css/") > -1 ||
                requestURI.indexOf("/image/") > -1){
            return true;
        }
        /*从session获取token，拒绝访问*/
        String key = "";
        Object sessionToken = request.getSession().getAttribute("token");
        if(!ObjectUtils.isEmpty(sessionToken)){
            key = String.valueOf(sessionToken);
        }
        /*从url获取token，如果urlToken存在，那么更新/覆盖sessionToken*/
        String urlToken = request.getParameter("token");
        if(!StringUtils.isEmpty(urlToken)){
            key = urlToken;
            request.getSession().setAttribute("token",key);
        }
        /*如果url与session都不存在token，拒绝访问*/
        if(StringUtils.isEmpty(key)){
            return false;
        }
        return true;
    }

    /**
     * 在请求不报异常，顺利完成后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        String url = request.getRequestURI();
        if(!StringUtils.isEmpty(url)){

        }
    }

    /**
     * 无论请求是否异常，最后都会执行。用于清理资源，关闭连接等
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
    }

    /**
     * 设置拦截规则
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns：添加拦截规则
        // excludePathPatterns： 排除拦截规则
        // error 是 spring boot中的默认处理异常的url，所以排除掉，不拦截它。
        registry.addInterceptor(new WebHandlerConfig())
                .addPathPatterns("/**")
                .excludePathPatterns("/error");
    }
}
