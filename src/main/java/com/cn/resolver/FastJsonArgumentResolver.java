package com.cn.resolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.annotation.JsonParam;
import com.cn.exception.ParamParseException;
import com.cn.param.OutCountryCode;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by home on 2017/11/23.
 */
public class FastJsonArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JsonParam.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        RequestContext requestContext=new RequestContext(request);
        // content-type不是json的不处理
       /* if (!request.getContentType().contains("application/json")) {
            return null;
        }*/
        // 把reqeust的body读取到
        /* BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int rd;
        while ((rd = reader.read(buf)) != -1) {
            sb.append(buf, 0, rd);
        }*/

        try {
            Class type=parameter.getParameterType();
            String  sb= request.getParameter("q");
            // 利用fastjson转换为对应的类型
            Object objcet=JSONObject.toJavaObject(JSON.parseObject(sb), type);
            return  objcet;
        } catch (Exception e) {
           throw new ParamParseException(requestContext.getMessage("sys.prompt.paramerror"));
        }

    }


}
