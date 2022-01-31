/*package com.seecity.app.seecityms1.MS1.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(
        exclude = {
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class,
                EmbeddedMongoAutoConfiguration.class
        }
)
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@ComponentScan({"com.seecity.app", "com.seecity.app.seecityms1"})
@EnableMongoRepositories ("com.seecity.app.seecityms1")
public class MongoConfiguration extends AbstractMongoClientConfiguration {
    @Value(value = "${MONGO_HOST:localhost}")
    private String mongoHost;

    @Value(value = "${MONGO_ROOT_USERNAME:root}")
    private String mongoUser;

    @Value(value = "${MONGO_ROOT_PASSWORD:root}")
    private String mongoPass;

    @Value(value = "${MONGO_AUTH_DB:admin}")
    private String mongoAuthDB;

    @Value(value = "${MONGO_PORT:27017}")
    private String mongoPort;

    @Value(value = "${MONGO_DB_NAME:MS1}")
    private String mongoDBName;

    public MongoConfiguration() {
    }

    @Override
    protected String getDatabaseName() {
        return this.mongoDBName;
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        String s = String.format("mongodb://%s:%s@%s:%s/%s?authSource=%s",
                mongoUser, mongoPass, mongoHost, mongoPort, mongoDBName, mongoAuthDB);
        return MongoClients.create(s);
    }
}


 */
