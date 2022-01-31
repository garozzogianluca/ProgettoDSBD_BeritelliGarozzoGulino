package com.seecity.app.seecityms1;

import com.seecity.app.seecityms1.MS1.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeecityMs1Application {


    public static void main(String[] args) {
        SpringApplication.run(SeecityMs1Application.class, args);

        System.out.println("\nMS1 in esecuzione!");
    }


}
