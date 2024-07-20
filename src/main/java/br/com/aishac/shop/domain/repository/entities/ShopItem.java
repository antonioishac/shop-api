package br.com.aishac.shop.domain.repository.entities;

import br.com.aishac.shop.domain.dtos.ShopItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tb_shop_item")
public class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_identifier")
    private String productIdentifier;

    private Integer amount;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public static ShopItem convert(ShopItemDTO dto) {
        ShopItem shopItem = new ShopItem();
        shopItem.setProductIdentifier(dto.getProductIdentifier());
        shopItem.setAmount(dto.getAmount());
        shopItem.setPrice(dto.getPrice());
        return shopItem;
    }
}