package com.rouvsen.pdfgen1.dao.repository;

import com.rouvsen.pdfgen1.dao.entity.StatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<StatementEntity, Long> {
    List<StatementEntity> findByDateBetween(LocalDate dateAfter, LocalDate dateBefore);
}
