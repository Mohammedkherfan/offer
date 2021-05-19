package com.mydigieyes.carzzzat.service;

import com.mydigieyes.carzzzat.offer.response.ListOfferResponse;

public interface ListOffersService {

    ListOfferResponse list();

    ListOfferResponse listByMerchant(String merchantExternalId);

    ListOfferResponse listByService(String serviceExternalId);
}
