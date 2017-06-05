package com.org.spring.boot;

import com.org.spring.boot.model.Signature;
import com.org.spring.boot.model.User;
import com.org.spring.boot.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HelloworldApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(HelloworldApplication.class);

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

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
        Signature signature = new Signature(readSignature("/signature.png"), "png");
        userHimansu.setSignature(signature);
        userRepository.save(userHimansu);

        writeSignature(userRepository.findByUsername("himansu"));


        // remove parent and child - cascade delete
        User removeHimansu = userRepository.findSignatureByUser("Himansu");
        userRepository.delete(removeHimansu);

        writeSignature(userRepository.findByUsername("chaminda"));

        // remove child only - orphan delete
        User removeSignature = userRepository.findSignatureByUser("chaminda");
        removeSignature.setSignature(null);
        userRepository.save(removeSignature);

        // remove childless parent
        User removeChaminda = userRepository.findSignatureByUser("chaminda");
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
