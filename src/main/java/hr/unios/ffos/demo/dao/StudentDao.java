package hr.unios.ffos.demo.dao;

import hr.unios.ffos.demo.dao.mapper.StudentMapper;
import hr.unios.ffos.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

/**
 * Student Data Access Object
 * Responsible for reading, writing and changing the {@link hr.unios.ffos.demo.model.Student} from the database
 * */
@Service
public class StudentDao {

    private static final Logger log = LoggerFactory.getLogger(StudentDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentMapper studentMapper;

    public Student getStudent(UUID id) {
        var sql = "SELECT * FROM student WHERE id = ?";
        try {
            log.info("query for student {}", id);
            Student student = jdbcTemplate.queryForObject(sql, new Object[]{id}, studentMapper);
            log.info("returning student {}", student);
            return student;
        } catch (Exception e) {
            log.error("could not find student {}", id);
            return null;
        }
    }

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

    //UPDATE student SET first_name='ZELJKO' where id='840915ae-9c0d-4f8d-b546-db037c9787a9';
    public Student updateStudent(Student student) {
        //var sqlq = "UPDATE student SET first_name=?, last_name=?, age=?, subject=?, grade=?";
        var sql = "UPDATE student SET first_name = ?, last_name = ?, age = ?, subject = ?, grade = ? WHERE id = ?";
        var studentId = student.getId();
        try {
            log.info("updating student {}", studentId);
            jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getAge(), student.getSubject(), student.getGrade(), studentId);
            log.info("successfully updated student {} to {}", studentId, student);
            return student;
        } catch (Exception e) {
            log.error("could not update student {}", student.getId(), e);
            return null;
        }
    }
}
