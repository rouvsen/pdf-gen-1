package com.rouvsen.pdfgen1.controller;

import com.rouvsen.pdfgen1.model.dto.StatementRequestDto;
import com.rouvsen.pdfgen1.service.StatementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statements")
public class StatementController {

    private final StatementService statementService;

    @GetMapping("/total")
    public Long getStatementsSize() {
        return statementService.getStatementsSize();
    }

    @GetMapping("/pdf")
    public ResponseEntity<Resource> downloadStatementPdf(@Valid StatementRequestDto statementRequestDto) {
        return statementService.downloadStatementPdf(statementRequestDto);
    }
}
