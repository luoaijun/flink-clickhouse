package com.flink;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * @author LUO AI JUN
 * @date 2021/10/12 17:41
 */
public class KafkaTest {

    private final KafkaProducer<String, String> producer;
    public final static String TOPIC = "ods_base_log";
    public String ip = "172.24.240.137";

    public static void main(String[] args) {
        KafkaTest kafkaTest = new KafkaTest();
        kafkaTest.produce();
    }

    public void consume() {
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", "172.24.240.137:9092");
        //必须指定消费者组
        props.put("group.id", "base_log_app_group");
        //设置数据key和value的序列化处理类
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
        //创建消息者实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //订阅topic1的消息
        consumer.subscribe(Arrays.asList("ods_base_log"));
        //到服务器中读取记录
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("key:" + record.key() + "" + ",value:" + record.value());
            }
        }
    }

    private KafkaTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", ip + ":9092," + ip + ":9092," + ip + ":9092");//xxx服务器ip
        props.put("acks", "all");//所有follower都响应了才认为消息提交成功，即"committed"
        props.put("retries", 0);//retries = MAX 无限重试，直到你意识到出现了问题:)
        props.put("batch.size", 16384);//producer将试图批处理消息记录，以减少请求次数.默认的批量处理消息字节数
        //batch.size当批量的数据大小达到设定值后，就会立即发送，不顾下面的linger.ms
        props.put("linger.ms", 1);//延迟1ms发送，这项设置将通过增加小的延迟来完成--即，不是立即发送一条记录，producer将会等待给定的延迟时间以允许其他消息记录发送，这些消息记录可以批量处理
        props.put("buffer.memory", 33554432);//producer可以用来缓存数据的内存大小。
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<String, String>(props);
    }

    public void produce() {
        int messageNo = 1;
        final int COUNT = 5;
        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = String.format("hello KafkaProducer message %s from hubo 06291018 ", key);
            try {
                producer.send(new ProducerRecord<String, String>(TOPIC, data));
            } catch (Exception e) {
                e.printStackTrace();
            }
            messageNo++;
        }
        producer.close();
    }
}

