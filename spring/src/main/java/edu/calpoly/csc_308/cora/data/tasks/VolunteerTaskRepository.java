package edu.calpoly.csc_308.cora.data.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerTaskRepository extends JpaRepository<VolunteerTask, Long> {
    // auto-populated by JPA
}