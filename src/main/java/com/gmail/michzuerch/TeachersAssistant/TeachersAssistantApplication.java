package com.gmail.michzuerch.TeachersAssistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeachersAssistantApplication {

    /**
     * The main method makes it possible to run the application as a plain Java
     * application which starts embedded web server via Spring Boot.
     * <p>
     * Ein Commit nach init
     *
     * @param args command line parameters
     */
    public static void main(String[] args) {
        SpringApplication.run(TeachersAssistantApplication.class, args);
    }
}
