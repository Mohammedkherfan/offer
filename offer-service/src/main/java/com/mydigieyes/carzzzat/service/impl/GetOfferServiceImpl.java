package com.mydigieyes.carzzzat.service.impl;

import com.mydigieyes.carzzzat.bo.OfferBo;
import com.mydigieyes.carzzzat.enums.ResponseCode;
import com.mydigieyes.carzzzat.enums.Status;
import com.mydigieyes.carzzzat.exception.OfferException;
import com.mydigieyes.carzzzat.offer.response.GetOfferResponse;
import com.mydigieyes.carzzzat.repository.OfferRepository;
import com.mydigieyes.carzzzat.service.GetOfferService;

public class GetOfferServiceImpl implements GetOfferService {

    private OfferRepository offerRepository;

    public GetOfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public GetOfferResponse get(String externalId) {
        validateRequest(externalId);
        OfferBo offerBo = offerRepository.getByExternalId(externalId);
        return GetOfferResponse.builder()
                .externalId(offerBo.getExternalId())
                .merchantExternalId(offerBo.getMerchantExternalId())
                .serviceExternalId(offerBo.getServiceExternalId())
                .status(offerBo.getStatus())
                .description(offerBo.getDescription())
                .discountAmount(offerBo.getDiscountAmount())
                .discountPercentage(offerBo.getDiscountPercentage())
                .startDate(offerBo.getStartDate().toString())
                .endDate(offerBo.getEndDate().toString())
                .image(offerBo.getImage())
                .responseCode(getResponseCode(offerBo.getStatus()))
                .responseMessage(getResponseCode(offerBo.getStatus()).getMessage())
                .build();
    }

    private ResponseCode getResponseCode(Status status) {
        return Status.ACTIVE.equals(status) ? ResponseCode.SUCCESS : ResponseCode.OFFER_EXPIRED;
    }

    private void validateRequest(String externalId) {
        Boolean isOfferExist = offerRepository.isOfferExist(externalId);
        if (!isOfferExist)
            throw new OfferException(ResponseCode.OFFER_NOT_FOUND, String.format("Offer not found %1s", externalId));
    }
}
