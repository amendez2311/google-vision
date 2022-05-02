package com.upc.ia.repository;

import com.upc.ia.entity.GoogleVisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoogleVisionEntityRepository extends JpaRepository<GoogleVisionEntity, Long>{
	List<GoogleVisionEntity> findByDescription(String description);
}
