package com.mydigieyes.carzzzat.service.impl;

import com.mydigieyes.carzzzat.enums.ResponseCode;
import com.mydigieyes.carzzzat.exception.OfferException;
import com.mydigieyes.carzzzat.general.GeneralResponse;
import com.mydigieyes.carzzzat.repository.OfferRepository;
import com.mydigieyes.carzzzat.service.DeleteOfferService;

public class DeleteOfferServiceImpl implements DeleteOfferService {

    private OfferRepository offerRepository;

    public DeleteOfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public GeneralResponse delete(String externalId) {
        validateRequest(externalId);
        offerRepository.delete(externalId);
        GeneralResponse generalResponse = new GeneralResponse(ResponseCode.SUCCESS, ResponseCode.SUCCESS.getMessage());
        return generalResponse;
    }

    private void validateRequest(String externalId) {
        Boolean isOfferExist = offerRepository.isOfferExist(externalId);
        if (!isOfferExist)
            throw new OfferException(ResponseCode.OFFER_NOT_FOUND, String.format("Offer not found %1s", externalId));
    }
}
