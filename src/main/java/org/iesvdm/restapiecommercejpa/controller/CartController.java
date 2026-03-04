package org.iesvdm.restapiecommercejpa.controller;

import org.iesvdm.restapiecommercejpa.model.CartResponseDTO;
import org.iesvdm.restapiecommercejpa.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartItemService cartItemService;
    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Long quantity) {

        return ResponseEntity.ok(cartItemService.addToCart(userId, productId, quantity));
    }
}
