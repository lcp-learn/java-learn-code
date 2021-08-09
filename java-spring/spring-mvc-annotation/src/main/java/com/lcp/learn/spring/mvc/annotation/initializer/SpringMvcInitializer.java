package com.lcp.learn.spring.mvc.annotation.initializer;

import com.lcp.learn.spring.mvc.annotation.config.AppConfig;
import com.lcp.learn.spring.mvc.annotation.config.SpringMvcConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/9-17:31
 */
public class SpringMvcInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {

    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(AppConfig.class);
    servletContext.addListener(new ContextLoaderListener(rootContext));

    AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
    webContext.register(SpringMvcConfig.class);
    ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
    servletRegistration.setLoadOnStartup(1);
    servletRegistration.addMapping("/");
  }
}
