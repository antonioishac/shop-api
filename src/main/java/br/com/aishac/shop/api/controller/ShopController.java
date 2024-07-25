package br.com.aishac.shop.api.controller;

import br.com.aishac.shop.domain.dtos.ShopDTO;
import br.com.aishac.shop.domain.repository.ShopRepository;
import br.com.aishac.shop.domain.repository.entities.Shop;
import br.com.aishac.shop.domain.repository.entities.ShopItem;
import br.com.aishac.shop.domain.service.events.KafkaClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/shop")
@RequiredArgsConstructor
@Tag(name = "Shop", description = "Gerênciando as vendas.")
public class ShopController {

    private final ShopRepository shopRepository;
    private final KafkaClient kafkaClient;

    @GetMapping
    @Operation(summary = "Listando as vendas.", description = "Lista das vendas realizadas pelo i-delivery.")
    public List<ShopDTO> getShop() {
        return shopRepository
                .findAll()
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Cadastro de vendas.", description = "Cadastrando uma venda e criando um tópico no kafka para validação.")
    public ShopDTO saveShop(@RequestBody ShopDTO shopDTO) {

        shopDTO.setIdentifier(UUID.randomUUID().toString());
        shopDTO.setDateShop(LocalDateTime.now());
        shopDTO.setStatus("PENDING");

        Shop shop = Shop.convert(shopDTO);
        for (ShopItem shopItem : shop.getItems()) {
            shopItem.setShop(shop);
        }

        shopDTO = ShopDTO.convert(shopRepository.save(shop));
        kafkaClient.sendMessage(shop);

        return shopDTO;
    }
}
