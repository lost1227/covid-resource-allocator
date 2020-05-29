package edu.calpoly.csc_308.cora.data.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserDAO, Long> {
    
    @Query("select u from UserDAO u where lower(u.name) like lower(concat('%', ?1, '%'))")
    List<UserDAO> findByName(String name);

    UserDAO findByUsername(String username);

}
