package com.shamstabrez16.restcrud;

import com.shamstabrez16.restcrud.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void save(Student student) {
        String sqlQuery = "insert into students(first_name, last_name) " +
                "values (?, ?)";
        jdbcTemplate.update(sqlQuery,
                student.getFirstName(),
                student.getLastName()
                );
    }

    public Student saveAndReturnId(Student student) {
        String sqlQuery = "insert into students(first_name, last_name) " +
                "values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"id"});
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            return stmt;
        }, keyHolder);
        return  findOne(keyHolder.getKey().longValue());
    }
    public Student findOne(long id) {
        String sqlQuery = "select id, first_name, last_name " +
                "from students where id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToStudent, id);
    }

    public List<Student> findAll() {
        String sqlQuery = "select id, first_name, last_name from students";
        return jdbcTemplate.query(sqlQuery, this::mapRowToStudent);
    }

    private Student mapRowToStudent(ResultSet resultSet, int rowNum) throws SQLException {
        return Student.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .build();
    }
    public Student update(Student student) {
        String sqlQuery = "update students set " +
                "first_name = ?, last_name = ? " +
                "where id = ?";
        jdbcTemplate.update(sqlQuery
                , student.getFirstName()
                , student.getLastName()
                , student.getId());
        return findOne(student.getId());

    }

    public boolean delete(long id) {
        String sqlQuery = "delete from students where id = ?";
        return jdbcTemplate.update(sqlQuery, id) > 0;
    }

}
