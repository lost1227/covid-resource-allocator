package edu.calpoly.csc_308.cora.data.supplies;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<SupplyDAO, Long> {
    // auto-populated by JPA
}