package com.mydigieyes.carzzzat.map;

import com.mydigieyes.carzzzat.bo.OfferBo;
import com.mydigieyes.carzzzat.entity.OfferEntity;

public interface OfferMapping {

    OfferBo toBo(OfferEntity offerEntity);

    OfferEntity toEntity(OfferBo offerBo);
}
