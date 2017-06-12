package com.org.spring.boot.service;

import com.org.spring.boot.HelloworldApplication;
import com.org.spring.boot.model.Signature;
import com.org.spring.boot.model.User;
import com.org.spring.boot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by himansu on 05/06/2017.
 */
@Slf4j
@Service
public class HelloworldService {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }

    public void createUser() throws Exception {

        // Add parent and child
        User chaminda = new User("Chaminda");
        Signature chamindaSignature = new Signature(readSignature("/signature.jpg"), "jpg");
        chaminda.setSignature(chamindaSignature);

        // Add parent only
        List<User> userList = new ArrayList<>();
        userList.add(chaminda);
        userList.add(new User("Himansu"));
        userRepository.save(userList);

        // Add child to existing parent
        User userHimansu = userRepository.findSignatureByUser("Himansu");
        log.info("User : " + userHimansu);
        Signature signature = new Signature(readSignature("/signature.png"), "png");
        userHimansu.setSignature(signature);
        userRepository.save(userHimansu);

        writeSignature(userRepository.findByUsername("Himansu"));


        // remove parent and child - cascade delete
        User removeHimansu = userRepository.findSignatureByUser("Himansu");
        userRepository.delete(removeHimansu);

        writeSignature(userRepository.findByUsername("Chaminda"));

        // remove child only - orphan delete
        User removeSignature = userRepository.findSignatureByUser("Chaminda");
        log.info("User : " + removeSignature);

        removeSignature.setSignature(null);
        userRepository.save(removeSignature);

        // remove childless parent
        User removeChaminda = userRepository.findSignatureByUser("Chaminda");
        log.info("User : " + removeChaminda);
        userRepository.delete(removeChaminda);

    }

    private static byte[] readSignature(String file){
        try {
            return IOUtils.toByteArray(new ClassPathResource(file).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static void writeSignature(User user){
        Path path = Paths.get("/Users/himansu/Downloads/temp/signature" + "." + user.getSignature().getFileType());
        try {
            Files.write(path, user.getSignature().getSignature());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
