package hr.unios.ffos.demo.dao;

import hr.unios.ffos.demo.dao.mapper.StudentMapper;
import hr.unios.ffos.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Student Data Access Object
 * Responsible for reading, writing, changing & deleting the {@link hr.unios.ffos.demo.model.Student} from the database
 * */
@Service
public class StudentDao {

    private static final Logger log = LoggerFactory.getLogger(StudentDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentMapper studentMapper;

    /**
     * Get student from the DB
     * @param id - the unique student id
     * @return {@link Student}
     * */
    public Student getStudent(UUID id) {
        var sql = "SELECT * FROM student WHERE id = ?";
        try {
            log.info("query for student {}", id);
            Student student = jdbcTemplate.queryForObject(sql, new Object[]{id}, studentMapper);
            log.info("returning student {}", student);
            return student;
        } catch (Exception e) {
            log.error("could not find student {}", id, e);
            return null;
        }
    }

    /**
     * Create a new student
     * @param student - a new student object
     * @return {@link Student} - the newly created student
     * */
    public Student createStudent(Student student) {
        var sql = "INSERT INTO student(id, first_name, last_name, age, subject, grade) VALUES (?,?,?,?,?,?)";
        try {
            log.info("creating student {}", student);
            jdbcTemplate.update(sql, student.getId(), student.getFirstName(), student.getLastName(), student.getAge(), student.getSubject(), student.getGrade());
            log.info("successfully inserted student {}", student);
            return student;
        } catch (Exception e) {
            log.error("could not insert student {} ", student, e);
            return null;
        }
    }

    /**
     * Update an existing student
     * @param student - the update object of {@link Student}
     * @return {@link Student} - the updated student
     * */
    public Student updateStudent(Student student) {
        var sql = "UPDATE student SET first_name = ?, last_name = ?, age = ?, subject = ?, grade = ? WHERE id = ?";
        var studentId = student.getId();
        try {
            log.info("updating student {}", studentId);
            jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getAge(), student.getSubject(), student.getGrade(), studentId);
            log.info("successfully updated student {} to {}", studentId, student);
            return student;
        } catch (Exception e) {
            log.error("could not update student {}", studentId, e);
            return null;
        }
    }

    /**
     * Delete a student
     * @param id - an existing unique student id
     * @return true if successfully deleted or false if something went wrong
     * */
    public boolean deleteStudent(UUID id) {
        var sql = "DELETE student WHERE id = ?";
        try {
            log.info("deleting student {}", id);
            jdbcTemplate.update(sql, id);
            log.info("successfully deleted student {}", id);
            return true;
        } catch (Exception e) {
            log.error("could not delete student {}", id, e);
            return false;
        }
    }
}
