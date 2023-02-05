package com.example.auction.exception.domain;

import com.example.auction.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.auction.constants.exception.BidExceptionConstants.BID_MAX_NOT_FOUND_CODE;
import static com.example.auction.constants.exception.BidExceptionConstants.BID_MAX_NOT_FOUND_MESSAGE;

public class BidMaxNotFoundException extends CustomException {

    public BidMaxNotFoundException() {
        super(BID_MAX_NOT_FOUND_MESSAGE,BID_MAX_NOT_FOUND_CODE,HttpStatus.NOT_FOUND);
    }
}
