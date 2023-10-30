package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** API LAYER */

/**
 * Classe che definisce i metodi per
 * intercettare le richieste HTTP (get, post, put e delete)
 * e di smistarle correttamente tramite gli appositi metodi
 * */

/**
 * note su dependency injection:
 *
 * l'annotazione @Autowired ci permette di
 * passare tramite dependency injection uno studentService al costruttore.
 *
 * Ce lo istanzia e ci fa la injection automaticamente, andando
 * a cercare in StudentService i compoenenti annotati
 * con @Component o @Service.
 *
 * Ci evita di fare "new StudentServiceLayer(...)"
 */

@RestController // @RestController che dice che questo è un rest controller
@RequestMapping(path = "api/student") // @RequestMapping is used to map HTTP requests to handler methods (REST controllers).
public class StudentController {

    // attributo
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /** METODI REST ============================================================================== */

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    // seleziona il request body della post e la si mappa nella classe Student
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    /** mio put
    @PutMapping(path = "{studentId}")
    public void updateStudent(@RequestBody Student data, @PathVariable("studentId") Long studentId){
        studentService.updateStudent(data, studentId);
    }
    */

    /**
     * put del video.
     * prende parametri della query string e quindi richiede un url del genere:
     *
     * PUT .../api/student/1?name=sca
     * PUT .../api/student/1?email=sca@gmail.com
     * PUT .../api/student/1?name=sca&email=sca@gmail.com
     */
    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

                studentService.updateStudent(studentId, name, email);

    }

}
