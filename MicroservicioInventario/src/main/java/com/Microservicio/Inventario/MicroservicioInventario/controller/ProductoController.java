package com.Microservicio.Inventario.MicroservicioInventario.controller;

import com.Microservicio.Inventario.MicroservicioInventario.dto.ProductoRequestDTO;
import com.Microservicio.Inventario.MicroservicioInventario.model.Producto;
import com.Microservicio.Inventario.MicroservicioInventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Endpoint para listar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarTodos();
        return ResponseEntity.ok(productos);
    }

    // Endpoint para crear un producto con validación de DTO
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoRequestDTO productoDTO) {
        Producto nuevoProducto = productoService.guardarProducto(productoDTO);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
}