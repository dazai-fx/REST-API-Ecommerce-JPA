package org.iesvdm.restapiecommercejpa.controller;

import org.iesvdm.restapiecommercejpa.model.Product;
import org.iesvdm.restapiecommercejpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Listar con Paginación, Ordenación y Filtro (Punto 4)
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(required = false) String name, // Filtro opcional por nombre
            @RequestParam(defaultValue = "0") int page, // Número de página (empieza en 0)
            @RequestParam(defaultValue = "10") int size, // Cuántos productos ver por página
            @RequestParam(defaultValue = "id,asc") String[] sort) { // Campo de orden y dirección

        // 1. Determinamos la dirección (ASC o DESC) leyendo el segundo elemento del array 'sort'
        Sort.Direction direction = sort[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        // 2. Creamos el objeto Pageable que combina página, tamaño y el criterio de ordenación
        // Sort.by(dirección, campo) -> ej: Sort.by(DESC, "price")
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        // Llamamos al servicio y devolvemos un 200 OK con la página de resultados
        return ResponseEntity.ok(productService.listAll(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id)); //El código 200 OK es el estándar para decir que la petición fue exitosa.
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        // ÉXITO SIN DATOS: .noContent() abre la configuración, .build() la cierra.
        //El código 204 No Content le dice al cliente (Postman): "La operación se ha realizado con éxito, pero no tengo ningún dato que enviarte de vuelta".
        //Es el estándar de oro para los métodos DELETE. Si borras un producto, el producto ya no existe, por lo que no tiene sentido devolver el objeto en el JSON.
        return ResponseEntity.noContent().build();
    }

}
