package com.example.auction.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import static com.example.auction.constants.swagger.UserSwaggerConstants.*;

@Getter
@Setter
@Schema(title = AUTH_RESPONSE_DTO_TITTLE)
public class AuthResponseDto {
    @Schema(description = ACCESS_TOKEN_DESCRIPTION,example = ACCESS_TOKEN_EXAMPLE)
    private String accessToken;
    @Schema(description = TOKEN_TYPE_DESCRIPTION,example = TOKEN_TYPE_EXAMPLE)
    private String tokenType="Bearer";

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
