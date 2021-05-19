package com.mydigieyes.carzzzat.api;

import com.mydigieyes.carzzzat.general.GeneralResponse;
import com.mydigieyes.carzzzat.offer.request.CreateOfferRequest;
import com.mydigieyes.carzzzat.offer.request.UpdateOfferRequest;
import com.mydigieyes.carzzzat.offer.response.CreateOfferResponse;
import com.mydigieyes.carzzzat.offer.response.GetOfferResponse;
import com.mydigieyes.carzzzat.offer.response.ListOfferResponse;
import com.mydigieyes.carzzzat.offer.response.UpdateOfferResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/offer")
public interface OfferController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Method to create offer", notes = "This method used when want to create offer")
    @ApiParam(value = "Parameter of operation", name = "createOfferRequest")
    CreateOfferResponse create(CreateOfferRequest request);

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Method to create offer", notes = "This method used when want to create offer")
    @ApiParam(value = "Parameter of operation", name = "updateOfferRequest")
    UpdateOfferResponse update(UpdateOfferRequest request);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{externalId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Method to get offer", notes = "This method used when want to get offer")
    @ApiParam(value = "Parameter of operation", name = "externalId")
    GetOfferResponse get(String externalId);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Method to get offers", notes = "This method used when want to get offers")
    ListOfferResponse list();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/merchant/{merchantExternalId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Method to get offers by merchant", notes = "This method used when want to get offers by merchant")
    @ApiParam(value = "Parameter of operation", name = "merchantExternalId")
    ListOfferResponse listByMerchant(String merchantExternalId);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/service/{serviceExternalId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Method to get offers by service", notes = "This method used when want to get offers by service")
    @ApiParam(value = "Parameter of operation", name = "serviceExternalId")
    ListOfferResponse listByService(String serviceExternalId);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{externalId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Method to delete offer", notes = "This method used when want to delete offer")
    @ApiParam(value = "Parameter of operation", name = "externalId")
    GeneralResponse delete(String externalId);
}
