package com.wasu.accounting.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.apache.logging.log4j.web.*;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


/**
 * Created by yangzh on 2017/2/23.
 */
public class RootInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("spring.profiles.default","dev");
//        servletContext.setInitParameter("log4jConfigLocation","classpath*:/log4j2.xml");
//        servletContext.addListener(Log4jServletContextListener.class);
        registerContextLoaderListener(servletContext);
        registerDispatcherServlet(servletContext);
    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");

        return new Filter[]{characterEncodingFilter};
    }


}
