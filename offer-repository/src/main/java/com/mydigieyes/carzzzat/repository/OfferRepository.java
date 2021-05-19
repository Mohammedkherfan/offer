package com.mydigieyes.carzzzat.repository;

import com.mydigieyes.carzzzat.bo.OfferBo;

import java.util.List;

public interface OfferRepository {

    OfferBo create(OfferBo offerBo);

    Boolean isOfferExist(String externalId);

    OfferBo getByExternalId(String externalId);

    void update(OfferBo offerBo);

    List<OfferBo> list();

    List<OfferBo> listByMerchant(String merchantExternalId);

    List<OfferBo> listByService(String serviceExternalId);

    void delete(String externalId);
}
