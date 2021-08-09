package com.lcp.learn.base.collections;

import static com.alibaba.fastjson.serializer.SerializerFeature.QuoteFieldNames;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

import com.alibaba.fastjson.JSON;
import org.lcp.learn.java.beans.User;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/4/27-09:18
 */
public class JSONMain {

  public static void main(String[] args) {

    var user = new User();

    System.out.println("instances = " + JSON.toJSONString(user, QuoteFieldNames, WriteMapNullValue,
        WriteNullNumberAsZero, WriteNullListAsEmpty, WriteNullStringAsEmpty, WriteNullBooleanAsFalse));

  }
}
