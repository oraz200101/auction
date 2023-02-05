package com.example.auction.exception.domain;

import com.example.auction.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.auction.constants.exception.AnnouncementExceptionConstants.ANNOUNCEMENT_NOT_FOUND_BY_ID_CODE;
import static com.example.auction.constants.exception.AnnouncementExceptionConstants.ANNOUNCEMENT_NOT_FOUND_BY_ID_MESSAGE;

public class AnnouncementNotFoundByIdException extends CustomException {

    public AnnouncementNotFoundByIdException() {
        super(ANNOUNCEMENT_NOT_FOUND_BY_ID_MESSAGE, ANNOUNCEMENT_NOT_FOUND_BY_ID_CODE, HttpStatus.NOT_FOUND);
    }
}
