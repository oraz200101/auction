package com.example.auction.exception.domain;

import com.example.auction.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.auction.constants.exception.BidExceptionConstants.USER_CANNOT_BID_ON_THEIR_AD_MESSAGE;
import static com.example.auction.constants.exception.BidExceptionConstants.USER_CANNOT_ON_THEIR_AD_CODE;

public class UserCannotBidOnTheirAdException extends CustomException {
    public UserCannotBidOnTheirAdException() {
        super( USER_CANNOT_BID_ON_THEIR_AD_MESSAGE,USER_CANNOT_ON_THEIR_AD_CODE, HttpStatus.BAD_REQUEST);
    }
}
