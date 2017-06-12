package com.org.spring.boot;

import com.org.spring.boot.service.HelloworldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class HelloworldApplication implements CommandLineRunner {

    private final HelloworldService helloworldService;

    /**
     * Instantiates a new instance of the Signature Controller
     *
     * @param helloworldService
     *         the helloworld service
     */
    @Autowired
    public HelloworldApplication(HelloworldService helloworldService) {
        this.helloworldService = helloworldService;
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        helloworldService.createUser();

    }
}
