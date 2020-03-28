package com.mydeveloperplanet.myliquibaseplanet.dao;

import com.mydeveloperplanet.myliquibaseplanet.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEE", new EmployeeRowMapper());
    }

    public int addEmployee(final String firstName, String lastName, String country) {
        return jdbcTemplate.update("INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, COUNTRY) VALUES (?, ?, ?)",
                firstName, lastName, country);
    }

    private static final class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final Employee employee = new Employee(rs.getLong("ID"),
                                                   rs.getString("FIRST_NAME"),
                                                   rs.getString("LAST_NAME"),
                                                   rs.getString("COUNTRY"));
            return employee;
        }

    }

}
