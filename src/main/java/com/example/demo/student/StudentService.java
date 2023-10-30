package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** SERVICE LAYER */

/**
 * Classe per la gestione della business logic dello studente.
 *
 * @Service indica che questa è una service class, ovvero
 * una classe che mette a disposizione della business logic
 * */

@Service
public class StudentService {

    // attributi
    private final StudentRepository studentRepository;

    // costruttore
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // metodi
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email already in use");
        }

        studentRepository.save(student);
        System.out.println("student " + student.getName() + " saved to db");

    }

    public void deleteStudent(Long studentId){

        boolean exists = studentRepository.existsById(studentId);

        if (! exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exists");
        }

        studentRepository.deleteById(studentId);
        System.out.println("Student with id " + studentId + " deleted from db");

    }

    /** mio put
    @Transactional
    public void updateStudent(Student data, Long studentId){

        // controllo esistenza
        boolean exists = studentRepository.existsById(studentId);

        if (! exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exists");
        }

        // recupero studente dal db
        Student existingStudent = studentRepository.getOne(studentId);

        // aggiornamento
        existingStudent.setEmail(data.getEmail());
        existingStudent.setName(data.getName());

        // salvataggio modifiche
        studentRepository.save(existingStudent);
        System.out.println("student " + studentId + " updated");

    }
    */

    // put del video
    @Transactional // @Transactional ci permette di usare i getters e i setters invece delle query per interagire col db
    public void updateStudent(Long studentId, String name, String email) {

        // recupero studente dal db
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id" + studentId + " does not exist"));

        // se il nome non è uguale
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        // se la mail non è uguale e non è già utilizzata
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){

            /**
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if(studentOptional.isPresent()) {
                throw new IllegalStateException("Email already in use");
            }
            */

            student.setEmail(email);
        }

    }


}
