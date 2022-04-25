package hr.unios.ffos.demo.controller;

import hr.unios.ffos.demo.dao.StudentDao;
import hr.unios.ffos.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentDao studentDao;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") UUID id) {
        log.info("getting student {}", id);
        Student student = studentDao.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        student.setId(UUID.randomUUID());
        log.info("creating student {}", student);
        Student createdStudent = studentDao.createStudent(student);
        if (createdStudent == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student) {
        Student dbStudent = studentDao.getStudent(student.getId());
        if (dbStudent == null) {
            log.error("student with the id {} does not exist", student.getId());
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        } else {
            log.info("updating student {} with following {}", dbStudent.getId(), student);
            Student updatedStudent = studentDao.updateStudent(dbStudent);
            if (updatedStudent == null) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            } else {
                return new ResponseEntity<>(updatedStudent, HttpStatus.ACCEPTED);
            }
        }
    }

}
