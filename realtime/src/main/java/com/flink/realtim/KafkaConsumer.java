package com.flink.realtim;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Properties;

public class KafkaConsumer {
    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        private static final long serialVersionUID = 1L;

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> collector) throws Exception {
            //按空格分词
            String[] words = value.split(" ");
            //遍历所有word，包成二元组输出
            for(String word : words) {
                collector.collect(new Tuple2<>(word, 1));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //创建Flink执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //设置监控数据流的时间间隔
        env.enableCheckpointing(1000);
        //配置KafKa和Zookeeper的ip和端口
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "172.24.240.137:9092");
        properties.setProperty("zookeeper.connect", "172.24.240.137:2181");
        properties.setProperty("group.id", "base_log_app_group");

        //将kafka和zookeeper配置信息加载到Flink的执行环境当中StreamExecutionEnvironment
        FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<String>("ods_base_log", new SimpleStringSchema(),
                properties);

        //添加数据源，此处选用数据流的方式，将KafKa中的数据转换成Flink的DataStream类型
        DataStream<String> stream = env.addSource(myConsumer);
        //流计算，也是Flink的算子部分keyBy是通过key的形式进行分组，此处传入的0，表示是通过二元组的第一个元素也就是单词进行分组
        //LineSplitter是算子的具体逻辑
        DataStream<Tuple2<String, Integer>> counts = stream.flatMap(new LineSplitter())
                .keyBy(0)
                .sum(1);

        //打印输出
        counts.print();
        //执行Job，Flink执行环境必须要有job的执行步骤，而以上的整个过程就是一个Job
        env.execute("WordCount From KafKa data");
    }
}