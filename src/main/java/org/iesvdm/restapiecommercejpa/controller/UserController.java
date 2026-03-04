package org.iesvdm.restapiecommercejpa.controller;

import org.iesvdm.restapiecommercejpa.model.User;
import org.iesvdm.restapiecommercejpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*Al marcar tu clase como @RestController (en lugar de solo @Controller), Spring asume por defecto que:

Entrada: Vas a recibir datos en formato JSON.

Salida: Todo lo que devuelvas (un Objeto, una Lista, etc.) debe ser transformado a JSON antes de enviarlo al cliente*/
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        //RequestBody: no busques los datos en la URL. Mira dentro del cuerpo (body) de la petición,
        // coge ese texto JSON y conviértelo automáticamente en un objeto Java de tipo Product".
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

}
