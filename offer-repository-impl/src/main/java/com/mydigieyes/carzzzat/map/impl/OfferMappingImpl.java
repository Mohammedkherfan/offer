package com.mydigieyes.carzzzat.map.impl;

import com.mydigieyes.carzzzat.bo.OfferBo;
import com.mydigieyes.carzzzat.entity.OfferEntity;
import com.mydigieyes.carzzzat.map.OfferMapping;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class OfferMappingImpl implements OfferMapping {

    @Override
    public OfferBo toBo(OfferEntity offerEntity) {
        return OfferBo.builder()
                .id(offerEntity.getId())
                .status(offerEntity.getStatus())
                .image(offerEntity.getImage())
                .discountPercentage(offerEntity.getDiscountPercentage())
                .discountAmount(offerEntity.getDiscountAmount())
                .description(offerEntity.getDescription())
                .merchantExternalId(offerEntity.getMerchantExternalId())
                .serviceExternalId(offerEntity.getServiceExternalId())
                .externalId(offerEntity.getExternalId())
                .startDate(offerEntity.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .endDate(offerEntity.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .build();
    }

    @Override
    public OfferEntity toEntity(OfferBo offerBo) {
        return OfferEntity.builder()
                .id(offerBo.getId())
                .status(offerBo.getStatus())
                .image(offerBo.getImage())
                .discountPercentage(offerBo.getDiscountPercentage())
                .discountAmount(offerBo.getDiscountAmount())
                .description(offerBo.getDescription())
                .merchantExternalId(offerBo.getMerchantExternalId())
                .serviceExternalId(offerBo.getServiceExternalId())
                .externalId(offerBo.getExternalId())
                .startDate(Date.from(offerBo.getStartDate().atZone(ZoneId.systemDefault()).toInstant()))
                .endDate(Date.from(offerBo.getStartDate().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
    }
}
