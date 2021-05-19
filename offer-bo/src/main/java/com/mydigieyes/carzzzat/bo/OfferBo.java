package com.mydigieyes.carzzzat.bo;

import com.mydigieyes.carzzzat.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferBo {

    private Long id;
    private String externalId;
    private String merchantExternalId;
    private String serviceExternalId;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private byte [] image;
    private Integer discountPercentage;
    private Integer discountAmount;
}
