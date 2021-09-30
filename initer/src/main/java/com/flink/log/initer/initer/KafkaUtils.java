package com.flink.log.initer.initer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @author LUO AI JUN
 * @date 2021/9/30 12:00
 */
public class KafkaUtils {

        public static void main(String[] args) {
            Properties properties = new Properties();

            properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.24.240.137:9092,172.24.240.137:9092,172.24.240.137:9092");
            properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            properties.put(ProducerConfig.ACKS_CONFIG, "all");
            properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
            properties.put(ProducerConfig.RETRIES_CONFIG, 0);
            properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300);
            properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);

            // KafkaProducer 是线程安全的，可以多个线程使用用一个 KafkaProducer
            KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>("ods_base_log", "value - (" + i + 1 + ")");
                kafkaProducer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception != null) {
                            System.err.println("发送数据到kafka中,发生了异常.");
                            exception.printStackTrace();
                            return;
                        }
                        System.out.println("topic: " + metadata.topic() + " offset: " + metadata.offset() + " partition: "
                                + metadata.partition());
                    }
                });
            }

            System.out.println("消息发送完成");
            kafkaProducer.close();
        }
}
