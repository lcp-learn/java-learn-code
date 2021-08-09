package com.lcp.learn.frameworks.mq.kafka;

import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/20-16:38
 */
public class KafkaProductMain {

  private static final Logger logger = LoggerFactory.getLogger(KafkaProductMain.class);

  public static void main(String[] args) throws Exception {

    var address = "PLAINTEXT://vbox_centos_8:9092";
    var topic = "kfk_test_1";

    var props = new Properties();

    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, address);//kafka地址，多个地址用逗号分割
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    // props.put("bootstrap.servers", address);
    // props.put("acks", "all");
    // props.put("retries", 0);
    // props.put("batch.size", 16384);
    // props.put("key.serializer", StringSerializer.class.getName());
    // props.put("value.serializer", StringSerializer.class.getName());
    var producer = new KafkaProducer<String, String>(props);

    for (int i = 0; i < 10; i++) {
      var message = "message_" + RandomStringUtils.random(10, true, true);
      var future = producer.send(new ProducerRecord<>(topic, "content_about_name", message));
      var mateData = future.get();
      logger.info("mateData:{}", mateData);
    }
  }

}
