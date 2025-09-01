package com.rouvsen.pdfgen1.config;

import com.rouvsen.pdfgen1.dao.entity.StatementEntity;
import com.rouvsen.pdfgen1.dao.repository.StatementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final StatementRepository statementRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<StatementEntity> entities = new ArrayList<>();
        if (statementRepository.count() == 0) {
            LocalDate statementDate = LocalDate.now();
            for (int i = 0; i < 1000; i++) {
                UUID uuid = UUID.randomUUID();

                entities.add(StatementEntity.builder()
                        .customerId("Customer-1")
                        .date(statementDate)
                        .description("Description-" + uuid)
                        .build());
            }
            statementRepository.saveAll(entities);
        }
    }
}
