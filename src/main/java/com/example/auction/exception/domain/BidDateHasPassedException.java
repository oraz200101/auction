package com.example.auction.exception.domain;

import com.example.auction.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.auction.constants.exception.BidExceptionConstants.BID_DATE_HAS_PASSED_CODE;
import static com.example.auction.constants.exception.BidExceptionConstants.BID_DATE_HAS_PASSED_MESSAGE;

public class BidDateHasPassedException extends CustomException {

    public BidDateHasPassedException() {
        super(BID_DATE_HAS_PASSED_MESSAGE,BID_DATE_HAS_PASSED_CODE,HttpStatus.BAD_REQUEST);
    }
}
