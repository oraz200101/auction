package com.example.auction.exception.domain;

import com.example.auction.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.auction.constants.exception.UserExceptionConstants.*;

public class UserNotFoundByEmailException extends CustomException {
    public UserNotFoundByEmailException() {
        super(USER_NOT_FOUND_BY_EMAIL_MESSAGE,USER_NOT_FOUND_BY_EMAIL_CODE,HttpStatus.NOT_FOUND);
    }
}
