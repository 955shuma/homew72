package com.example.homew72.util;

import com.example.homew72.model.User;
import com.example.homew72.repository.CommentRepository;
import com.example.homew72.repository.ThemeRepository;
import com.example.homew72.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PreloadDB {
    private final PasswordEncoder encoder;

    public PreloadDB(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository ur, ThemeRepository tr, CommentRepository cr){
        return (args) -> {
            ur.deleteAll();
            tr.deleteAll();
            cr.deleteAll();
            User user = new User();
            user.setMail("admin@a.r");
            user.setName("admin");
            user.setLogin("admin");
            user.setPassword(encoder.encode("admin"));
            ur.save(user);
        };
    }
}
