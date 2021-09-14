package com.tildev;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class SimpleProducer {
    private final static Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

    // 생성한 레코드를 전송하기 위해 전송하고자 하는 토픽을 알고 있어야 함. 토픽 이름은 Producer Record 인스턴스를 생성할 때 사용.
    private final static String TOPIC_NAME = "test";
    // 전송하고자 하는 카프카 클러스터 서버의 host, IP 지정
    private final static String BOOTSTRAP_SERVERS = "my-kafka:9092";

    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);

        String messageValue = "testMessage";
        // 카프카 브로커로 데이터를 보내기 위해 ProducerRecord를 생성.
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, messageValue);

        producer.send(record);
        logger.info("{}", record);
        producer.flush();
        producer.close();
    }
}
