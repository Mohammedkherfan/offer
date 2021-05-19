package com.mydigieyes.carzzzat.entity;

import com.mydigieyes.carzzzat.enums.Status;
import com.mydigieyes.carzzzat.persistence.AuditedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "OFFERS")
@AllArgsConstructor
@NoArgsConstructor
public class OfferEntity extends AuditedEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "EXTERNAL_ID", nullable = false, unique = true)
    private String externalId;

    @Column(name = "MERCHANT_EXTERNAL_ID", nullable = false, unique = false)
    private String merchantExternalId;

    @Column(name = "SERVICE_EXTERNAL_ID", nullable = false, unique = false)
    private String serviceExternalId;

    @Column(name = "DESCRIPTION", nullable = false, unique = false)
    private String description;

    @Column(name = "START_DATE", nullable = false, unique = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false, unique = false)
    private Date endDate;

    @Column(name = "STATUS", nullable = false, unique = false)
    private Status status;

    @Column(name = "IMAGE", nullable = false, unique = false)
    private byte [] image;

    @Column(name = "DISCOUNT_PERCENTAGE", nullable = false, unique = false)
    private Integer discountPercentage;

    @Column(name = "DISCOUNT_AMOUNT", nullable = false, unique = false)
    private Integer discountAmount;
}
