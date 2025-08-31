package com.rouvsen.pdfgen1.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.rouvsen.pdfgen1.dao.entity.TestEntity;
import com.rouvsen.pdfgen1.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tests")
public class TestController {

    private final TestService testService;

    @GetMapping
    public ResponseEntity<TestEntity> getTestMessage(@RequestParam Long id) {
        return ResponseEntity.ok(testService.getHello(id));
    }

    @PostMapping
    public ResponseEntity<Void> saveTestMessage(@RequestBody TestEntity request) {
        testService.saveHello(request);
        return new ResponseEntity<>(CREATED);
    }
}
