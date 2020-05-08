package edu.calpoly.csc_308.cora.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
    // auto-populated by JPA
}
