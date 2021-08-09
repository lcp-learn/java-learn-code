package com.lcp.learn.spring.web.container.initializer;

import com.lcp.learn.spring.web.container.config.AppConfiguration;
import javax.servlet.ServletContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-10:24
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {
    initializeSpringConfig(servletContext);
  }

  private void initializeSpringConfig(ServletContext servletContext) {

    // Create the 'root' Spring application context
    AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext =
        new AnnotationConfigWebApplicationContext();

    annotationConfigWebApplicationContext.register(AppConfiguration.class);

    // Manage the life cycle of the root application context
    servletContext.addListener(new ContextLoaderListener(annotationConfigWebApplicationContext));
  }
}
