package br.com.aishac.shop.domain.dtos;

import br.com.aishac.shop.domain.repository.entities.ShopItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ShopItemDTO {

    @Schema(description = "Código do produto.", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productIdentifier;

    @Schema(description = "Qtde do produto.", example = "1")
    private Integer amount;

    @Schema(description = "Valor do produto.", example = "100")
    private BigDecimal price;

    public static ShopItemDTO convert(ShopItem shopItem) {
        ShopItemDTO dto = new ShopItemDTO();
        dto.setProductIdentifier(shopItem.getProductIdentifier());
        dto.setAmount(shopItem.getAmount());
        dto.setPrice(shopItem.getPrice());
        return dto;
    }
}