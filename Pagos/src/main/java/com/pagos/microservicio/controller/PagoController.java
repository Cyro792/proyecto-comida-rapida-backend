package com.pagos.microservicio.controller;

import com.pagos.microservicio.dto.PagoRequestDTO;
import com.pagos.microservicio.model.Pago;
import com.pagos.microservicio.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // <-- Importante agregar este import

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<Pago> realizarPago(@Valid @RequestBody PagoRequestDTO pagoDTO) {
        Pago nuevoPago = pagoService.procesarPago(pagoDTO);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pago>> obtenerHistorial() {
        List<Pago> historial = pagoService.obtenerTodos();
        return ResponseEntity.ok(historial);
    }
}