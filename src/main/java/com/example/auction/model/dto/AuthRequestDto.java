package com.example.auction.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import static com.example.auction.constants.swagger.UserSwaggerConstants.*;
import static com.example.auction.constants.validation.UserValidationConstants.*;

@Getter
@Setter
@Schema(title = LOGIN_DTO_TITLE)
public class AuthRequestDto {

    @Schema(description = USER_EMAIL_DESCRIPTION,example = USER_EMAIL_EXAMPLE)
    @Email(message = USER_EMAIL_FORMAT_MESSAGE)
    @NotBlank(message = USER_EMAIL_REQUIRED_MESSAGE)
    private String email;
    @Schema(description = USER_PASSWORD_DESCRIPTION,example = USER_PASSWORD_EXAMPLE)
    @NotBlank(message = USER_PASSWORD_REQUIRED_MESSAGE)
    private String password;

}
