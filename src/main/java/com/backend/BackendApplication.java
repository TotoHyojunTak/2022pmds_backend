package com.backend;

import feign.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableFeignClients
public class BackendApplication {

    public static void main(String[] args) {
        enc();

        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    private static void enc(){
        String url = "jdbc:log4jdbc:mariadb://146.56.114.57:3306/user";
        String username = "user";
        String password = "user1234!@#$";

        String encryptUrl = jasyptEncrypt(url);
        String encryptUsername = jasyptEncrypt(username);
        String encryptPassword = jasyptEncrypt(password);

        System.out.println("user encryptUrl : " + encryptUrl);
        System.out.println("user encryptUsername : " + encryptUsername);
        System.out.println("user encryptPassword : " + encryptPassword);

        url = "jdbc:log4jdbc:mariadb://146.56.114.57:3306/order";
        username = "order";
        password = "order1234!@#$";

        encryptUrl = jasyptEncrypt(url);
        encryptUsername = jasyptEncrypt(username);
        encryptPassword = jasyptEncrypt(password);

        System.out.println("order encryptUrl : " + encryptUrl);
        System.out.println("order encryptUsername : " + encryptUsername);
        System.out.println("order encryptPassword : " + encryptPassword);

        url = "jdbc:log4jdbc:mariadb://146.56.114.57:3306/catalog";
        username = "catalog";
        password = "catalog1234!@#$";

        encryptUrl = jasyptEncrypt(url);
        encryptUsername = jasyptEncrypt(username);
        encryptPassword = jasyptEncrypt(password);

        System.out.println("catalog encryptUrl : " + encryptUrl);
        System.out.println("catalog encryptUsername : " + encryptUsername);
        System.out.println("catalog encryptPassword : " + encryptPassword);
    }

     private static String jasyptEncrypt(String input) {
        String key = "A421080gsm";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.encrypt(input);
    }

    private static String jasyptDecryt(String input){
        String key = "A421080gsm";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.decrypt(input);
    }

}
