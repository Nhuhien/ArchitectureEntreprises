package com.polytech.persistence;

import com.polytech.services.Story;
import com.polytech.services.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcStoryRepository implements StoryRepository {


    private Connection connection;

    private JdbcTemplate jdbcTemplate;

    public JdbcStoryRepository(Connection connection) {
        this.connection = connection;
    }
    public JdbcStoryRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public void save(Story story) {
//        String query = "INSERT INTO STORY (CONTENT)VALUES('" + story.getContent() + "')";
        String query = "INSERT INTO STORY (CONTENT)VALUES(?)";
        jdbcTemplate.update(query, story.getContent());
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public List<Story> findAll() {
        String query = "SELECT * FROM STORY";

        return jdbcTemplate.query(query, mapToStory());
//        List<Story> stories = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                String content = resultSet.getString("CONTENT");
//                stories.add(new Story(content));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return stories;
    }

    private RowMapper<Story> mapToStory() {
        return new RowMapper<Story>() {
            @Override
            public Story mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                String content = resultSet.getString("CONTENT");
                return new Story(content);
            }
        };
    }

    //une autre solution: creer une classe StoryMapper qui implemente RowMapper.
}
