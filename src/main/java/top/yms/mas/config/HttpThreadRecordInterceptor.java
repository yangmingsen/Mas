package top.yms.mas.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import com.google.common.collect.Maps;
import top.yms.mas.utils.LocalThreadUtils;

/**
 * 是否登陸 Created by sam on 3/5/18.
 */
@Component
public class HttpThreadRecordInterceptor implements HandlerInterceptor {
    Logger log = LoggerFactory.getLogger(HttpThreadRecordInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        try {
            Map<String, Object> map = Maps.newHashMap();
            String sessionId = httpServletRequest.getSession().getId();// Cookie
            String lang = httpServletRequest.getHeader("lang");
            String sysCode = httpServletRequest.getHeader("sysCode");
            String referer = httpServletRequest.getHeader("Referer");
            map.put("sessionId", sessionId);
            map.put("lang", lang);
            map.put("sysCode", sysCode);
            map.put("Referer", referer);
            map.put("httpAttr", RequestContextHolder.getRequestAttributes());
            map.put("companyCode", httpServletRequest.getSession().getAttribute("companyCode"));
            LocalThreadUtils.set(map);
        } catch (Exception ex) {
            log.error("set http local thread var error :", ex);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}

