package com.seecity.app.seecityms2.MS2.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${KAFKA_ADDRESS}:9092")
    private String bootstrapAddress;

    @Value(value = "monitoring")
    private String kafkaMainTopic;

    @Value(value = "cittaTopic")
    private String kafkaMainTopicCitta;


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic(kafkaMainTopic, 100, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic(kafkaMainTopicCitta, 100, (short) 1);
    }
}