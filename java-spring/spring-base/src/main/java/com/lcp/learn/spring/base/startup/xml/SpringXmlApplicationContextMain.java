package com.lcp.learn.spring.base.startup.xml;

import static com.alibaba.fastjson.serializer.SerializerFeature.QuoteFieldNames;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.base.beans.Goods;
import com.lcp.learn.spring.base.beans.User;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/13-11:07
 */
public class SpringXmlApplicationContextMain {

  private static final Logger logger = LoggerFactory.getLogger(SpringXmlApplicationContextMain.class);

  public static void main(String[] args) {

    GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
    genericApplicationContext.refresh();

    String beanName = "user3";
    GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
    beanDefinition.setBeanClassName(User.class.getName());
    beanDefinition.setInitMethodName("init");
    beanDefinition.setPropertyValues(new MutablePropertyValues(Collections.singletonMap("key", "asdasdas")));
    genericApplicationContext.registerBeanDefinition(beanName, beanDefinition);
    Object obj = genericApplicationContext.getBean(beanName);
    logger.info("clazzName = " + obj.getClass().getName());
    logger.info("instances = " + JSON.toJSONString(obj,
        QuoteFieldNames,
        WriteMapNullValue,
        WriteNullNumberAsZero,
        WriteNullListAsEmpty,
        WriteNullStringAsEmpty,
        WriteNullBooleanAsFalse
    ));

    //        var beanName2 = "user3";
    GenericBeanDefinition beanDefinition2 = new GenericBeanDefinition();
    beanDefinition2.setBeanClassName(Goods.class.getName());
    genericApplicationContext.registerBeanDefinition(beanName, beanDefinition2);

    logger.info("=========================");
    Object obj2 = genericApplicationContext.getBean(beanName);
    logger.info("clazzName = " + obj2.getClass().getName());
    logger.info("instances = " + JSON.toJSONString(obj2,
        QuoteFieldNames,
        WriteMapNullValue,
        WriteNullNumberAsZero,
        WriteNullListAsEmpty,
        WriteNullStringAsEmpty,
        WriteNullBooleanAsFalse
    ));
    logger.info("=========================");
    logger.info("clazzName = " + obj.getClass().getName());
    logger.info("instances = " + JSON.toJSONString(obj,
        QuoteFieldNames,
        WriteMapNullValue,
        WriteNullNumberAsZero,
        WriteNullListAsEmpty,
        WriteNullStringAsEmpty,
        WriteNullBooleanAsFalse
    ));

  }

}
