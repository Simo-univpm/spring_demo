package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * per inizializzazione db all'avvio.
 * classe superflua ed eliminabile.
 */


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {

        return args -> {

            Student asder = new Student(
                    "asder",
                    "asder@gmail.com"
            );

            Student asderMaster = new Student(
                    "master",
                    "master@gmail.com"
            );

            repository.saveAll(List.of(asder, asderMaster));

        };

    }

}