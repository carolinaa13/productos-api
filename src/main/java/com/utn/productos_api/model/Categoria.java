package com.utn.productos_api.model;

public enum Categoria {
    Electrónica,
    Ropa,
    Alimentos,
    Hogar,
    Deportes;

    public String getDescripcion() { return name(); }

}
