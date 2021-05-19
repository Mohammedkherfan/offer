package com.mydigieyes.carzzzat.service;

import com.mydigieyes.carzzzat.offer.request.UpdateOfferRequest;
import com.mydigieyes.carzzzat.offer.response.UpdateOfferResponse;

public interface UpdateOfferService {

    UpdateOfferResponse update(UpdateOfferRequest request);
}
