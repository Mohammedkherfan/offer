package com.mydigieyes.carzzzat.service.impl;

import com.mydigieyes.carzzzat.bo.OfferBo;
import com.mydigieyes.carzzzat.enums.ResponseCode;
import com.mydigieyes.carzzzat.exception.OfferException;
import com.mydigieyes.carzzzat.offer.request.UpdateOfferRequest;
import com.mydigieyes.carzzzat.offer.response.UpdateOfferResponse;
import com.mydigieyes.carzzzat.repository.OfferRepository;
import com.mydigieyes.carzzzat.service.UpdateOfferService;

import java.time.LocalDateTime;

public class UpdateOfferServiceImpl implements UpdateOfferService {

    private OfferRepository offerRepository;

    public UpdateOfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public UpdateOfferResponse update(UpdateOfferRequest request) {
        validateRequest(request);
        OfferBo offerBo = offerRepository.getByExternalId(request.getExternalId());

        offerBo.setDescription(request.getDescription());
        offerBo.setDiscountAmount(request.getDiscountAmount());
        offerBo.setDiscountPercentage(request.getDiscountPercentage());
        offerBo.setEndDate(LocalDateTime.parse(request.getEndDate()));
        offerBo.setStartDate(LocalDateTime.parse(request.getStartDate()));
        offerBo.setExternalId(request.getExternalId());
        offerBo.setImage(request.getImage());
        offerBo.setStatus(request.getStatus());

        offerRepository.update(offerBo);

        return UpdateOfferResponse.builder()
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
                .responseCode(ResponseCode.SUCCESS)
                .responseMessage(ResponseCode.SUCCESS.getMessage())
                .build();
    }

    private void validateRequest(UpdateOfferRequest request) {
        Boolean isOfferExist = offerRepository.isOfferExist(request.getExternalId());
        if (!isOfferExist)
            throw new OfferException(ResponseCode.OFFER_NOT_FOUND, String.format("Offer not found %1s", request.getExternalId()));
        if (LocalDateTime.parse(request.getStartDate()).isBefore(LocalDateTime.now()))
            throw new OfferException(ResponseCode.INVALID_DATE, String.format("Invalid start date %1s", request.getStartDate()));
        if (LocalDateTime.parse(request.getEndDate()).isBefore(LocalDateTime.now()))
            throw new OfferException(ResponseCode.INVALID_DATE, String.format("Invalid end date %1s", request.getEndDate()));
        if (LocalDateTime.parse(request.getEndDate()).isBefore(LocalDateTime.parse(request.getStartDate())))
            throw new OfferException(ResponseCode.INVALID_DATE, String.format("Invalid date, start date: %1s end date: %2s", request.getStartDate(), request.getEndDate()));
    }
}
