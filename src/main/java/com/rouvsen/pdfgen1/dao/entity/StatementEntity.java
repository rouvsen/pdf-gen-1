package com.rouvsen.pdfgen1.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "STATEMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class StatementEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "statement_seq_gen")
    @SequenceGenerator(
            name = "statement_seq_gen",
            sequenceName = "STATEMENT_ID_SEQ",
            allocationSize = 10
    )
    @Column(name = "ID", nullable = false)
    Long id;

    @Column(name = "CUSTOMER_ID", nullable = false, length = 16)
    String customerId;

    @Column(name = "ST_DATE", nullable = false)
    LocalDate date;

    @Column(name = "DESCRIPTION", nullable = false, length = 500)
    String description;
}