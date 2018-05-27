package com.polytech.persistence;

import com.polytech.services.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserRepository implements UserRepository {
    private Connection connection;

    private JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
    }

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(AppUser user) {

        String query = "INSERT INTO USERS (username, password, enabled)VALUES(?, ?,?)";
        jdbcTemplate.update(query, user.getUserName(), user.getEncrytedPassword(), "true");
    }

    @Override
    public AppUser findUserByUserName(String username) {
        String query = "SELECT * FROM Users where username = '" + username + "'";

        return jdbcTemplate.query(query, mapToUser()).get(0);
    }

    private RowMapper<AppUser> mapToUser() {
        return new UserMapper();
    }
}
