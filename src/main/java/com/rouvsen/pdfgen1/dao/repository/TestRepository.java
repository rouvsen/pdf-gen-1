package com.rouvsen.pdfgen1.dao.repository;

import com.rouvsen.pdfgen1.dao.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
