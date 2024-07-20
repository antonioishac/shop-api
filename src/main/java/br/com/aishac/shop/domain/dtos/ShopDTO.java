package br.com.aishac.shop.domain.dtos;

import br.com.aishac.shop.domain.repository.entities.Shop;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShopDTO {

    private String identifier;
    private LocalDateTime dateShop;
    private String status;
    private List<ShopItemDTO> items = new ArrayList<>();

    public static ShopDTO convert(Shop shop) {
        ShopDTO dto = new ShopDTO();
        dto.setIdentifier(shop.getIdentifier());
        dto.setDateShop(shop.getDateShop());
        dto.setStatus(shop.getStatus());
        dto.setItems(shop
                .getItems()
                .stream()
                .map(i -> ShopItemDTO.convert(i))
                .collect(Collectors.toList()));
        return dto;
    }
}