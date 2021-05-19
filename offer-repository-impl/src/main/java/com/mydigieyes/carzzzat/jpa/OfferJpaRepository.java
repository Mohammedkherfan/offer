package com.mydigieyes.carzzzat.jpa;

import com.mydigieyes.carzzzat.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferJpaRepository extends JpaRepository<OfferEntity, Long> {

    Boolean existsByExternalId(String externalId);

    OfferEntity findByExternalId(String externalId);

    List<OfferEntity> findAllByMerchantExternalId(String merchantExternalId);

    List<OfferEntity> findAllByServiceExternalId(String serviceExternalId);
}
