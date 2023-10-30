package com.example.demo.student;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

/** questa classe la mappiamo su una tabella del db tramite le annotazioni */

@Entity // per hibernate: Specifies that the class is an entity
@Table  // per hibernate: Specifies the table in the database with which this entity is mapped. ???
public class Student {

    // attributi

    @Id // auto generated, auto increment, primary key
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;

    // costruttore default, necessario a Hibernate per l'istanziazione dell'entità
    public Student(){}

    // costruttore senza id per database, l'id lo fornisce automaticamente
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // costruttore classico
    public Student(Long id, String name, String email) {

     this.id = id;
     this.name = name;
     this.email = email;

     }



    // getters e setters ======================================================================

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
