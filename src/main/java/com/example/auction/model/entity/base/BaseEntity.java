package com.example.auction.model.entity.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static com.example.auction.constants.swagger.BaseEntitySwaggerConstants.FIELD_ID_DESCRIPTION;
import static com.example.auction.constants.swagger.BaseEntitySwaggerConstants.FIELD_ID_EXAMPLE;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description =FIELD_ID_DESCRIPTION,example = FIELD_ID_EXAMPLE)
    private Long id;
}
