package com.example.auction.model.dto;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static com.example.auction.constants.swagger.AnnouncementSwaggerConstants.*;
import static com.example.auction.constants.swagger.AnnouncementSwaggerConstants.DESCRIPTION_OF_ANNOUNCEMENT_EXAMPLE;
import static com.example.auction.constants.validation.AnnouncementValidationConstants.*;

@Getter
@Setter
@Schema(title = ANNOUNCEMENT_CREATE_DTO_TITLE)
public class AnnouncementCreateDto {
    @Schema(description = NAME_OF_ANNOUNCEMENT_DESCRIPTION,example = NAME_OF_ANNOUNCEMENT_EXAMPLE)
    @NotBlank(message =ANNOUNCEMENT_NAME_REQUIRED_MESSAGE )
    private String name;

    @Schema(description = DESCRIPTION_OF_ANNOUNCEMENT_DESCRIPTION,example = DESCRIPTION_OF_ANNOUNCEMENT_EXAMPLE)
    @NotBlank(message = ANNOUNCEMENT_DESCRIPTION_REQUIRED_MESSAGE)
    private String description;
   @Min(value = 0 ,message = ANNOUNCEMENT_PRICE_REQUIRED_MESSAGE)
    @Schema(description = PRICE_OF_ANNOUNCEMENT_DESCRIPTION,example = PRICE_OF_ANNOUNCEMENT_EXAMPLE)
    private BigDecimal price;
}
