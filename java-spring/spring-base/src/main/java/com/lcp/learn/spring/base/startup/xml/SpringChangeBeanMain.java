package com.lcp.learn.spring.base.startup.xml;

import com.lcp.learn.spring.base.beans.animal.Cat;
import com.lcp.learn.spring.base.beans.animal.Dog;
import com.lcp.learn.spring.base.event.MyApplicationEvent;
import com.lcp.learn.spring.base.event.MyEvents;
import com.lcp.learn.spring.base.listener.MyApplicationListener;
import com.lcp.learn.spring.base.listener.handler.ApplicationListenerHandler;
import com.lcp.learn.spring.base.service.HelloService;
import com.lcp.learn.spring.base.service.impl.HelloServiceImpl;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/13-11:07
 */
public class SpringChangeBeanMain {

  private static final Logger logger = LoggerFactory.getLogger(SpringChangeBeanMain.class);

  public static void main(String[] args) {

    //        var beanName = "user3";

    GenericApplicationContext applicationContext = new GenericApplicationContext();
    applicationContext.refresh();

    applicationContext.registerBean("listener1", MyApplicationListener.class);
    applicationContext.registerBean("hello", HelloServiceImpl.class);

    MyApplicationListener listener = applicationContext.getBean(MyApplicationListener.class);
    Map<String, ApplicationListenerHandler> handlers = applicationContext
        .getBeansOfType(ApplicationListenerHandler.class);
    for (ApplicationListenerHandler applicationListenerHandler : handlers.values()) {
      listener.regist(applicationListenerHandler);
    }

    HelloService hello = applicationContext.getBean("hello", HelloService.class);

    applicationContext.registerBean("animal", Dog.class);
    applicationContext.publishEvent(new MyApplicationEvent(MyEvents.CHANGE_BEAN));
    System.out.println("hello.getAnimal().getName() = " + hello.getAnimal().getName());

    applicationContext.registerBean("animal", Cat.class);
    applicationContext.publishEvent(new MyApplicationEvent(MyEvents.OTHER));
    System.out.println("hello.getAnimal().getName() = " + hello.getAnimal().getName());

    //
    //        var beanDefinition = new GenericBeanDefinition();
    //        beanDefinition.setBeanClassName(User.class.getName());
    //        beanDefinition.setInitMethodName("init");
    //        beanDefinition.setPropertyValues(new MutablePropertyValues(Collections.singletonMap("key", "asdasdas")));
    //        applicationContext.registerBeanDefinition("user3", beanDefinition);
    //
    //        var beanDefinition2 = new GenericBeanDefinition();
    //        beanDefinition2.setBeanClassName(HelloServiceImpl.class.getName());
    //        applicationContext.registerBeanDefinition("helloService", beanDefinition2);
    ////
    //        var target = applicationContext.getBean("user3");
    //        var helloService = applicationContext.getBean(HelloServiceImpl.class);
    //        helloService.setAnimal(target);
    //
    //        Object result = null;
    //        result = helloService.haha();
    //        System.out.println("result = " + result.getClass().getName());
    //
    //        applicationContext.addApplicationListener(helloService);
    //
    //        var beanDefinition3 = new GenericBeanDefinition();
    //        beanDefinition3.setBeanClassName(Goods.class.getName());
    //        applicationContext.registerBeanDefinition("user3", beanDefinition3);
    //        applicationContext.publishEvent(new ServiceEvent("change_user3"));
    //
    //        result = helloService.haha();
    //        System.out.println("result = " + result.getClass().getName());

  }

}
