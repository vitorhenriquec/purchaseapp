package com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange;

import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCaseImpl.NUMBER_DECIMAL_PLACES;

@Data
@NoArgsConstructor
public class RetrievePurchaseResponse{
    private Long id;

    private String description;

    private BigDecimal amount;

    private String createdAt;

    private List<AmountConversion> amountConversions;

    public RetrievePurchaseResponse(final PurchaseModel purchaseModel, final List<FiscalDataModel> fiscalDataModels) {
        this.id = purchaseModel.getId();
        this.description = purchaseModel.getDescription();
        this.amount = purchaseModel.getAmount();
        this.createdAt = purchaseModel.getCreatedAt().toString();
        this.amountConversions = fiscalDataModels.stream().map(fiscalDataModel -> new AmountConversion(
                fiscalDataModel.getCountryCurrencyDesc(),
                fiscalDataModel.getExchangeRate(),
                purchaseModel.getAmount().multiply(fiscalDataModel.getExchangeRate()).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.HALF_UP)
        )).toList();
    }
}
