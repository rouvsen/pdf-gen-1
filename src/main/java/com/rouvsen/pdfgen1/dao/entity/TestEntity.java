package com.rouvsen.pdfgen1.dao.entity;

import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "TEST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "test_seq_gen")
    @SequenceGenerator(
            name = "test_seq_gen",
            sequenceName = "TEST_ID_SEQ",
            allocationSize = 50
    )
    @Column(name = "ID", nullable = false)
    Long id;

    @Column(name = "CUSTOMER_ID", nullable = false, unique = true, length = 16)
    String customerId;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    String password;
}