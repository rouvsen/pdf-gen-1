package com.rouvsen.pdfgen1.service;

import com.rouvsen.pdfgen1.dao.entity.TestEntity;
import com.rouvsen.pdfgen1.dao.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public TestEntity getHello(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test not found with id " + id));
    }

    public void saveHello(TestEntity entity) {
        testRepository.save(entity);
    }
}
