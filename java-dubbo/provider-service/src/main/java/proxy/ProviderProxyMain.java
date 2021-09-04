package proxy;

import com.lcp.learn.dubbo.api.SimpleApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import proxy.config.BeanConfig;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/25-14:26
 */
public class ProviderProxyMain {

  private static final Logger logger = LoggerFactory.getLogger(ProviderProxyMain.class);

  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            // DubboConfig.class,
            BeanConfig.class
    );

    Arrays.stream(applicationContext.getBeanDefinitionNames())
            .forEach(name -> logger.info("name:{}", name));

    // aa(applicationContext.getBean(SimpleApi.class));
  }

  private static void aa(SimpleApi apiImpl) {

    SimpleApi simpleApi = (SimpleApi) Proxy.newProxyInstance(
            apiImpl.getClass().getClassLoader(),
            apiImpl.getClass().getInterfaces(),
            (proxy, method, args) -> method.invoke(apiImpl, args));

    simpleApi.check("asdasdasd");

  }

}
