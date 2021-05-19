package com.mydigieyes.carzzzat.service.impl;

import com.mydigieyes.carzzzat.bo.OfferBo;
import com.mydigieyes.carzzzat.enums.ResponseCode;
import com.mydigieyes.carzzzat.enums.Status;
import com.mydigieyes.carzzzat.exception.OfferException;
import com.mydigieyes.carzzzat.offer.request.CreateOfferRequest;
import com.mydigieyes.carzzzat.offer.response.CreateOfferResponse;
import com.mydigieyes.carzzzat.repository.OfferRepository;
import com.mydigieyes.carzzzat.service.CreateOfferService;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateOfferServiceImpl implements CreateOfferService {

    private OfferRepository offerRepository;

    public CreateOfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public CreateOfferResponse create(CreateOfferRequest request) {
        validateRequest(request);
        OfferBo offerBo = offerRepository.create(OfferBo.builder()
                .externalId(UUID.randomUUID().toString())
                .merchantExternalId(request.getMerchantExternalId())
                .serviceExternalId(request.getMerchantExternalId())
                .status(Status.ACTIVE)
                .description(request.getDescription())
                .discountAmount(request.getDiscountAmount())
                .discountPercentage(request.getDiscountPercentage())
                .startDate(LocalDateTime.parse(request.getStartDate()))
                .endDate(LocalDateTime.parse(request.getEndDate()))
                .image(request.getImage())
                .build());
        return CreateOfferResponse.builder()
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

    private void validateRequest(CreateOfferRequest request) {
        if (LocalDateTime.parse(request.getStartDate()).isBefore(LocalDateTime.now()))
            throw new OfferException(ResponseCode.INVALID_DATE, String.format("Invalid start date %1s", request.getStartDate()));
        if (LocalDateTime.parse(request.getEndDate()).isBefore(LocalDateTime.now()))
            throw new OfferException(ResponseCode.INVALID_DATE, String.format("Invalid end date %1s", request.getEndDate()));
        if (LocalDateTime.parse(request.getEndDate()).isBefore(LocalDateTime.parse(request.getStartDate())))
            throw new OfferException(ResponseCode.INVALID_DATE, String.format("Invalid date, start date: %1s end date: %2s", request.getStartDate(), request.getEndDate()));
    }
}
