package com.mydigieyes.carzzzat.service.impl;

import com.mydigieyes.carzzzat.bo.OfferBo;
import com.mydigieyes.carzzzat.enums.ResponseCode;
import com.mydigieyes.carzzzat.offer.dto.OfferDto;
import com.mydigieyes.carzzzat.offer.response.ListOfferResponse;
import com.mydigieyes.carzzzat.repository.OfferRepository;
import com.mydigieyes.carzzzat.service.ListOffersService;

import java.util.List;
import java.util.stream.Collectors;

public class ListOffersServiceImpl implements ListOffersService {
    
    private OfferRepository offerRepository;

    public ListOffersServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public ListOfferResponse list() {
        List<OfferBo> list = offerRepository.list();
        List<OfferDto> offers = list.stream().map(e -> OfferDto.builder()
                .externalId(e.getExternalId())
                .merchantExternalId(e.getMerchantExternalId())
                .serviceExternalId(e.getServiceExternalId())
                .status(e.getStatus())
                .description(e.getDescription())
                .discountAmount(e.getDiscountAmount())
                .discountPercentage(e.getDiscountPercentage())
                .startDate(e.getStartDate().toString())
                .endDate(e.getEndDate().toString())
                .image(e.getImage())
                .build())
                .collect(Collectors.toList());
        return ListOfferResponse.builder()
                .offers(offers)
                .responseCode(ResponseCode.SUCCESS)
                .responseMessage(ResponseCode.SUCCESS.getMessage())
                .build();
    }

    @Override
    public ListOfferResponse listByMerchant(String merchantExternalId) {
        List<OfferBo> list = offerRepository.listByMerchant(merchantExternalId);
        List<OfferDto> offers = list.stream().map(e -> OfferDto.builder()
                .externalId(e.getExternalId())
                .merchantExternalId(e.getMerchantExternalId())
                .serviceExternalId(e.getServiceExternalId())
                .status(e.getStatus())
                .description(e.getDescription())
                .discountAmount(e.getDiscountAmount())
                .discountPercentage(e.getDiscountPercentage())
                .startDate(e.getStartDate().toString())
                .endDate(e.getEndDate().toString())
                .image(e.getImage())
                .build())
                .collect(Collectors.toList());
        return ListOfferResponse.builder()
                .offers(offers)
                .responseCode(ResponseCode.SUCCESS)
                .responseMessage(ResponseCode.SUCCESS.getMessage())
                .build();
    }

    @Override
    public ListOfferResponse listByService(String serviceExternalId) {
        List<OfferBo> list = offerRepository.listByService(serviceExternalId);
        List<OfferDto> offers = list.stream().map(e -> OfferDto.builder()
                .externalId(e.getExternalId())
                .merchantExternalId(e.getMerchantExternalId())
                .serviceExternalId(e.getServiceExternalId())
                .status(e.getStatus())
                .description(e.getDescription())
                .discountAmount(e.getDiscountAmount())
                .discountPercentage(e.getDiscountPercentage())
                .startDate(e.getStartDate().toString())
                .endDate(e.getEndDate().toString())
                .image(e.getImage())
                .build())
                .collect(Collectors.toList());
        return ListOfferResponse.builder()
                .offers(offers)
                .responseCode(ResponseCode.SUCCESS)
                .responseMessage(ResponseCode.SUCCESS.getMessage())
                .build();
    }
}
