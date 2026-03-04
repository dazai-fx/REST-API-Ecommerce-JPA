package org.iesvdm.restapiecommercejpa.service;

import org.iesvdm.restapiecommercejpa.model.CartItem;
import org.iesvdm.restapiecommercejpa.model.CartResponseDTO;
import org.iesvdm.restapiecommercejpa.model.Product;
import org.iesvdm.restapiecommercejpa.model.User;
import org.iesvdm.restapiecommercejpa.repository.CartItemRepository;
import org.iesvdm.restapiecommercejpa.repository.ProductRepository;
import org.iesvdm.restapiecommercejpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public CartResponseDTO addToCart(Long userId, Long productId, Long quantity){
        // 1. Validar que existan usuario y producto
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        // 2. Lógica: ¿Ya existe este producto en el carrito del usuario?
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId)
                .orElse(new CartItem());

        if (cartItem.getId() == null) {
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCreatedDate(LocalDateTime.now());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setModifiedDate(LocalDateTime.now());
        }

        cartItemRepository.save(cartItem);

        // 3. Obtener lista actualizada y calcular total
        List<CartItem> userItems = cartItemRepository.findByUserId(userId);

        BigDecimal total = userItems.stream()
                .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponseDTO(userItems, total);
    }

}
