package com.mydigieyes.carzzzat.service;

import com.mydigieyes.carzzzat.offer.request.CreateOfferRequest;
import com.mydigieyes.carzzzat.offer.response.CreateOfferResponse;

public interface CreateOfferService {

    CreateOfferResponse create(CreateOfferRequest request);
}
