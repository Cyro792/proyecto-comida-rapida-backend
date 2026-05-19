package com.comidarapida.catalogo.service;

import com.comidarapida.catalogo.dto.ProductoDTO;
import com.comidarapida.catalogo.model.Producto;
import com.comidarapida.catalogo.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Usamos la forma moderna, igual que en Usuarios
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<ProductoDTO> obtenerTodos(){
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(this::convertirADTO).toList();
    }

    public ProductoDTO guardarProducto(ProductoDTO productoDTO){
        Producto nuevaEntidad = new Producto();
        nuevaEntidad.setNombre(productoDTO.getNombre());
        nuevaEntidad.setDescripcion(productoDTO.getDescripcion());
        nuevaEntidad.setPrecio(productoDTO.getPrecio());

        Producto productoGuardado = productoRepository.save(nuevaEntidad);
        return convertirADTO(productoGuardado);
    }

    private ProductoDTO convertirADTO(Producto producto){
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto()); // Agregamos el ID al mapeo
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        return dto;
    }

    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO){
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error!! El producto no existe"));

        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setDescripcion(productoDTO.getDescripcion());
        productoExistente.setPrecio(productoDTO.getPrecio());

        Producto productorActualizado = productoRepository.save(productoExistente);
        return convertirADTO(productorActualizado);
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
}