package com.org.spring.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mvn package && java -jar target/commandlinerunner.jar "Himansu Nayak"
 */
@SpringBootApplication
public class SpringBootCommandLineRunner implements CommandLineRunner {

    public static void main(String...args) {
        // SpringApplication.run(SpringBootCommandLineRunner.class, args);
        SpringApplication sa = new SpringApplication(SpringBootCommandLineRunner.class);
        sa.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        for(final String string : strings){
            System.out.println("strings = [" + strings + "]");
        }
    }
}
