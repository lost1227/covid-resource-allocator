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
            "Long Beach, CA",
            "provider",
            "Insurance company serving Long Beach, California",
            new String[] {}),
          -1L,
          "bshield",
          encoder.encode("super secure password"))
      );
      UserDAO user3 = repository.save(
        new UserDAO(
          new UserDAO.ProfileInfo(
            "Jordan Powers",
            "Long Beach, CA",
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

      logger.info("Preloading {}", user1);
      logger.info("Preloading {}", user2);
      logger.info("Preloading {}", user3);
      logger.info("Preloading {}", user4);
      };

  }
    
}
