package com.example.auction.exception.domain;

import com.example.auction.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.auction.constants.exception.BidExceptionConstants.BID_LOWER_THAN_ACTUAL_PRICE_CODE;
import static com.example.auction.constants.exception.BidExceptionConstants.BID_LOWER_THAN_ACTUAL_PRICE_MESSAGE;

public class BidLowerThanActualPriceException extends CustomException {
    public BidLowerThanActualPriceException() {
        super(BID_LOWER_THAN_ACTUAL_PRICE_MESSAGE,BID_LOWER_THAN_ACTUAL_PRICE_CODE, HttpStatus.BAD_REQUEST);
    }
}
