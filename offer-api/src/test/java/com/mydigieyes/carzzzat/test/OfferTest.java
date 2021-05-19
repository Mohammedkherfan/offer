package com.mydigieyes.carzzzat.test;

import com.mydigieyes.carzzzat.OfferApplication;
import com.mydigieyes.carzzzat.configuration.OfferConfiguration;
import com.mydigieyes.carzzzat.entity.OfferEntity;
import com.mydigieyes.carzzzat.enums.Status;
import com.mydigieyes.carzzzat.jackson.JacksonUtil;
import com.mydigieyes.carzzzat.jpa.OfferJpaRepository;
import com.mydigieyes.carzzzat.offer.dto.OfferDto;
import com.mydigieyes.carzzzat.offer.request.CreateOfferRequest;
import com.mydigieyes.carzzzat.offer.request.UpdateOfferRequest;
import com.mydigieyes.carzzzat.offer.response.CreateOfferResponse;
import com.mydigieyes.carzzzat.offer.response.ListOfferResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OfferApplication.class, OfferConfiguration.class}, properties = {"spring.main.allow-bean-definition-overriding=true"})
@AutoConfigureMockMvc
public class OfferTest {

    @Autowired
    private JacksonUtil jacksonUtil;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private OfferJpaRepository offerJpaRepository;

    private CreateOfferRequest createOfferRequest;
    private UpdateOfferRequest updateOfferRequest;

    @Before
    public void setUp() throws Exception {
        createOfferRequest = CreateOfferRequest.builder()
                .description("description")
                .discountAmount(0)
                .discountPercentage(0)
                .endDate(LocalDateTime.now().plusYears(1).toString())
                .startDate(LocalDateTime.now().plusDays(1).toString())
                .image(new byte[1])
                .merchantExternalId("any external id")
                .serviceExternalId("any external id")
                .build();
        updateOfferRequest = UpdateOfferRequest.builder()
                .description("description")
                .discountAmount(0)
                .discountPercentage(0)
                .endDate(LocalDateTime.now().plusYears(1).toString())
                .startDate(LocalDateTime.now().plusDays(1).toString())
                .image(new byte[1])
                .externalId("any external id")
                .status(Status.INACTIVE)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        offerJpaRepository.deleteAll();
    }

    @Test
    public void whenCreateOffer_AndDescriptionIsNull_ThenShouldReturnError()throws Exception {
        createOfferRequest.setDescription(null);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in description : Invalid description")));
    }

    @Test
    public void whenCreateOffer_AndDescriptionIsEmpty_ThenShouldReturnError()throws Exception {
        createOfferRequest.setDescription("");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in description : Invalid description")));
    }

    @Test
    public void whenCreateOffer_AndDescriptionIsSpace_ThenShouldReturnError()throws Exception {
        createOfferRequest.setDescription("   ");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in description : Invalid description")));
    }

    @Test
    public void whenCreateOffer_AndDiscountAmountIsNull_ThenShouldReturnError()throws Exception {
        createOfferRequest.setDiscountAmount(null);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in discountAmount : Invalid discount amount")));
    }

    @Test
    public void whenCreateOffer_AndDiscountPercentageIsNull_ThenShouldReturnError()throws Exception {
        createOfferRequest.setDiscountPercentage(null);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in discountPercentage : Invalid discount percentage")));
    }

    @Test
    public void whenCreateOffer_AndEndDateIsNull_ThenShouldReturnError()throws Exception {
        createOfferRequest.setEndDate(null);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in endDate : Invalid end date")));
    }

    @Test
    public void whenCreateOffer_AndEndDateIsEmpty_ThenShouldReturnError()throws Exception {
        createOfferRequest.setEndDate("");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in endDate : Invalid end date")));
    }

    @Test
    public void whenCreateOffer_AndEndDateIsSpace_ThenShouldReturnError()throws Exception {
        createOfferRequest.setEndDate("   ");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in endDate : Invalid end date")));
    }

    @Test
    public void whenCreateOffer_AndStartDateIsNull_ThenShouldReturnError()throws Exception {
        createOfferRequest.setStartDate(null);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in startDate : Invalid start date")));
    }

    @Test
    public void whenCreateOffer_AndStartDateIsEmpty_ThenShouldReturnError()throws Exception {
        createOfferRequest.setStartDate("");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in startDate : Invalid start date")));
    }

    @Test
    public void whenCreateOffer_AndStartDateIsSpace_ThenShouldReturnError()throws Exception {
        createOfferRequest.setStartDate("   ");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in startDate : Invalid start date")));
    }

    @Test
    public void whenCreateOffer_AndMerchantExternalIdIsNull_ThenShouldReturnError()throws Exception {
        createOfferRequest.setMerchantExternalId(null);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in merchantExternalId : Invalid merchant external id")));
    }

    @Test
    public void whenCreateOffer_AndMerchantExternalIdIsEmpty_ThenShouldReturnError()throws Exception {
        createOfferRequest.setMerchantExternalId("");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in merchantExternalId : Invalid merchant external id")));
    }

    @Test
    public void whenCreateOffer_AndMerchantExternalIdIsSpace_ThenShouldReturnError()throws Exception {
        createOfferRequest.setMerchantExternalId("   ");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in merchantExternalId : Invalid merchant external id")));
    }

    @Test
    public void whenCreateOffer_AndServiceExternalIdIsNull_ThenShouldReturnError()throws Exception {
        createOfferRequest.setServiceExternalId(null);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in serviceExternalId : Invalid service external id")));
    }

    @Test
    public void whenCreateOffer_AndServiceExternalIdIsEmpty_ThenShouldReturnError()throws Exception {
        createOfferRequest.setServiceExternalId("");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in serviceExternalId : Invalid service external id")));
    }

    @Test
    public void whenCreateOffer_AndServiceExternalIdIsSpace_ThenShouldReturnError()throws Exception {
        createOfferRequest.setServiceExternalId("   ");

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in serviceExternalId : Invalid service external id")));
    }

    @Test
    public void whenCreateOffer_AndStartDateBeforeCurrentDate_ThenShouldReturnError()throws Exception {
        String startDate = LocalDateTime.now().minusDays(1).toString();
        createOfferRequest.setStartDate(startDate);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("INVALID_DATE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Invalid start date " + startDate)));
    }

    @Test
    public void whenCreateOffer_AndEndDateBeforeCurrentDate_ThenShouldReturnError()throws Exception {
        String endDate = LocalDateTime.now().minusDays(1).toString();
        createOfferRequest.setEndDate(endDate);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("INVALID_DATE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Invalid end date " + endDate)));
    }

    @Test
    public void whenCreateOffer_AndEndDateBeforeStartDate_ThenShouldReturnError()throws Exception {
        String endDate = LocalDateTime.now().plusDays(1).toString();
        createOfferRequest.setEndDate(endDate);

        String startDate = LocalDateTime.now().plusDays(3).toString();
        createOfferRequest.setStartDate(startDate);

        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("INVALID_DATE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Invalid date, start date: " + startDate + " end date: " + endDate)));
    }

    @Test
    public void whenCreateOffer_AndRequestIsValid_ThenShouldReturnSuccess()throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }

    @Test
    public void whenUpdateOffer_AndDescriptionIsNull_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setDescription(null);

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in description : Invalid description")));
    }

    @Test
    public void whenUpdateOffer_AndDescriptionIsEmpty_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setDescription("");

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in description : Invalid description")));
    }

    @Test
    public void whenUpdateOffer_AndDescriptionIsSpace_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setDescription("   ");

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in description : Invalid description")));
    }

    @Test
    public void whenUpdateOffer_AndDiscountAmountIsNull_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setDiscountAmount(null);

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in discountAmount : Invalid discount amount")));
    }

    @Test
    public void whenUpdateOffer_AndDiscountPercentageIsNull_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setDiscountPercentage(null);

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in discountPercentage : Invalid discount percentage")));
    }

    @Test
    public void whenUpdateOffer_AndStartDateIsNull_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setStartDate(null);

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in startDate : Invalid start date")));
    }

    @Test
    public void whenUpdateOffer_AndStartDateIsEmpty_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setStartDate("");

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in startDate : Invalid start date")));
    }

    @Test
    public void whenUpdateOffer_AndStartDateIsSpace_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setStartDate("   ");

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in startDate : Invalid start date")));
    }

    @Test
    public void whenUpdateOffer_AndEndDateIsNull_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setEndDate(null);

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in endDate : Invalid end date")));
    }

    @Test
    public void whenUpdateOffer_AndEndDateIsEmpty_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setEndDate("");

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in endDate : Invalid end date")));
    }

    @Test
    public void whenUpdateOffer_AndEndDateIsSpace_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setEndDate("   ");

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in endDate : Invalid end date")));
    }

    @Test
    public void whenUpdateOffer_AndStatusIsNull_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setStatus(null);

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in status : Invalid status")));
    }

    @Test
    public void whenUpdateOffer_AndExternalIdIsNull_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setExternalId(null);

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in externalId : Invalid external id")));
    }

    @Test
    public void whenUpdateOffer_AndExternalIdIsEmpty_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setExternalId("");

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in externalId : Invalid external id")));
    }

    @Test
    public void whenUpdateOffer_AndExternalIdIsSpace_ThenShouldReturnError()throws Exception {
        updateOfferRequest.setExternalId("   ");

        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("TECHNICAL_ERROR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Error in externalId : Invalid external id")));
    }

    @Test
    public void whenUpdateOffer_AndExternalIdIsNotFound_ThenShouldReturnError()throws Exception {
        String request = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action = mvc.perform(put("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("OFFER_NOT_FOUND")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Offer not found any external id")));
    }

    @Test
    public void whenUpdateOffer_AndStartDateBeforeCurrentDate_ThenShouldReturnError()throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));

        String externalId = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), CreateOfferResponse.class).getExternalId();

        updateOfferRequest.setExternalId(externalId);

        String startDate = LocalDateTime.now().minusDays(1).toString();
        updateOfferRequest.setStartDate(startDate);

        String request1 = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action1 = mvc.perform(put("/offer")
                .content(request1)
                .contentType(MediaType.APPLICATION_JSON));

        action1
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("INVALID_DATE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Invalid start date " + startDate)));
    }

    @Test
    public void whenUpdateOffer_AndEndDateBeforeCurrentDate_ThenShouldReturnError()throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));

        String externalId = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), CreateOfferResponse.class).getExternalId();

        updateOfferRequest.setExternalId(externalId);

        String endDate = LocalDateTime.now().minusDays(1).toString();
        updateOfferRequest.setEndDate(endDate);

        String request1 = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action1 = mvc.perform(put("/offer")
                .content(request1)
                .contentType(MediaType.APPLICATION_JSON));

        action1
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("INVALID_DATE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Invalid end date " + endDate)));
    }

    @Test
    public void whenUpdateOffer_AndEndDateBeforeStartDate_ThenShouldReturnError()throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));

        String externalId = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), CreateOfferResponse.class).getExternalId();

        updateOfferRequest.setExternalId(externalId);

        String endDate = LocalDateTime.now().plusDays(1).toString();
        updateOfferRequest.setEndDate(endDate);

        String startDate = LocalDateTime.now().plusDays(3).toString();
        updateOfferRequest.setStartDate(startDate);

        String request1 = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action1 = mvc.perform(put("/offer")
                .content(request1)
                .contentType(MediaType.APPLICATION_JSON));

        action1
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("INVALID_DATE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Invalid date, start date: " + startDate + " end date: " + endDate)));
    }

    @Test
    public void whenUpdateOffer_AndOfferRequestIsValid_ThenShouldReturnSuccess()throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));

        String externalId = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), CreateOfferResponse.class).getExternalId();

        updateOfferRequest.setExternalId(externalId);

        String request1 = jacksonUtil.toJson(updateOfferRequest);

        ResultActions action1 = mvc.perform(put("/offer")
                .content(request1)
                .contentType(MediaType.APPLICATION_JSON));

        action1
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }

    @Test
    public void whenDeleteOffer_AndOfferExternalIdNotFound_ThenShouldReturnError()throws Exception {
        ResultActions action = mvc.perform(delete("/offer/" + "anyExternalId").contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("OFFER_NOT_FOUND")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Offer not found anyExternalId")));
    }

    @Test
    public void whenDeleteOffer_AndOfferExternalIdExist_ThenShouldReturnSuccess()throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));

        String externalId = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), CreateOfferResponse.class).getExternalId();

        ResultActions action1 = mvc.perform(delete("/offer/" + externalId).contentType(MediaType.APPLICATION_JSON));

        action1
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }

    @Test
    public void whenGetOffer_AndOfferExternalIdNotFound_ThenShouldReturnError()throws Exception {
        ResultActions action = mvc.perform(get("/offer/" + "anyExternalId").contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("OFFER_NOT_FOUND")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Offer not found anyExternalId")));
    }

    @Test
    public void whenGetOffer_AndOfferExist_ThenShouldReturnSuccess()throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));

        String externalId = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), CreateOfferResponse.class).getExternalId();

        ResultActions action1 = mvc.perform(get("/offer/" + externalId).contentType(MediaType.APPLICATION_JSON));

        action1
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }

    @Test
    public void whenGetOffer_AndOfferExist_ButOfferExpired_ThenShouldReturnExpired()throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));

        String externalId = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), CreateOfferResponse.class).getExternalId();

        OfferEntity offerEntity = offerJpaRepository.findByExternalId(externalId);
        offerEntity.setStatus(Status.EXPIRED);
        offerJpaRepository.save(offerEntity);

        ResultActions action1 = mvc.perform(get("/offer/" + externalId).contentType(MediaType.APPLICATION_JSON));

        action1
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("OFFER_EXPIRED")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Offer expired")));
    }

    @Test
    public void whenListOffersByMerchant_AndMerchantExternalIdNotExist_thenShouldReturnEmptyList() throws Exception {
        ResultActions action = mvc.perform(get("/offer/merchant/" + "anyExternalId").contentType(MediaType.APPLICATION_JSON));

        List<OfferDto> offers = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), ListOfferResponse.class).getOffers();
        Assert.assertTrue(offers.isEmpty());
        action
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }

    @Test
    public void whenListOffersByMerchant_AndMerchantExternalIdExist_thenShouldReturnListOfStores() throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));

        String merchantExternalId = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), CreateOfferResponse.class).getMerchantExternalId();

        ResultActions action1 = mvc.perform(get("/offer/merchant/" + merchantExternalId).contentType(MediaType.APPLICATION_JSON));

        List<OfferDto> offers = jacksonUtil.fromJson(action1.andReturn().getResponse().getContentAsString(), ListOfferResponse.class).getOffers();
        Assert.assertTrue(!offers.isEmpty());
        action1
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }

    @Test
    public void whenListOffersByService_AndServiceExternalIdNotExist_thenShouldReturnEmptyList() throws Exception {
        ResultActions action = mvc.perform(get("/offer/service/" + "anyExternalId").contentType(MediaType.APPLICATION_JSON));

        List<OfferDto> offers = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), ListOfferResponse.class).getOffers();
        Assert.assertTrue(offers.isEmpty());
        action
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }

    @Test
    public void whenListOffersByService_AndServiceExternalIdExist_thenShouldReturnListOfStores() throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));

        String serviceExternalId = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), CreateOfferResponse.class).getServiceExternalId();

        ResultActions action1 = mvc.perform(get("/offer/service/" + serviceExternalId).contentType(MediaType.APPLICATION_JSON));

        List<OfferDto> offers = jacksonUtil.fromJson(action1.andReturn().getResponse().getContentAsString(), ListOfferResponse.class).getOffers();
        Assert.assertTrue(!offers.isEmpty());
        action1
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }

    @Test
    public void whenListOffers_AndNoOffersInDatabase_thenShouldReturnEmptyList() throws Exception {
        ResultActions action = mvc.perform(get("/offer").contentType(MediaType.APPLICATION_JSON));

        List<OfferDto> offers = jacksonUtil.fromJson(action.andReturn().getResponse().getContentAsString(), ListOfferResponse.class).getOffers();
        Assert.assertTrue(offers.isEmpty());
        action
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }

    @Test
    public void whenListOffers_AndThereIsOfferInDatabase_thenShouldReturnListOfStores() throws Exception {
        String request = jacksonUtil.toJson(createOfferRequest);

        ResultActions action = mvc.perform(post("/offer")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON));

        action
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));


        ResultActions action1 = mvc.perform(get("/offer").contentType(MediaType.APPLICATION_JSON));

        List<OfferDto> offers = jacksonUtil.fromJson(action1.andReturn().getResponse().getContentAsString(), ListOfferResponse.class).getOffers();
        Assert.assertTrue(!offers.isEmpty());
        action1
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode", is("SUCCESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage", is("Success")));
    }
}
