package com.lcp.learn.frameworks.mq.kafka;

import com.google.common.collect.Lists;
import java.time.Duration;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/20-16:38
 */
public class KafkaConsumerMain {

  private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerMain.class);

  public static void main(String[] args) throws Exception {

    var address = "PLAINTEXT://vbox_centos_8:9092";
    var topic = "kfk_test_1";

    var props = new Properties();

    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);//kafka地址，多个地址用逗号分割
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);//设置是否为自动提交
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "Test1");

    var kafkaConsumer = new KafkaConsumer<String, String>(props);
    kafkaConsumer.subscribe(Lists.newArrayList(topic));

    while (true) {
      var consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
      if (!consumerRecords.isEmpty()) {
        for (var consumerRecord : consumerRecords) {
          logger.info("TopicName:{},Partition:{},Offset:{},Msg:{},key:{} ",
              consumerRecord.topic(),
              consumerRecord.partition(),
              consumerRecord.offset(),
              consumerRecord.value(),
              consumerRecord.key()
          );

          //进行逻辑处理
        }

      }
    }

  }

}
