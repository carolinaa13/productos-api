package com.utn.productos_api.service;


import com.utn.productos_api.exception.ProductoNotFoundException;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper; // <-- 1. Inyectar aquí también
    // Inyección de dependencias por constructor (práctica recomendada)
    public ProductoService(ProductoRepository productoRepository, ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));

        modelMapper.map(productoActualizado, productoExistente);

        return productoRepository.save(productoExistente);
    }

    public Producto actualizarStock(Long id, Integer nuevoStock) {
        // 1. Buscamos el producto
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));

        // 2. Actualizamos solo el stock
        productoExistente.setStock(nuevoStock);

        // 3. Guardamos y retornamos
        return productoRepository.save(productoExistente);
    }
    public void eliminarProducto(Long id) {
        // 1. Verificamos si el producto existe antes de intentar borrarlo.
        //    Esto es bueno para poder lanzar una excepción personalizada si no se encuentra.
        if (!productoRepository.existsById(id)) {
            // (Esto lo manejaremos mejor en la Parte 3 con el paquete 'exception')
            throw new ProductoNotFoundException("Producto no encontrado con id: " + id);
        }

        // 2. Si existe, procedemos a eliminarlo.
        productoRepository.deleteById(id);
    }
}
