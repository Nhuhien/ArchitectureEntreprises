package com.polytech.persistence;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.List;

public class JdbcRoleRepository implements RoleRepository {
    private Connection connection;

    private JdbcTemplate jdbcTemplate;

    public JdbcRoleRepository(Connection connection) {
        this.connection = connection;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public JdbcRoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getRoleNames(Long userId) {
        String sql = "Select r.Role_Name " //
                + " from User_Role ur, App_Role r " //
                + " where ur.Role_Id = r.Role_Id and ur.User_Id = ? ";

        Object[] params = new Object[]{userId};

        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }
}
