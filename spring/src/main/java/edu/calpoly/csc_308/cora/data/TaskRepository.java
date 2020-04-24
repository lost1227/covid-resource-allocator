package edu.calpoly.csc_308.cora.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // auto-populated by JPA
}
