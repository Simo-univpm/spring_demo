package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** DATA ACCESS LAYER */

/**
 * Questa interfaccia tramite l'extends, ci mette a disposizione
 * una serie di metodi per accedere e manipolare i dati a db,
 * per questo si chiama data access layer.
 *
 * StudentRepository --> il termine "Repository" è una convenzione
 * per nominare classi quando queste interagiscono col db in
 * in particolare se stiamo usando JPA come noi.
 *
 * JpaRepository è un generics e va specificato <T, id T>
 * T = classe Student, Long = data type id di Student
 *
 * @Repository: indica che questa interfaccia è responsabile
 * dell'accesso ai dati al db
 */

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {


    /** se si uncommenta la query, verrà eseguita in
     * specifico quella query, altrimenti è automatica.
     *
     * Optional<T>: A container object which may or may not contain
     * a non-null value. If a value is present, isPresent()
     * will return true and get() will return the value.
     */
    //@Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}