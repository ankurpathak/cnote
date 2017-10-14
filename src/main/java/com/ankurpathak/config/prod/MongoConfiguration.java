package com.ankurpathak.config.prod;

import com.ankurpathak.annotation.Prod;
import com.ankurpathak.mongo.codec.BigIntegerCodec;
import com.mongodb.*;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Collections;

/**
 * Created by ankur on 07-02-2017.
 */
@Configuration
@ConfigurationProperties("spring.data.mongodb")
@EnableMongoAuditing(auditorAwareRef = "usernameAuditorAware")
@Prod
public class MongoConfiguration extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {

        CodecRegistry codecs = CodecRegistries.fromCodecs(new BigIntegerCodec());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), codecs);
       //MongoClientOptions options = MongoClientOptions.builder().socketKeepAlive(true).connectTimeout(30000).maxConnectionIdleTime(60000).codecRegistry(codecRegistry).build();
        MongoClientOptions options = MongoClientOptions.builder().maxConnectionIdleTime(60000).codecRegistry(codecRegistry).build();
      //  MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
      //  MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), Collections.singletonList(credential),options);
        MongoClientURI uri = new MongoClientURI(getUri(), MongoClientOptions.builder(options));
        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient;

    }


    private String uri;



    private String database;

    private String host;

    private Integer port;

    private String password;

    private String username;


    public void setDatabase(String database) {
        this.database = database;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }



    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }


    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
