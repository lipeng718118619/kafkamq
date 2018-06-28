package com.lp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @program: kafkamq
 * @description:
 * @author: lipeng
 * @create: 2018-06-27 10:31
 **/
public class ConsumeKafka
{
    public static void main(String[] args) throws InterruptedException
    {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.18.8.35:9092");
        props.put("group.id", "tplus_log_test");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset","earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer(props);
        consumer.subscribe(Arrays.asList("tplus_log"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records)
            {
                System.err.printf("partition = %d, offset = %d, key = %s, value = %s%n", record.partition(),record.offset(), record.key(), record.value());
                //Thread.sleep(1000);
            }
            consumer.commitSync();
        }
    }

}
