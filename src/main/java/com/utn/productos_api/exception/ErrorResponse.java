package com.utn.productos_api.exception;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor // Constructor simple para todos los campos
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int estado; // Código de estado HTTP
    private String mensaje;
    private String ruta;
}
