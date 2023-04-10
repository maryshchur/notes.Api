//package com.example.notesapi.config;
//
//
//import org.springframework.context.annotation.Configuration;
//
//import java.net.UnknownHostException;
//import java.util.Collections;
//
//@Configuration
////@ConfigurationProperties(prefix = "spring.data.mongodb")
////@EnableMongoRepositories(basePackages = "org.spring.mongo.demo")
//public class MongoConfig extends AbstractMongoConfiguration {
//
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
