package com.polytech.config;

import com.polytech.persistence.*;
import com.polytech.services.FeedService;
import com.polytech.services.PublicationService;
import com.polytech.services.UserDetailsServiceImpl;
import com.polytech.web.FeedController;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcUserRepository(jdbcTemplate);
    }

    @Bean
    public RoleRepository roleRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRoleRepository(jdbcTemplate);
    }

    //    @Bean
//    public StoryRepository storyRepository(DataSource dataSource) throws SQLException {
//        return new JdbcStoryRepository(dataSource.getConnection());
//    }
    @Bean
    public StoryRepository storyRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcStoryRepository(jdbcTemplate);
    }

    @Bean
    public FeedService
    feedService(StoryRepository storyRepository) {
        return new FeedService(storyRepository);
    }

    @Bean
    public PublicationService publicationService(StoryRepository storyRepository) {
        return new PublicationService(storyRepository);
    }

    @Bean
    public FeedController feedController(PublicationService publicationService, FeedService feedService) {
        return new FeedController(publicationService, feedService);
    }


//    @Bean
//    public JdbcStoryRepository jdbcStoryRepository(DataSource dataSource) throws SQLException {
//        return new JdbcStoryRepository(dataSource.getConnection());
//    }

//    @Bean
//    public JdbcStoryRepository jdbcStoryRepository() throws SQLException {
//        return new JdbcStoryRepository(jdbcTemplate());
//    }

    @Bean
    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .build();
        String url = "jdbc:mysql://localhost:3306/polytech";
        String username = "root";
        String password = "root";

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
