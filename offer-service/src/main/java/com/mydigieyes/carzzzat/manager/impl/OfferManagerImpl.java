package com.mydigieyes.carzzzat.manager.impl;

import com.mydigieyes.carzzzat.general.GeneralResponse;
import com.mydigieyes.carzzzat.manager.OfferManager;
import com.mydigieyes.carzzzat.offer.request.CreateOfferRequest;
import com.mydigieyes.carzzzat.offer.request.UpdateOfferRequest;
import com.mydigieyes.carzzzat.offer.response.CreateOfferResponse;
import com.mydigieyes.carzzzat.offer.response.GetOfferResponse;
import com.mydigieyes.carzzzat.offer.response.ListOfferResponse;
import com.mydigieyes.carzzzat.offer.response.UpdateOfferResponse;
import com.mydigieyes.carzzzat.service.*;

public class OfferManagerImpl implements OfferManager {

    private CreateOfferService createOfferService;
    private UpdateOfferService updateOfferService;
    private GetOfferService getOfferService;
    private ListOffersService listOffersService;
    private DeleteOfferService deleteOfferService;

    public OfferManagerImpl(CreateOfferService createOfferService,
                            UpdateOfferService updateOfferService,
                            GetOfferService getOfferService,
                            ListOffersService listOffersService,
                            DeleteOfferService deleteOfferService) {
        this.createOfferService = createOfferService;
        this.updateOfferService = updateOfferService;
        this.getOfferService = getOfferService;
        this.listOffersService = listOffersService;
        this.deleteOfferService = deleteOfferService;
    }

    @Override
    public CreateOfferResponse create(CreateOfferRequest request) {
        CreateOfferResponse createOfferResponse = createOfferService.create(request);
        return createOfferResponse;
    }

    @Override
    public UpdateOfferResponse update(UpdateOfferRequest request) {
        UpdateOfferResponse updateOfferResponse = updateOfferService.update(request);
        return updateOfferResponse;
    }

    @Override
    public GetOfferResponse get(String externalId) {
        GetOfferResponse getOfferResponse = getOfferService.get(externalId);
        return getOfferResponse;
    }

    @Override
    public ListOfferResponse list() {
        ListOfferResponse listOfferResponse = listOffersService.list();
        return listOfferResponse;
    }

    @Override
    public ListOfferResponse listByMerchant(String merchantExternalId) {
        ListOfferResponse listOfferResponse = listOffersService.listByMerchant(merchantExternalId);
        return listOfferResponse;
    }

    @Override
    public ListOfferResponse listByService(String serviceExternalId) {
        ListOfferResponse listOfferResponse = listOffersService.listByService(serviceExternalId);
        return listOfferResponse;
    }

    @Override
    public GeneralResponse delete(String externalId) {
        GeneralResponse generalResponse = deleteOfferService.delete(externalId);
        return generalResponse;
    }
}
