package com.rouvsen.pdfgen1.service;

import com.rouvsen.pdfgen1.dao.entity.StatementEntity;
import com.rouvsen.pdfgen1.dao.repository.StatementRepository;
import com.rouvsen.pdfgen1.model.dto.StatementRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementService {

    private final FileGenerator fileGenerator;
    private final StatementRepository statementRepository;

    public Long getStatementsSize() {
        return statementRepository.count();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Resource> downloadStatementPdf(StatementRequestDto requestDto) {
        List<StatementEntity> statements =
                statementRepository.findByDateBetween(requestDto.getBeginDate(), requestDto.getEndDate());

        byte[] pdfBytes = fileGenerator.generateStatementPdf(statements, requestDto);

        return getResourceResponseEntity(pdfBytes, MediaType.APPLICATION_PDF_VALUE, "accountStatement.pdf");
    }

    public ResponseEntity<Resource> getResourceResponseEntity(byte[] bytes,
                                                              String mediaType,
                                                              String fileName) {

        ByteArrayResource resource = new ByteArrayResource(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename(fileName).build());
        headers.add("Access-Control-Expose-Headers", "Content-Disposition,Content-Length");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.valueOf(mediaType))
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
