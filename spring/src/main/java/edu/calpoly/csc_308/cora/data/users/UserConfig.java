package edu.calpoly.csc_308.cora.data.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserConfig {

    Logger logger = LoggerFactory.getLogger(UserConfig.class);

    @Bean
    CommandLineRunner initUsers(UserRepository repository) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return args -> {
            logger.info("Preloading " + repository.save(
                new UserDAO(
                    "Memorialcare Health System",
                    "Long Beach, CA",
                    "provider",
                    "Hospital located in Long Beach, California",
                    new String[] {},
                    -1L,
                    "memorialcare",
                    encoder.encode("pass"))));
            logger.info("Preloading " + repository.save(
                new UserDAO(
                    "Blue Shield of California",
                    "Long Beach, CA",
                    "provider",
                    "Insurance company serving Long Beach, California",
                    new String[] {},
                    -1L,
                    "bshield",
                    encoder.encode("super secure password"))));
            logger.info("Preloading " + repository.save(
                new UserDAO(
                    "Jordan Powers",
                    "Long Beach, CA",
                    "volunteer",
                    "Student living in Long Beach, CA",
                    new String[] { "programming" },
                    -1L,
                    "jordan",
                    encoder.encode("password123"))));
            logger.info("Preloading " + repository.save(
                    new UserDAO(
                            "Finian Rawson",
                            "Monterey, CA",
                            "volunteer",
                            "Student living in Monterey, CA",
                            new String[] { "programming" },
                            -1L,
                            "frawson",
                            encoder.encode("password123"))));
            };

    }
    
}
