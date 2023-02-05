package com.example.auction.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static com.example.auction.constants.swagger.AnnouncementSwaggerConstants.*;

@Getter
@Setter
@Schema(title =  BID_CREATE_DTO_TITLE)
public class BidCreateDto {
    @Schema(description = BID_PRICE_DESCRIPTION,example = BID_PRICE_EXAMPLE)
    private BigDecimal price;
}
