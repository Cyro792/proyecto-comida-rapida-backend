package com.comidarapida.catalogo.controller;

import com.comidarapida.catalogo.dto.ProductoDTO;
import com.comidarapida.catalogo.dto.UsuarioResponseDTO;
import com.comidarapida.catalogo.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Slf4j
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/integracion-inventario")
    public ResponseEntity<List<Object>> probarConexionInventario() {
        return ResponseEntity.ok(productoService.traerInventario());
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProducto(){
        log.info("Ejecutando petición para listar todos los productos del catálogo");
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO){
        log.info("Recibiendo petición para crear un nuevo producto: {}", productoDTO.getNombre());
        ProductoDTO nuevoProducto = productoService.guardarProducto(productoDTO);
        log.info("Producto creado exitosamente con ID: {}", nuevoProducto.getIdProducto());
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO){
        log.info("Recibiendo petición para actualizar el producto con ID: {}", id);
        ProductoDTO productoActualizado = productoService.actualizarProducto(id, productoDTO);
        log.info("Producto actualizado exitosamente con ID: {}", id);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        log.warn("Recibiendo petición para eliminar el producto con ID: {}", id);
        productoService.eliminarProducto(id);
        log.info("Producto eliminado exitosamente con ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/prueba-feign/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> probarFeign(@PathVariable Long idUsuario){
        log.info("Catálogo está intentando comunicarse con Usuarios para buscar el ID: {}", idUsuario);
        UsuarioResponseDTO usuarioEncontrado = productoService.probarConexionConUsuarios(idUsuario);
        return ResponseEntity.ok(usuarioEncontrado);
    }
}