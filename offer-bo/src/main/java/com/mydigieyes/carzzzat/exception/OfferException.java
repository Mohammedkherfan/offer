package com.mydigieyes.carzzzat.exception;

import com.mydigieyes.carzzzat.enums.ResponseCode;
import lombok.Getter;

@Getter
public class OfferException extends GeneralException {

    private ResponseCode responseCode;

    public OfferException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
