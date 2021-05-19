package com.mydigieyes.carzzzat.api.impl;

import com.mydigieyes.carzzzat.api.OfferController;
import com.mydigieyes.carzzzat.general.GeneralResponse;
import com.mydigieyes.carzzzat.manager.OfferManager;
import com.mydigieyes.carzzzat.monitor.Monitored;
import com.mydigieyes.carzzzat.offer.request.CreateOfferRequest;
import com.mydigieyes.carzzzat.offer.request.UpdateOfferRequest;
import com.mydigieyes.carzzzat.offer.response.CreateOfferResponse;
import com.mydigieyes.carzzzat.offer.response.GetOfferResponse;
import com.mydigieyes.carzzzat.offer.response.ListOfferResponse;
import com.mydigieyes.carzzzat.offer.response.UpdateOfferResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Api(value = "Offer Api")
public class OfferControllerImpl implements OfferController {

    private OfferManager offerManager;

    public OfferControllerImpl(OfferManager offerManager) {
        this.offerManager = offerManager;
    }

    @Monitored
    @Override
    public CreateOfferResponse create(@RequestBody @Valid CreateOfferRequest request) {
        return offerManager.create(request);
    }

    @Monitored
    @Override
    public UpdateOfferResponse update(@RequestBody @Valid UpdateOfferRequest request) {
        return offerManager.update(request);
    }

    @Monitored
    @Override
    public GetOfferResponse get(@PathVariable @Valid @NotBlank(message = "Invalid external id") String externalId) {
        return offerManager.get(externalId);
    }

    @Monitored
    @Override
    public ListOfferResponse list() {
        return offerManager.list();
    }

    @Monitored
    @Override
    public ListOfferResponse listByMerchant(@PathVariable @Valid @NotBlank(message = "Invalid merchant external id") String merchantExternalId) {
        return offerManager.listByMerchant(merchantExternalId);
    }

    @Monitored
    @Override
    public GeneralResponse delete(@PathVariable @Valid @NotBlank(message = "Invalid external id") String externalId) {
        return offerManager.delete(externalId);
    }

    @Monitored
    @Override
    public ListOfferResponse listByService(@PathVariable @Valid @NotBlank(message = "Invalid service external id") String serviceExternalId) {
        return offerManager.listByService(serviceExternalId);
    }
}
