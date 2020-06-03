package edu.calpoly.csc_308.cora.data.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserConfig {
  private static final String PRELOADING = "Preloading {}";

  Logger logger = LoggerFactory.getLogger(UserConfig.class);

  @Bean
  CommandLineRunner initUsers(UserRepository repository) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return args -> {
      if(repository.findAll().isEmpty()) {
        UserDAO user1 = repository.save(
          new UserDAO(
            new UserDAO.ProfileInfo(
              "Memorialcare Health System",
              "Long Beach, CA",
              "provider",
              "Hospital located in Long Beach, California",
              new String[] {}),
            -1L,
            "memorialcare",
            encoder.encode("pass"))
        );
        UserDAO user2 = repository.save(
          new UserDAO(
            new UserDAO.ProfileInfo(
              "Blue Shield of California",
              "Los Angeles, CA",
              "provider",
              "Insurance company serving Los Angeles, California",
              new String[] {}),
            -1L,
            "bshield",
            encoder.encode("super secure password"))
        );
        UserDAO user3 = repository.save(
          new UserDAO(
            new UserDAO.ProfileInfo(
              "Jordan Powers",
              "Long Beach",
              "volunteer",
              "Student living in Long Beach, CA",
              new String[] { "programming" }),
            -1L,
            "jordan",
            encoder.encode("password123"))
        );
        UserDAO user4 = repository.save(
          new UserDAO(
            new UserDAO.ProfileInfo(
              "Finian Rawson",
              "Monterey, CA",
              "volunteer",
              "Student living in Monterey, CA",
              new String[] { "programming" }),
            -1L,
            "frawson",
            encoder.encode("password123"))
        );

        logger.info(PRELOADING, user1);
        logger.info(PRELOADING, user2);
        logger.info(PRELOADING, user3);
        logger.info(PRELOADING, user4);
      }
      };
  }
    
}
