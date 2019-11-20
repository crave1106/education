package com.crave.edu.commons;

import com.crave.edu.util.SpringBeanUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

@Component
public class ServletContextUtil {
    private static ServletContext serveltContext = null;

    private ServletContextUtil(){};

    public synchronized static ServletContext getServletContext() {

        if(null == serveltContext) {
            serveltContext = SpringBeanUtil.getBean(ServletContext.class);
        }
        return serveltContext;
    }
}
