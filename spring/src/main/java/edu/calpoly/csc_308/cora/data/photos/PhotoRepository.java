package edu.calpoly.csc_308.cora.data.photos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoDAO, Long>{
  
}
