package com.jaiwo99.cards.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.UnknownHostException;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Configuration
@PropertySource("classpath:system.test.properties")
public class AppTestConfig {

    @Value("${mongo.embedded.host}")
    private String mongodbHost;
    @Value("${mongo.embedded.port}")
    private String mongodbPort;

    @Bean
    public Mongo mongo() throws UnknownHostException {
        return new MongoClient(mongodbHost, Integer.valueOf(mongodbPort));
    }
}
