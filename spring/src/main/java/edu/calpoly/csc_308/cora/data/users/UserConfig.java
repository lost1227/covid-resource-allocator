package edu.calpoly.csc_308.cora.data.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    Logger logger = LoggerFactory.getLogger(UserConfig.class);

    @Bean
    CommandLineRunner initUsers(UserRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(
                new UserDAO(
                    "Memorialcare Health System",
                    "Long Beach, CA",
                    "provider",
                    "Hospital located in Long Beach, California",
                    new String[] {})));
            logger.info("Preloading " + repository.save(
                new UserDAO(
                    "Blue Shield of California",
                    "Long Beach, CA",
                    "provider",
                    "Insurance company serving Long Beach, California",
                    new String[] {})));
            logger.info("Preloading " + repository.save(
                new UserDAO(
                    "Jordan Powers",
                    "Long Beach, CA",
                    "volunteer",
                    "Student living in Long Beach, CA", 
                    new String[] { "programming" })));
            };
    }
    
}
