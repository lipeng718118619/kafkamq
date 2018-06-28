package com.lp;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

/**
 * @program: kafkamq
 * @description:
 * @author: lipeng
 * @create: 2018-06-26 19:04
 **/
public class TestKafka
{

    public static void main(String[] args) throws InterruptedException
    {


        Properties props = new Properties();
        props.put("bootstrap.servers", "172.18.8.35:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer(props);

       // List ii = producer.partitionsFor("my-topic21");
        for(int i = 100; i < 20000; i++)
        {
            producer.send(new ProducerRecord<String, String>("tplus_log", Integer.toString(i), Integer.toString(i)));
        }
        producer.close();
    }

}
