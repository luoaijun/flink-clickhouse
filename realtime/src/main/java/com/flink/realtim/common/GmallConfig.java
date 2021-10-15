package com.flink.realtim.common;

/**
 * Author: Felix
 * Date: 2021/2/2
 * Desc: 项目配置的常量类
 */
public class GmallConfig {
    //Hbase的命名空间
    public static final String HABSE_SCHEMA = "GMALL0820_REALTIME";

    //Phonenix连接的服务器地址
    public static final String PHOENIX_SERVER="jdbc:phoenix:172.24.240.137,172.24.240.137,172.24.240.137:2181";

    //ClickHouse的URL连接地址
    public static final String CLICKHOUSE_URL="jdbc:clickhouse://172.24.240.137:8123/default";

}
