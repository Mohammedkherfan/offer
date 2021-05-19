package com.mydigieyes.carzzzat.repository.impl;

import com.mydigieyes.carzzzat.bo.OfferBo;
import com.mydigieyes.carzzzat.entity.OfferEntity;
import com.mydigieyes.carzzzat.jpa.OfferJpaRepository;
import com.mydigieyes.carzzzat.map.OfferMapping;
import com.mydigieyes.carzzzat.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OfferRepositoryImpl implements OfferRepository {

    @Autowired
    private OfferJpaRepository offerJpaRepository;
    @Autowired
    private OfferMapping offerMapping;

    @Override
    public OfferBo create(OfferBo offerBo) {
        OfferEntity offerEntity = offerJpaRepository.save(offerMapping.toEntity(offerBo));
        return offerMapping.toBo(offerEntity);
    }

    @Override
    public Boolean isOfferExist(String externalId) {
        Boolean isOfferExist = offerJpaRepository.existsByExternalId(externalId);
        return isOfferExist;
    }

    @Override
    public OfferBo getByExternalId(String externalId) {
        OfferEntity offerEntity = offerJpaRepository.findByExternalId(externalId);
        return offerMapping.toBo(offerEntity);
    }

    @Override
    public void update(OfferBo offerBo) {
        offerJpaRepository.save(offerMapping.toEntity(offerBo));
    }

    @Override
    public List<OfferBo> list() {
        List<OfferEntity> list = offerJpaRepository.findAll();
        return list.stream().map(e -> offerMapping.toBo(e)).collect(Collectors.toList());
    }

    @Override
    public List<OfferBo> listByMerchant(String merchantExternalId) {
        List<OfferEntity> list = offerJpaRepository.findAllByMerchantExternalId(merchantExternalId);
        return list.stream().map(e -> offerMapping.toBo(e)).collect(Collectors.toList());
    }

    @Override
    public List<OfferBo> listByService(String serviceExternalId) {
        List<OfferEntity> list = offerJpaRepository.findAllByServiceExternalId(serviceExternalId);
        return list.stream().map(e -> offerMapping.toBo(e)).collect(Collectors.toList());
    }

    @Override
    public void delete(String externalId) {
        Long id = offerJpaRepository.findByExternalId(externalId).getId();
        offerJpaRepository.deleteById(id);
    }
}
