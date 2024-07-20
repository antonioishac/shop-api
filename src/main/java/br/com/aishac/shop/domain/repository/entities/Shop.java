package br.com.aishac.shop.domain.repository.entities;

import br.com.aishac.shop.domain.dtos.ShopDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "tb_shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    private String status;

    @Column(name = "date_shop")
    private LocalDateTime dateShop;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "shop")
    private List<ShopItem> items;

    public static Shop convert(ShopDTO dto) {
        Shop shop = new Shop();
        shop.setIdentifier(dto.getIdentifier());
        shop.setStatus(dto.getStatus());
        shop.setDateShop(dto.getDateShop());
        shop.setItems(dto
                .getItems()
                .stream()
                .map(i -> ShopItem.convert(i))
                .collect(Collectors.toList()));

        return shop;
    }

}