package org.iesvdm.restapiecommercejpa.repository;

import org.iesvdm.restapiecommercejpa.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Busca si el usuario ya tiene ese producto en el carrito
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);

    // Lista todos los items del carrito de un usuario concreto
    List<CartItem> findByUserId(Long userId);

}
