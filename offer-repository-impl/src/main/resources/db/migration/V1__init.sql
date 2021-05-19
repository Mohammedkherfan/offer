CREATE TABLE OFFERS
  (
     ID                             SERIAL NOT NULL UNIQUE,
     EXTERNAL_ID                    VARCHAR(255) NOT NULL UNIQUE,
     MERCHANT_EXTERNAL_ID           VARCHAR(255) NOT NULL,
     SERVICE_EXTERNAL_ID            VARCHAR(255) NOT NULL,
     DESCRIPTION                    TEXT NOT NULL,
     START_DATE                     TIMESTAMP NOT NULL,
     END_DATE                       TIMESTAMP NOT NULL,
     IMAGE                          BYTEA NOT NULL,
     STATUS                         NUMERIC(1) NOT NULL,
     DISCOUNT_PERCENTAGE            NUMERIC NOT NULL,
     DISCOUNT_AMOUNT                NUMERIC NOT NULL,
     CREATED_DATE                   TIMESTAMP NOT NULL,
     UPDATED_DATE                   TIMESTAMP NULL,
     CREATED_BY                     VARCHAR(255) NOT NULL,
     UPDATED_BY                     VARCHAR(255) NULL,
     PRIMARY KEY (ID)
  ) ;