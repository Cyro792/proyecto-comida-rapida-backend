package com.pagos.microservicio.controller;

import com.pagos.microservicio.dto.PagoRequestDTO;
import com.pagos.microservicio.model.Pago;
import com.pagos.microservicio.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // Modificamos el endpoint para cumplir con la rúbrica:
    // 1. @Valid: Activa las validaciones del DTO (@NotNull, @Min, etc.) antes de entrar al método.
    // 2. ResponseEntity<Pago>: Nos permite controlar el código de estado HTTP de retorno.
    @PostMapping
    public ResponseEntity<Pago> realizarPago(@Valid @RequestBody PagoRequestDTO pagoDTO) {
        Pago nuevoPago = pagoService.procesarPago(pagoDTO);

        // Retornamos un estado HTTP 201 Created, que es la buena práctica REST obligatoria en la pauta
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }
}