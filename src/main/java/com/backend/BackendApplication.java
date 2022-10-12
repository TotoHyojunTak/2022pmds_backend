package com.backend;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
public class BackendApplication {

    public static void main(String[] args) {
        enc();

        SpringApplication.run(BackendApplication.class, args);
    }

    private static void enc(){
        String url = "jdbc:log4jdbc:mariadb://146.56.114.57:3306/2022pmds";
        String username = "2022pmds";
        String password = "1q2w3e4r!@";

        String encryptUrl = jasyptEncrypt(url);
        String encryptUsername = jasyptEncrypt(username);
        String encryptPassword = jasyptEncrypt(password);

        System.out.println("oci encryptUrl : " + encryptUrl);
        System.out.println("encryptUsername : " + encryptUsername);
        System.out.println("encryptPassword : " + encryptPassword);
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
