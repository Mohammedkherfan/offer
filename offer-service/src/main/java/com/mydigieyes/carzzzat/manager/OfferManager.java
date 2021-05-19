package com.mydigieyes.carzzzat.manager;

import com.mydigieyes.carzzzat.general.GeneralResponse;
import com.mydigieyes.carzzzat.offer.request.CreateOfferRequest;
import com.mydigieyes.carzzzat.offer.request.UpdateOfferRequest;
import com.mydigieyes.carzzzat.offer.response.CreateOfferResponse;
import com.mydigieyes.carzzzat.offer.response.GetOfferResponse;
import com.mydigieyes.carzzzat.offer.response.ListOfferResponse;
import com.mydigieyes.carzzzat.offer.response.UpdateOfferResponse;

public interface OfferManager {

    CreateOfferResponse create(CreateOfferRequest request);

    UpdateOfferResponse update(UpdateOfferRequest request);

    GetOfferResponse get(String externalId);

    ListOfferResponse list();

    ListOfferResponse listByMerchant(String merchantExternalId);

    GeneralResponse delete(String externalId);

    ListOfferResponse listByService(String serviceExternalId);
}
