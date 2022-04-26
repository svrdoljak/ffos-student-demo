package hr.unios.ffos.demo.controller;

import hr.unios.ffos.demo.dao.StudentDao;
import hr.unios.ffos.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 * REST controller for the {@link Student}
 * */
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentDao studentDao; //todo use a service layer between the controller and dao

    /**
     * Get a {@link Student}
     * @param id - the unique student id
     * @return {@link Student}
     * */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") UUID id) {
        log.info("getting student {}", id);
        Student student = studentDao.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /**
     * Create a {@link Student}
     * @param student - the student object which is being validated
     * @return {@link Student} - the created student
     * */
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        //generate a random unique UUID
        student.setId(UUID.randomUUID());
        log.info("creating student {}", student);
        Student createdStudent = studentDao.createStudent(student);
        if (createdStudent == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    /**
     * Update a {@link Student}
     * @param student - the student update object
     * @return {@link Student} the updated student
     * */
    @PutMapping
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student) {
        //check first if the student exists
        Student dbStudent = studentDao.getStudent(student.getId());
        if (dbStudent == null) {
            log.error("student with the id {} does not exist", student.getId());
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        } else {
            log.info("updating student {} with following {}", dbStudent.getId(), student);
            Student updatedStudent = studentDao.updateStudent(student);
            if (updatedStudent == null) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            } else {
                return new ResponseEntity<>(updatedStudent, HttpStatus.ACCEPTED);
            }
        }
    }

    /**
     * Delete a {@link Student}
     * @param id - the unique student id
     * @return 202 HttpStatus if successfully deleted or 417 expectation failed if something went wrong
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        log.info("deleting student with the id {}", id);
        var deleted = studentDao.deleteStudent(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            log.error("could not delete student {}", id);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
