package com.pagos.microservicio.controller;

import com.pagos.microservicio.model.Pago;
import com.pagos.microservicio.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    //Endpoint para recibir y procesar un pago
    @PostMapping
    public Pago realizarPago(@RequestBody Pago pago) {
        return pagoService.procesarPago(pago);
    }

    //Endpoint para listar todos los pagos hechos (Y guardarlos en un Historial)
    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.obtenerTodosLosPagos();
    }
}