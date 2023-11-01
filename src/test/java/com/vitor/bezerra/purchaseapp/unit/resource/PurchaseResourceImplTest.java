package com.vitor.bezerra.purchaseapp.unit.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vitor.bezerra.purchaseapp.domain.gateway.FiscalDataApiGateway;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCase;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCaseImpl;
import com.vitor.bezerra.purchaseapp.domain.usecase.RetrievePurchaseUseCase;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.repository.PurchaseRepository;
import com.vitor.bezerra.purchaseapp.infrastructure.configuration.CacheConfig;
import com.vitor.bezerra.purchaseapp.infrastructure.configuration.ObjectMapperConfig;
import com.vitor.bezerra.purchaseapp.infrastructure.configuration.PurchaseConfig;
import com.vitor.bezerra.purchaseapp.infrastructure.handler.PurchaseExceptionHandler;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.PurchaseMapper;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.PurchaseResourceImpl;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.CreatePurchaseRequest;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.RetrievePurchaseResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.vitor.bezerra.purchaseapp.unit.utils.Constants.INVALID_DESCRIPTION;
import static com.vitor.bezerra.purchaseapp.unit.utils.Constants.INVALID_NEGATIVE_AMOUNT;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSavedPurchaseModel;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PurchaseResourceImpl.class, CreatePurchaseUseCaseImpl.class, RetrievePurchaseUseCase.class, ObjectMapperConfig.class})
@AutoConfigureMockMvc
@EnableAutoConfiguration(
        exclude = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class
        }
)
@ContextConfiguration(classes = {PurchaseExceptionHandler.class, CreatePurchaseUseCase.class, RetrievePurchaseUseCase.class, ObjectMapperConfig.class, PurchaseConfig.class, CacheConfig.class, FiscalDataApiGateway.class})
public class PurchaseResourceImplTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePurchaseUseCase createPurchaseUseCase;

    @MockBean
    private RetrievePurchaseUseCase retrievePurchaseUseCase;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PurchaseRepository purchaseRepository;

    @MockBean
    private PurchaseMapper purchaseMapper;

    @MockBean
    private FiscalDataApiGateway fiscalDataApiGateway;

    @Test
    @DisplayName("Should create a purchase with success")
    public void shouldCreatePurchaseSuccess() throws Exception {
        final var savedPurchaseModel = createSavedPurchaseModel();

        when(createPurchaseUseCase.createPurchase(any())).thenReturn(savedPurchaseModel);

        mockMvc.perform(
                post("/v1/purchase")
                        .contentType(APPLICATION_JSON)
                        .content(getCreatePurchaseModelString("Description", new BigDecimal("10.33")))
        ).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.description").value("A beautiful description"))
        .andExpect(jsonPath("$.amount").value(10.33))
        .andExpect(jsonPath("$.createdAt").value(LocalDate.now().toString()));
    }

    @Test
    @DisplayName("Should not create a purchase when amount is invalid")
    public void shouldNotCreatePurchaseAmountInvalid() throws Exception {
        mockMvc.perform(
                        post("/v1/purchase")
                                .contentType(APPLICATION_JSON)
                                .content(getCreatePurchaseModelString("Description", INVALID_NEGATIVE_AMOUNT))
                ).andDo(print())
                .andExpect(status().isBadRequest());

        verify(createPurchaseUseCase, times(0)).createPurchase(any());
    }


    @Test
    @DisplayName("Should not create a purchase when description is invalid")
    public void shouldNotCreatePurchaseDescriptionInvalid() throws Exception {
        mockMvc.perform(
                        post("/v1/purchase")
                                .contentType(APPLICATION_JSON)
                                .content(getCreatePurchaseModelString(INVALID_DESCRIPTION, new BigDecimal("10.33")))
                ).andDo(print())
                .andExpect(status().isBadRequest());

        verify(createPurchaseUseCase, times(0)).createPurchase(any());
    }

    @Test
    @DisplayName("Should not create a purchase when description is null")
    public void shouldNotCreatePurchaseDescriptionNull() throws Exception {
        mockMvc.perform(
                        post("/v1/purchase")
                                .contentType(APPLICATION_JSON)
                                .content(getCreatePurchaseModelString(null, new BigDecimal("10.33")))
                ).andDo(print())
                .andExpect(status().isBadRequest());

        verify(createPurchaseUseCase, times(0)).createPurchase(any());
    }

    @Test
    @DisplayName("Should not create a purchase when amount is null")
    public void shouldNotCreatePurchaseAmountNull() throws Exception {
        mockMvc.perform(
                        post("/v1/purchase")
                                .contentType(APPLICATION_JSON)
                                .content(getCreatePurchaseModelString("Description", null))
                ).andDo(print())
                .andExpect(status().isBadRequest());

        verify(createPurchaseUseCase, times(0)).createPurchase(any());
    }

    private String getCreatePurchaseModelString(final String description, final BigDecimal amount) throws JsonProcessingException {
        return mapper.writeValueAsString(
            new CreatePurchaseRequest(description, amount)
        );
    }

}
