package com.dragon.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class Interceptor implements RequestInterceptor {

    /**
     * 拦截器方法
     *
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        /*
         * 获取原线程的request对象的请求头中的token
         * RequestContextHolder.getRequestAttributes()：获取request原始的请求头对象
         * 接口类RequestAttributes不能使用，所以强转为ServletRequestAttributes类型
         */
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //防止空指针
        if (servletRequestAttributes==null) {
            return;
        }
        //获取原Request对象
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //把原request的请求头的所有参数都拿出来
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            //获取每个请求头参数的名字
            String name = headerNames.nextElement();
            //获取值
            String value = request.getHeader(name);
            //放到feign调用对象的request中去
            requestTemplate.header(name,value);
        }
    }
}

