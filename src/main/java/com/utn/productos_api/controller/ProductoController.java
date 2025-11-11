package com.utn.productos_api.controller;
import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.exception.ProductoNotFoundException;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.service.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper; // <-- 1. Importar
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Gestión de Productos", description = "API REST para gestionar productos")
public class ProductoController {

    private final ProductoService productoService;
    private final ModelMapper modelMapper; // <-- 2. Declarar ModelMapper

    // 3. Inyectar ModelMapper en el constructor
    public ProductoController(ProductoService productoService, ModelMapper modelMapper) {
        this.productoService = productoService;
        this.modelMapper = modelMapper;
    }


    // --- ENDPOINTS CRUD (USANDO ModelMapper) ---

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        // 4. Mapeo en una línea
        Producto producto = modelMapper.map(productoDTO, Producto.class);

        Producto productoGuardado = productoService.crearProducto(producto);

        // Mapeo de respuesta
        ProductoResponseDTO responseDTO = modelMapper.map(productoGuardado, ProductoResponseDTO.class);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();

        // 4. Mapeo de la lista usando Streams
        List<ProductoResponseDTO> dtos = productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoResponseDTO.class)) // <-- Mapeo
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));

        // 4. Mapeo de respuesta
        ProductoResponseDTO responseDTO = modelMapper.map(producto, ProductoResponseDTO.class);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@RequestParam Categoria categoria) {
        List<Producto> productos = productoService.obtenerPorCategoria(categoria);

        // 4. Mapeo de la lista
        List<ProductoResponseDTO> dtos = productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoResponseDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        // 4. Mapeo de entrada
        Producto productoAActualizar = modelMapper.map(productoDTO, Producto.class);

        Producto productoActualizado = productoService.actualizarProducto(id, productoAActualizar);

        // 4. Mapeo de salida
        ProductoResponseDTO responseDTO = modelMapper.map(productoActualizado, ProductoResponseDTO.class);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockDTO stockDTO) {
           Producto productoActualizado = productoService.actualizarStock(id, stockDTO.getStock());

        // 4. Mapeo de salida
        ProductoResponseDTO responseDTO = modelMapper.map(productoActualizado, ProductoResponseDTO.class);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
