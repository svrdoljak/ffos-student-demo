package hr.unios.ffos.demo.dao.mapper;

import hr.unios.ffos.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.UUID;

/**
 * A mapper to map the ResultSet from the DB into a {@link Student}
 * */
@Service
public class StudentMapper implements RowMapper<Student> {

    private static final Logger log = LoggerFactory.getLogger(StudentMapper.class);

    @Override
    public Student mapRow(ResultSet rs, int rowNum) {
        try {
            log.info("mapping student");
            Student student = new Student();
            student.setId((UUID) rs.getObject("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setAge(rs.getInt("age"));
            student.setSubject(rs.getString("subject"));
            student.setGrade(rs.getInt("grade"));
            return student;
        } catch (Exception e) {
            log.error("could not map student ", e);
            return null;
        }

    }
}
