package com.mydigieyes.carzzzat.configuration;

import com.mydigieyes.carzzzat.manager.OfferManager;
import com.mydigieyes.carzzzat.manager.impl.OfferManagerImpl;
import com.mydigieyes.carzzzat.repository.OfferRepository;
import com.mydigieyes.carzzzat.repository.impl.OfferRepositoryImpl;
import com.mydigieyes.carzzzat.service.*;
import com.mydigieyes.carzzzat.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferConfiguration {

    @Bean
    public OfferRepository offerRepository() {
        return new OfferRepositoryImpl();
    }

    @Bean
    public CreateOfferService createOfferService(OfferRepository offerRepository) {
        return new CreateOfferServiceImpl(offerRepository);
    }

    @Bean
    public UpdateOfferService updateOfferService(OfferRepository offerRepository) {
        return new UpdateOfferServiceImpl(offerRepository);
    }

    @Bean
    public DeleteOfferService deleteOfferService(OfferRepository offerRepository) {
        return new DeleteOfferServiceImpl(offerRepository);
    }

    @Bean
    public GetOfferService getOfferService(OfferRepository offerRepository) {
        return new GetOfferServiceImpl(offerRepository);
    }

    @Bean
    public ListOffersService listOffersService(OfferRepository offerRepository) {
        return new ListOffersServiceImpl(offerRepository);
    }

    @Bean
    public OfferManager offerManager(CreateOfferService createOfferService,
                                     UpdateOfferService updateOfferService,
                                     DeleteOfferService deleteOfferService,
                                     GetOfferService getOfferService,
                                     ListOffersService listOffersService) {
        return new OfferManagerImpl(createOfferService, updateOfferService, getOfferService, listOffersService, deleteOfferService);
    }
}
