package com.utn.productos_api.model;

import com.utn.productos_api.model.Categoria;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Anotación de Lombok para generar getters, setters, constructor, etc.
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    // Usamos @Enumerated(EnumType.STRING) para guardar el nombre del enum (ej. "ROPA")
    // en lugar de su índice numérico (0, 1, 2...), lo cual es mucho más robusto.
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
