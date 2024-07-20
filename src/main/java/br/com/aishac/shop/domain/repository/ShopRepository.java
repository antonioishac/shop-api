package br.com.aishac.shop.domain.repository;

import br.com.aishac.shop.domain.repository.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    public Shop findByIdentifier(String identifier);
}