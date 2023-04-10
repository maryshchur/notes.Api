//package com.example.notesapi.config;
//
//
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.MongoClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//import java.net.UnknownHostException;
//import java.util.Collections;
//
//@Configuration
//@ConfigurationProperties(prefix = "spring.data.mongodb")
////@EnableMongoRepositories(basePackages = "org.spring.mongo.demo")
//public class MongoConfig
////        extends AbstractMongoConfiguration {
//{
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public MongoDbFactory mongoDbFactory() {
//        return new SimpleMongoDbFactory(new MongoClientURI(env.getProperty("spring.data.mongodb.uri")));
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
//
//        return mongoTemplate;
//
//    }
//
//    //    @Value("${project.mongodb.hostname}")
//    private String host;
//
//    //    @Value("${project.mongodb.port}")
//    private Integer port;
//
//    //    @Value("${project.mongodb.name}")
//    private String database;
//
////    @Value("${project.mongodb.username}")
//    private String username;
//
////    @Value("${project.mongodb.password}")
//    private String password;
//
////    @Value("${project.mongodb.authenticationDB}")
//    private String authenticationDB;
//
//
//    @Bean
//    public MongoTemplate mongoTemplate()
//            throws UnknownHostException, java.net.UnknownHostException {
//        return new MongoTemplate(
//                new SimpleMongoDbFactory(
//                        mongoClient(),
//                        getDatabaseName()
//                )
//        );
//    }
//
//    @Override
//    @Bean
//    public MongoClient mongoClient() {
//        MongoClient mongoClient = null;
//        try {
//            mongoClient = new MongoClient(
//                    new ServerAddress(host, port),
//                    Collections.singletonList(
//                            MongoCredential.createMongoCRCredential(
//                                    username,
//                                    authDB,
//                                    password.toCharArray()
//                            )
//                    )
//            );
//        } catch (java.net.UnknownHostException ex) {
////                Logger.getLogger(MongoDBConfiguration.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return mongoClient;
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return database;
//    }
//
//}
