package edu.calpoly.csc_308.cora.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
    // auto-populated by JPA
}